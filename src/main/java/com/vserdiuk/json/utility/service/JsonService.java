package com.vserdiuk.json.utility.service;

import java.util.List;

/**
 * Service interface for various JSON operations.
 */
public interface JsonService {

    /**
     * Validates if the provided JSON string is valid.
     *
     * @param json JSON string to validate
     * @return {@code true} if JSON is valid, otherwise {@code false}
     */
    boolean validateJson(String json);

    /**
     * Returns a pretty-printed version of the given JSON string.
     *
     * @param json JSON string to pretty print
     * @return pretty-printed JSON string
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String prettyPrintJson(String json);

    /**
     * Merges two JSON strings into a single JSON object.
     *
     * @param jsons the list of JSONs to merge
     * @return merged JSON string
     * @throws IllegalArgumentException if either input JSON is invalid
     */
    String mergeJson(List<String> jsons);

    /**
     * Validates a JSON string against a JSON Schema.
     *
     * @param json   the JSON string to validate
     * @param schema the JSON Schema string
     * @return {@code true} if the JSON is valid according to the schema, otherwise {@code false}
     * @throws IllegalArgumentException if either JSON or schema is invalid
     */
    boolean validateJsonWithSchema(String json, String schema);

    /**
     * Decreases the size of JSON by minifying it (removing whitespace, line breaks, etc).
     *
     * @param json JSON string to minify
     * @return minified JSON string
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String minifyJson(String json);

    /**
     * Sorts the JSON object keys alphabetically.
     * If a field name is provided, sorts arrays of objects by that field.
     *
     * @param json        JSON string to sort
     * @param sortByField field to sort array objects by (can be {@code null} for default alphabetical sort)
     * @return sorted JSON string
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String sortJson(String json, String sortByField);

    /**
     * Searches for a term in the JSON (keys or values) and returns matching JSON paths.
     *
     * @param json       the JSON to search
     * @param searchTerm the term to search for
     * @return list of JSON paths where the term is found
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    List<String> findInJson(String json, String searchTerm);

    /**
     * Converts a JSON array of objects to CSV format.
     *
     * @param json JSON array string
     * @return CSV string
     * @throws IllegalArgumentException if the input JSON is invalid or not an array
     */
    String convertJsonToCsv(String json);

    /**
     * Converts a CSV string to a JSON array of objects.
     *
     * @param csv the CSV string
     * @return JSON array string
     * @throws IllegalArgumentException if the CSV is invalid
     */
    String convertCsvToJson(String csv);

    /**
     * Converts JSON to XML format.
     *
     * @param json the JSON string
     * @return XML string
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String convertJsonToXml(String json);

    /**
     * Converts XML to JSON format.
     *
     * @param xml the XML string
     * @return JSON string
     * @throws IllegalArgumentException if the input XML is invalid
     */
    String convertXmlToJson(String xml);

    /**
     * Converts a JSON array of objects to an Excel file (as a Base64-encoded string).
     *
     * @param json the JSON array string
     * @return Base64-encoded Excel file (XLSX)
     * @throws IllegalArgumentException if the input JSON is invalid or not an array
     */
    String convertJsonToExcel(String json);

    /**
     * Converts a Base64-encoded Excel file to a JSON array of objects.
     *
     * @param base64Excel Base64-encoded Excel file (.xlsx)
     * @return JSON array string
     * @throws IllegalArgumentException if the input is invalid
     */
    String convertExcelToJson(String base64Excel);

    /**
     * Converts JSON to YAML format.
     *
     * @param json the JSON string
     * @return YAML string
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String convertJsonToYaml(String json);

    /**
     * Converts YAML to JSON format.
     *
     * @param yaml the YAML string
     * @return JSON string
     * @throws IllegalArgumentException if the input YAML is invalid
     */
    String convertYamlToJson(String yaml);

    /**
     * Generates TypeScript type definitions and documentation from JSON.
     *
     * @param json the JSON string
     * @return generated TypeScript type definitions
     * @throws IllegalArgumentException if the input JSON is invalid
     */
    String generateTypeDefinitionsFromJson(String json);
}
