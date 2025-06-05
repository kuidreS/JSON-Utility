package com.vserdiuk.json.utility.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.vserdiuk.json.utility.service.JsonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Implementation of {@link JsonService} that provides JSON validation, transformation, and conversion operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JsonServiceImpl implements JsonService {

    private final ObjectMapper objectMapper;
    private final ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    public boolean validateJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided.");
            return false;
        }
        try {
            objectMapper.readTree(json);
            log.debug("Valid JSON provided.");
            return true;
        } catch (Exception e) {
            log.warn("Invalid JSON: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public String prettyPrintJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for pretty print.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String prettyJson = prettyPrinter.writeValueAsString(jsonNode);
            log.debug("Pretty-printed JSON successfully.");
            return prettyJson;
        } catch (Exception e) {
            log.warn("Failed to pretty print JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON: " + e.getMessage(), e);
        }
    }

    @Override
//    public String mergeJson(String json1, String json2) {
//        try {
//            JsonNode tree1 = objectMapper.readTree(json1);
//            JsonNode tree2 = objectMapper.readTree(json2);
//            JsonNode merged = mergeJsonNodes(tree1, tree2);
//            String mergedJson = objectMapper.writeValueAsString(merged);
//            log.debug("Merged JSON successfully.");
//            return mergedJson;
//        } catch (Exception e) {
//            log.warn("Failed to merge JSONs: {}", e.getMessage());
//            throw new IllegalArgumentException("Invalid JSON: " + e.getMessage(), e);
//        }
//    }

    public String mergeJson(List<String> jsons) {
        try {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            JsonNode firstTree = null;
            for (String json : jsons) {
                JsonNode tree = objectMapper.readTree(json);
                if (firstTree == null) {
                    firstTree = tree;
                } else if (!haveSameStructure(firstTree, tree)) {
                    throw new IllegalArgumentException("JSONs have different structures.");
                }
                arrayNode.add(tree);
            }
            String mergedJson = objectMapper.writeValueAsString(arrayNode);
            log.debug("Merged JSONs successfully into an array.");
            return mergedJson;
        } catch (Exception e) {
            log.warn("Failed to merge JSONs: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or merge error: " + e.getMessage(), e);
        }
    }

    private boolean haveSameStructure(JsonNode node1, JsonNode node2) {
        // Handle null or missing nodes
        if (node1 == null || node2 == null) {
            return node1 == node2;
        }

        // Check if both nodes are of the same type
        if (node1.getNodeType() != node2.getNodeType()) {
            return false;
        }

        // Handle objects
        if (node1.isObject() && node2.isObject()) {
            ObjectNode obj1 = (ObjectNode) node1;
            ObjectNode obj2 = (ObjectNode) node2;

            // Check if they have the same field names
            Set<String> fields1 = new HashSet<>();
            Set<String> fields2 = new HashSet<>();
            obj1.fieldNames().forEachRemaining(fields1::add);
            obj2.fieldNames().forEachRemaining(fields2::add);

            if (!fields1.equals(fields2)) {
                return false;
            }

            // Recursively check the structure of each field
            for (String field : fields1) {
                if (!haveSameStructure(obj1.get(field), obj2.get(field))) {
                    return false;
                }
            }
            return true;
        }

        // Handle arrays
        if (node1.isArray() && node2.isArray()) {
            ArrayNode arr1 = (ArrayNode) node1;
            ArrayNode arr2 = (ArrayNode) node2;

            // If arrays are empty, they are considered to have the same structure
            if (arr1.size() == 0 && arr2.size() == 0) {
                return true;
            }

            // Check the structure of the first element (if any) as a representative
            // This assumes arrays contain elements with similar structures
            if (arr1.size() > 0 && arr2.size() > 0) {
                return haveSameStructure(arr1.get(0), arr2.get(0));
            }

            // If one array is empty and the other is not, consider them different
            return arr1.size() == arr2.size();
        }

        // For primitive types (string, number, boolean, etc.), same type is sufficient
        return node1.isValueNode() && node2.isValueNode();
    }

    @Override
    public boolean validateJsonWithSchema(String json, String schema) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode schemaNode = objectMapper.readTree(schema);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema jsonSchema = factory.getSchema(schemaNode);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
            if (errors.isEmpty()) {
                log.debug("JSON is valid against schema.");
                return true;
            }
            log.warn("JSON validation errors: {}", errors);
            return false;
        } catch (Exception e) {
            log.warn("Failed to validate JSON against schema: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or schema: " + e.getMessage(), e);
        }
    }

    @Override
    public String minifyJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for minify.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            String minifiedJson = objectMapper.writeValueAsString(jsonNode);
            log.debug("Minified JSON successfully.");
            return minifiedJson;
        } catch (Exception e) {
            log.warn("Failed to minify JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public String sortJson(String json, String sortByField) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for sort.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);

            if (jsonNode.isArray() && sortByField != null && !sortByField.isBlank()) {
                // Sort the root-level array
                List<JsonNode> elements = new ArrayList<>();
                jsonNode.forEach(elements::add);
                elements.sort(Comparator.comparing(a -> a.get(sortByField).asText()));
                ArrayNode sortedArray = objectMapper.createArrayNode();
                elements.forEach(sortedArray::add);
                String sortedJson = prettyPrinter.writeValueAsString(sortedArray);
                log.debug("Sorted JSON root-level array successfully.");
                return sortedJson;
            } else {
                JsonNode sortedNode = sortJsonNode(jsonNode, sortByField);
                String sortedJson = prettyPrinter.writeValueAsString(sortedNode);
                log.debug("Sorted JSON successfully.");
                return sortedJson;
            }
        } catch (Exception e) {
            log.warn("Failed to sort JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> findInJson(String json, String searchTerm) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for findInJson.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            List<String> matchingPaths = new ArrayList<>();
            findInJsonRecursive(jsonNode, "$", searchTerm, matchingPaths);
            log.debug("Found {} matches for term '{}'", matchingPaths.size(), searchTerm);
            return matchingPaths;
        } catch (Exception e) {
            log.warn("Failed to search in JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertJsonToCsv(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode companiesNode = jsonNode.get("companies");
            if (companiesNode == null || !companiesNode.isArray()) {
                throw new IllegalArgumentException("JSON must contain an array of objects for CSV conversion.");
            }
            Set<String> fieldNames = getFieldNames(companiesNode);
            StringWriter csvWriter = getCsvWriter(fieldNames, companiesNode);
            String csv = csvWriter.toString();
            log.debug("Converted JSON to CSV successfully.");
            return csv;
        } catch (Exception e) {
            log.warn("Failed to convert JSON to CSV: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertCsvToJson(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            log.warn("Empty or null CSV provided.");
            throw new IllegalArgumentException("Invalid CSV: input is empty or null");
        }
        try {
            StringReader reader = new StringReader(csv);
            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setHeader()
                    .setSkipHeaderRecord(false)
                    .setIgnoreEmptyLines(true)
                    .setTrim(true)
                    .get();
            String jsonArray = convertCsvToJsonString(csvFormat, reader);
            log.debug("Converted CSV to JSON successfully.");
            return jsonArray;
        } catch (Exception e) {
            log.warn("Failed to convert CSV to JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid CSV or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertJsonToXml(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for conversion to XML.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            log.debug("Converted JSON to XML successfully.");
            return xml;
        } catch (Exception e) {
            log.warn("Failed to convert JSON to XML: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertXmlToJson(String xml) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(xml);
            String json = prettyPrinter.writeValueAsString(jsonNode);
            log.debug("Converted XML to JSON successfully.");
            return json;
        } catch (Exception e) {
            log.warn("Failed to convert XML to JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid XML or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertJsonToExcel(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            if (!jsonNode.isArray()) {
                throw new IllegalArgumentException("JSON must be an array of objects for Excel conversion.");
            }
            Set<String> fieldNames = getFieldNames(jsonNode);
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Sheet1");
                createHeaderRow(sheet, fieldNames);
                fillDataRows(jsonNode, sheet, fieldNames);
                ByteArrayOutputStream out = writeWorkbookToStream(workbook);
                String base64Excel = Base64.getEncoder().encodeToString(out.toByteArray());
                log.debug("Converted JSON to Excel (Base64, {} bytes).", out.size());
                return base64Excel;
            }
        } catch (Exception e) {
            log.warn("Failed to convert JSON to Excel: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertJsonToYaml(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for conversion to YAML.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            YAMLMapper yamlMapper = new YAMLMapper();
            String yaml = yamlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            log.debug("Converted JSON to YAML successfully.");
            return yaml;
        } catch (Exception e) {
            log.warn("Failed to convert JSON to YAML: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JSON or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertExcelToJson(String base64Excel) {
        try {
            byte[] excelBytes = Base64.getDecoder().decode(base64Excel);
            try (InputStream inputStream = new ByteArrayInputStream(excelBytes);
                 Workbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet sheet = workbook.getSheetAt(0);
                Row headerRow = sheet.getRow(0);
                List<String> headers = getHeaders(headerRow);
                List<Map<String, String>> recordsList = getRecordsList(sheet, headers);
                String jsonArray = prettyPrinter.writeValueAsString(recordsList);
                log.debug("Converted Excel to JSON successfully.");
                return jsonArray;
            }
        } catch (Exception e) {
            log.warn("Failed to convert Excel to JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid Excel or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String convertYamlToJson(String yaml) {
        if (yaml == null || yaml.trim().isEmpty()) {
            log.warn("Empty or null YAML provided for conversion to JSON.");
            throw new IllegalArgumentException("Invalid YAML: input is empty or null");
        }
        try {
            YAMLMapper yamlMapper = new YAMLMapper();
            JsonNode jsonNode = yamlMapper.readTree(yaml);
            if (jsonNode == null || jsonNode.isMissingNode()) {
                log.warn("Parsed YAML resulted in null or missing node.");
                throw new IllegalArgumentException("Invalid YAML: could not parse");
            }
            String json = prettyPrinter.writeValueAsString(jsonNode);
            log.debug("Converted YAML to JSON successfully.");
            return json;
        } catch (Exception e) {
            log.warn("Failed to convert YAML to JSON: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid YAML or conversion error: " + e.getMessage(), e);
        }
    }

    @Override
    public String generateTypeDefinitionsFromJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            log.warn("Empty or null JSON provided for generating type definitions.");
            throw new IllegalArgumentException("Invalid JSON: input is empty or null");
        }
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            StringBuilder typeDefinitions = new StringBuilder();
            Set<String> generatedTypes = new HashSet<>(); // Avoid duplicate interface definitions
            generateTypeScriptDefinitionRecursive("RootObject", jsonNode, 0, typeDefinitions, generatedTypes);
            log.debug("Generated TypeScript definitions:\n{}", typeDefinitions);
            return typeDefinitions.toString();
        } catch (Exception e) {
            log.warn("Failed to generate type definitions from JSON: {}", json, e);
            throw new IllegalArgumentException("Invalid JSON or error during conversion: " + e.getMessage(), e);
        }
    }

    /**
     * Recursively generates TypeScript interfaces for nested JSON structures.
     */
    private void generateTypeScriptDefinitionRecursive(String typeName, JsonNode node, int indent,
                                                       StringBuilder output, Set<String> generatedTypes) {
        if (generatedTypes.contains(typeName)) {
            return; // Avoid duplicate definitions
        }
        generatedTypes.add(typeName);

        String indentStr = "  ".repeat(indent);
        output.append(indentStr).append("interface ").append(typeName).append(" {\n");

        Iterator<String> fields = node.fieldNames();
        while (fields.hasNext()) {
            String field = fields.next();
            JsonNode childNode = node.get(field);
            String childTypeName = capitalize(field);
            String fieldType = determineTypeScriptType(childNode, childTypeName);

            output.append(indentStr).append("  ").append(field).append(": ").append(fieldType).append(";\n");

            // Recursively generate nested interfaces
            if (childNode.isObject()) {
                generateTypeScriptDefinitionRecursive(childTypeName, childNode, indent, output, generatedTypes);
            } else if (childNode.isArray() && !childNode.isEmpty() && childNode.get(0).isObject()) {
                generateTypeScriptDefinitionRecursive(childTypeName + "Item", childNode.get(0), indent, output, generatedTypes);
            }
        }
        output.append(indentStr).append("}\n");
    }

    /**
     * Determines the TypeScript type for a given JSON node.
     *
     * @param node     the JSON node to analyze
     * @param typeName the suggested name for nested object types
     * @return the corresponding TypeScript type as a string
     */
    private String determineTypeScriptType(JsonNode node, String typeName) {
        if (node.isObject()) {
            return typeName;
        }
        if (node.isArray()) {
            return node.isEmpty() ? "any[]" : determineTypeScriptType(node.get(0), typeName + "Item") + "[]";
        }
        if (node.isTextual()) {
            return "string";
        }
        if (node.isNumber()) {
            return "number";
        }
        if (node.isBoolean()) {
            return "boolean";
        }
        if (node.isNull()) {
            return "null";
        }
        return "any";
    }

    /**
     * Capitalizes the first character of a string.
     *
     * @param str the input string to capitalize
     * @return the capitalized string, or the original string if null or empty
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Retrieves a list of records from an Excel sheet, mapping each row to a key-value map based on headers.
     *
     * @param sheet   The Excel sheet to process.
     * @param headers The list of header names from the first row.
     * @return A list of maps where each map represents a row with header names as keys and cell values as strings.
     */
    private List<Map<String, String>> getRecordsList(Sheet sheet, List<String> headers) {
        List<Map<String, String>> recordsList = new ArrayList<>();
        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                continue;
            }
            Map<String, String> recordMap = new LinkedHashMap<>();
            for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
                Cell cell = row.getCell(colIndex);
                String cellValue = cell != null ? getCellValueAsString(cell) : "";
                recordMap.put(headers.get(colIndex), cellValue);
            }
            recordsList.add(recordMap);
        }
        return recordsList;
    }

    /**
     * Extracts header names from the first row of an Excel sheet.
     *
     * @param headerRow The first row containing header names.
     * @return A list of header names as strings.
     */
    private List<String> getHeaders(Row headerRow) {
        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }

    /**
     * Converts an Excel cell value to a string representation.
     *
     * @param cell The cell to convert.
     * @return The string representation of the cell's value, or an empty string if the cell is blank or null.
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : Double.toString(cell.getNumericCellValue());
            case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    /**
     * Merges two JSON nodes recursively, updating the main node with values from the update node.
     *
     * @param mainNode   The primary JSON node to merge into.
     * @param updateNode The JSON node containing updates.
     * @return The merged JSON node.
     */
    private JsonNode mergeJsonNodes(JsonNode mainNode, JsonNode updateNode) {
        Iterator<String> fieldNames = updateNode.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode updateValue = updateNode.get(fieldName);
            if (mainNode.has(fieldName)) {
                JsonNode mainValue = mainNode.get(fieldName);
                if (mainValue.isObject() && updateValue.isObject()) {
                    mergeJsonNodes(mainValue, updateValue);
                } else {
                    ((ObjectNode) mainNode).replace(fieldName, updateValue);
                }
            } else {
                ((ObjectNode) mainNode).set(fieldName, updateValue);
            }
        }
        return mainNode;
    }

    /**
     * Converts a CSV string to a JSON array string using the provided CSV format and reader.
     *
     * @param csvFormat The CSV format configuration.
     * @param reader    The reader containing the CSV data.
     * @return The JSON array string representing the CSV data.
     * @throws IOException If an I/O error occurs during CSV parsing.
     */
    private String convertCsvToJsonString(CSVFormat csvFormat, StringReader reader) throws IOException {
        try (CSVParser csvParser = csvFormat.parse(reader)) {
            List<Map<String, String>> recordsList = new ArrayList<>();
            List<String> headers = csvParser.getHeaderNames();

            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.size() != headers.size()) {
                    throw new IllegalArgumentException("CSV record size mismatch: header size=" + headers.size() + ", record size=" + csvRecord.size());
                }
                recordsList.add(new LinkedHashMap<>(csvRecord.toMap()));
            }

            return prettyPrinter.writeValueAsString(recordsList);
        }
    }

    /**
     * Generates a CSV string from a JSON array node, using the specified field names as headers.
     *
     * @param fieldNames The set of field names to use as CSV headers.
     * @param jsonNode   The JSON array node to convert.
     * @return A StringWriter containing the CSV data.
     * @throws IOException If an I/O error occurs during CSV writing.
     */
    private StringWriter getCsvWriter(Set<String> fieldNames, JsonNode jsonNode) throws IOException {
        StringWriter csvWriter = new StringWriter();
        CSVFormat csvFormat = CSVFormat.Builder.create()
                .setHeader(fieldNames.toArray(new String[0]))
                .setSkipHeaderRecord(false)
                .get();
        try (CSVPrinter csvPrinter = new CSVPrinter(csvWriter, csvFormat)) {
            for (JsonNode objNode : jsonNode) {
                if (objNode.isObject()) {
                    List<String> values = new ArrayList<>();
                    for (String field : fieldNames) {
                        JsonNode valueNode = objNode.get(field);
                        values.add(valueNode != null ? valueNode.asText() : "");
                    }
                    csvPrinter.printRecord(values);
                }
            }
        }
        return csvWriter;
    }

    /**
     * Collects unique field names from a JSON array of objects.
     *
     * @param jsonNode The JSON array node to process.
     * @return A set of unique field names.
     */
    private Set<String> getFieldNames(JsonNode jsonNode) {
        Set<String> fieldNames = new LinkedHashSet<>();
        for (JsonNode node : jsonNode) {
            if (node.isObject()) {
                Iterator<String> fields = node.fieldNames();
                while (fields.hasNext()) {
                    String field = fields.next();
                    fieldNames.add(field);
                }
            }
        }
        return fieldNames;
    }

    /**
     * Recursively searches a JSON node for a search term, collecting matching JSON paths.
     *
     * @param node        The JSON node to search.
     * @param currentPath The current JSON path being processed.
     * @param searchTerm  The term to search for in keys and values.
     * @param matches     The list to store matching paths.
     */
    private void findInJsonRecursive(JsonNode node, String currentPath, String searchTerm, List<String> matches) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                String newPath = currentPath + "." + fieldName;
                JsonNode child = node.get(fieldName);
                if (fieldName.equals(searchTerm)) {
                    matches.add(newPath + " (key)");
                }
                if (child.isValueNode() && child.asText().equals(searchTerm)) {
                    matches.add(newPath + " (value)");
                }
                findInJsonRecursive(child, newPath, searchTerm, matches);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                String newPath = currentPath + "[" + i + "]";
                JsonNode child = node.get(i);
                if (child.isValueNode() && child.asText().equals(searchTerm)) {
                    matches.add(newPath + " (value)");
                }
                findInJsonRecursive(child, newPath, searchTerm, matches);
            }
        }
    }

    /**
     * Sorts a JSON node recursively, ordering object keys alphabetically and optionally sorting arrays by a specified field.
     *
     * @param node        The JSON node to sort.
     * @param sortByField The field to sort arrays by, if specified.
     * @return The sorted JSON node.
     */
    private JsonNode sortJsonNode(JsonNode node, String sortByField) {
        if (node.isObject()) {
            TreeMap<String, JsonNode> sortedMap = new TreeMap<>();
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                sortedMap.put(fieldName, sortJsonNode(node.get(fieldName), sortByField));
            }
            ObjectNode sortedObject = objectMapper.createObjectNode();
            sortedMap.forEach(sortedObject::set);
            return sortedObject;
        } else if (node.isArray()) {
            ArrayNode sortedArray = objectMapper.createArrayNode();
            for (JsonNode element : node) {
                sortedArray.add(sortJsonNode(element, sortByField));
            }
            if (sortByField != null && !sortByField.isBlank() && !node.isEmpty() && node.get(0).isObject()) {
                List<JsonNode> elementsList = new ArrayList<>();
                node.forEach(elementsList::add);
                elementsList.sort((a, b) -> {
                    JsonNode fieldA = a.get(sortByField);
                    JsonNode fieldB = b.get(sortByField);
                    return (fieldA == null || fieldB == null) ? 0 : fieldA.asText().compareTo(fieldB.asText());
                });
                sortedArray.removeAll();
                elementsList.forEach(sortedArray::add);
            }
            return sortedArray;
        }
        return node;
    }

    /**
     * Creates a header row in an Excel sheet using the provided field names.
     *
     * @param sheet      The Excel sheet to add the header row to.
     * @param fieldNames The set of field names to use as headers.
     */
    private void createHeaderRow(Sheet sheet, Set<String> fieldNames) {
        Row headerRow = sheet.createRow(0);
        int colIndex = 0;
        for (String fieldName : fieldNames) {
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(fieldName);
        }
    }

    /**
     * Fills data rows in an Excel sheet from a JSON array node.
     *
     * @param jsonNode   The JSON array node containing the data.
     * @param sheet      The Excel sheet to fill.
     * @param fieldNames The set of field names to use as column headers.
     */
    private void fillDataRows(JsonNode jsonNode, Sheet sheet, Set<String> fieldNames) {
        int rowIndex = 1;
        for (JsonNode objNode : jsonNode) {
            Row row = sheet.createRow(rowIndex++);
            int colIndex = 0;
            for (String fieldName : fieldNames) {
                Cell cell = row.createCell(colIndex++);
                JsonNode valueNode = objNode.get(fieldName);
                cell.setCellValue(valueNode != null ? valueNode.asText() : "");
            }
        }
    }

    /**
     * Writes an Excel workbook to a byte array output stream.
     *
     * @param workbook The Excel workbook to write.
     * @return A ByteArrayOutputStream containing the workbook data.
     * @throws IOException If an I/O error occurs during writing.
     */
    private ByteArrayOutputStream writeWorkbookToStream(Workbook workbook) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        return out;
    }
}