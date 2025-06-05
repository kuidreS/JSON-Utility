
# JSON Utility Service

## Overview

This project offers a **RESTful API** for a variety of JSON operations, including:

- Validation (syntax and schema)
- Pretty printing and minifying
- Merging JSON objects
- Searching and sorting JSON data
- Converting JSON to/from other formats (CSV, XML, Excel, YAML)
- Generating type definitions from JSON

Built with modern Java and Spring Boot, this service is designed to streamline your JSON workflows.

---

## Features

✅ Validate JSON syntax  
✅ Validate JSON against schemas  
✅ Pretty-print and minify JSON  
✅ Merge JSON objects  
✅ Search keys and values within JSON  
✅ Sort JSON keys or arrays  
✅ Convert JSON to/from:
- CSV
- XML
- YAML
- Excel (Base64-encoded)

✅ Generate type definitions from JSON

---

## Technologies

- Java 17+
- Spring Boot 3.5+
- Spring Web
- Spring REST Docs (API documentation)
- Lombok
- Jackson (JSON handling)
- Apache POI (Excel)
- SnakeYAML (YAML)
- JSON Schema Validator

---

## Getting Started

### Prerequisites

- Java 17 or newer
- Maven 3.8+

### Build & Run

To build and run the project:

```bash
mvn clean package
java -jar target/json-utility-*.jar
```

Or run directly:

```bash
./mvnw spring-boot:run
```

The API will be available at:  
`http://localhost:8080/api/json`

---

## API Endpoints

| Endpoint                     | Method | Description                                      |
|------------------------------|--------|--------------------------------------------------|
| `/validate`                  | POST   | Validate JSON syntax                             |
| `/validate-schema`           | POST   | Validate JSON against JSON Schema                |
| `/pretty`                    | POST   | Pretty-print JSON                                |
| `/minify`                    | POST   | Minify JSON                                      |
| `/merge`                     | POST   | Merge two JSON objects                           |
| `/sort`                      | POST   | Sort JSON keys or array elements                 |
| `/find`                      | POST   | Search for keys/values in JSON                   |
| `/json-to-csv`               | POST   | Convert JSON to CSV                              |
| `/csv-to-json`               | POST   | Convert CSV to JSON                              |
| `/json-to-xml`               | POST   | Convert JSON to XML                              |
| `/xml-to-json`               | POST   | Convert XML to JSON                              |
| `/json-to-excel`             | POST   | Convert JSON to Base64-encoded Excel             |
| `/excel-to-json`             | POST   | Convert Base64-encoded Excel to JSON             |
| `/json-to-yaml`              | POST   | Convert JSON to YAML                             |
| `/yaml-to-json`              | POST   | Convert YAML to JSON                             |
| `/generate-type-definitions` | POST   | Generate type definitions from JSON              |

---

## API Documentation

This project uses **Spring REST Docs**. The documentation snippets will be generated after controller test passed.

You can also integrate the generated AsciiDoc snippets (`generated-snippets`) into your own documentation.

---

## Development & Testing

### Running Tests

```bash
./mvnw test
```

The project includes:

- Unit tests for core services
- Integration tests for REST APIs (with documentation generation)

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

**Vitalii Serdiuk**  
**email**: vitaliy.serdiuk@gmail.com
