package com.vserdiuk.json.utility.controller;

import com.vserdiuk.json.utility.service.JsonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for JSON Utility operations.
 * <p>
 * This controller provides endpoints for various JSON manipulation and conversion tasks.
 * These tasks include validation, pretty printing, merging, schema validation,
 * minifying, sorting, finding, and conversions to and from other formats such as CSV, XML, Excel, and YAML.
 * </p>
 */
@RestController
@RequestMapping("/api/json")
@RequiredArgsConstructor
public class JsonController {

    private final JsonService jsonService;

    /**
     * Validates if the given JSON string is syntactically correct.
     *
     * @param json the JSON string to validate
     * @return true if the JSON is valid, otherwise false
     */
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateJson(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.validateJson(json));
    }

    /**
     * Returns a pretty-printed version of the given JSON string.
     *
     * @param json the JSON string to pretty-print
     * @return the pretty-printed JSON string
     */
    @PostMapping("/pretty")
    public ResponseEntity<String> prettyPrintJson(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.prettyPrintJson(json));
    }

    /**
     * Merges two JSON strings into one.
     *
     * @param jsonList a list containing exactly two JSON strings to merge
     * @return the merged JSON string, or an error if input list size is not 2
     */
    @PostMapping("/merge")
    public ResponseEntity<String> mergeJson(@RequestBody List<String> jsonList) {
        if (jsonList.size() != 2) {
            return ResponseEntity.badRequest().body("Please provide exactly two or more JSON strings.");
        }
        return ResponseEntity.ok(jsonService.mergeJson(jsonList));
    }

    /**
     * Validates the given JSON against a provided JSON schema.
     *
     * @param schema the JSON schema to validate against
     * @param json   the JSON string to validate
     * @return true if the JSON is valid against the schema, otherwise false
     */
    @PostMapping("/validate-schema")
    public ResponseEntity<Boolean> validateJsonWithSchema(@RequestParam String schema, @RequestBody String json) {
        return ResponseEntity.ok(jsonService.validateJsonWithSchema(json, schema));
    }

    /**
     * Minifies the given JSON string by removing unnecessary whitespace.
     *
     * @param json the JSON string to minify
     * @return the minified JSON string
     */
    @PostMapping("/minify")
    public ResponseEntity<String> minifyJson(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.minifyJson(json));
    }

    /**
     * Sorts the keys of the given JSON string alphabetically or sorts arrays by a specific field.
     *
     * @param sortByField optional field to sort arrays by
     * @param json        the JSON string to sort
     * @return the sorted JSON string
     */
    @PostMapping("/sort")
    public ResponseEntity<String> sortJson(@RequestParam(required = false) String sortByField, @RequestBody String json) {
        return ResponseEntity.ok(jsonService.sortJson(json, sortByField));
    }

    /**
     * Finds and returns all matching keys and values in the given JSON string based on a search term.
     *
     * @param searchTerm the search term to look for
     * @param json       the JSON string to search
     * @return a list of matching key-value pairs
     */
    @PostMapping("/find")
    public ResponseEntity<List<String>> findInJson(@RequestParam String searchTerm, @RequestBody String json) {
        return ResponseEntity.ok(jsonService.findInJson(json, searchTerm));
    }

    /**
     * Converts the given JSON string to a CSV format.
     *
     * @param json the JSON string to convert
     * @return the CSV representation of the JSON
     */
    @PostMapping("/json-to-csv")
    public ResponseEntity<String> convertJsonToCsv(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.convertJsonToCsv(json));
    }

    /**
     * Converts the given CSV string to JSON format.
     *
     * @param csv the CSV string to convert
     * @return the JSON representation of the CSV
     */
    @PostMapping("/csv-to-json")
    public ResponseEntity<String> convertCsvToJson(@RequestBody String csv) {
        return ResponseEntity.ok(jsonService.convertCsvToJson(csv));
    }

    /**
     * Converts the given JSON string to XML format.
     *
     * @param json the JSON string to convert
     * @return the XML representation of the JSON
     */
    @PostMapping("/json-to-xml")
    public ResponseEntity<String> convertJsonToXml(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.convertJsonToXml(json));
    }

    /**
     * Converts the given XML string to JSON format.
     *
     * @param xml the XML string to convert
     * @return the JSON representation of the XML
     */
    @PostMapping("/xml-to-json")
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xml) {
        return ResponseEntity.ok(jsonService.convertXmlToJson(xml));
    }

    /**
     * Converts the given JSON string to a Base64-encoded Excel file.
     *
     * @param json the JSON string to convert
     * @return the Base64-encoded Excel file
     */
    @PostMapping("/json-to-excel")
    public ResponseEntity<String> convertJsonToExcel(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.convertJsonToExcel(json));
    }

    /**
     * Converts a Base64-encoded Excel file to JSON format.
     *
     * @param base64Excel the Base64-encoded Excel file
     * @return the JSON representation of the Excel data
     */
    @PostMapping("/excel-to-json")
    public ResponseEntity<String> convertExcelToJson(@RequestBody String base64Excel) {
        return ResponseEntity.ok(jsonService.convertExcelToJson(base64Excel));
    }

    /**
     * Converts the given JSON string to YAML format.
     *
     * @param json the JSON string to convert
     * @return the YAML representation of the JSON
     */
    @PostMapping("/json-to-yaml")
    public ResponseEntity<String> convertJsonToYaml(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.convertJsonToYaml(json));
    }

    /**
     * Converts the given YAML string to JSON format.
     *
     * @param yaml the YAML string to convert
     * @return the JSON representation of the YAML
     */
    @PostMapping("/yaml-to-json")
    public ResponseEntity<String> convertYamlToJson(@RequestBody String yaml) {
        return ResponseEntity.ok(jsonService.convertYamlToJson(yaml));
    }

    /**
     * Generates type definitions (e.g., TypeScript interfaces) from the given JSON.
     *
     * @param json the JSON string to generate type definitions from
     * @return the generated type definitions
     */
    @PostMapping("/generate-type-definitions")
    public ResponseEntity<String> generateTypeDefinitions(@RequestBody String json) {
        return ResponseEntity.ok(jsonService.generateTypeDefinitionsFromJson(json));
    }
}
