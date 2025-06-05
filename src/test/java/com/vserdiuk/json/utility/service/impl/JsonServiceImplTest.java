package com.vserdiuk.json.utility.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonServiceImplTest {

    private JsonServiceImpl jsonService;

    @BeforeEach
    void setUp() {
        jsonService = new JsonServiceImpl(new ObjectMapper());
    }

    /**
     * Tests the {@code validateJson} method with a valid JSON string.
     * Verifies that the method returns {@code true}, indicating that the input JSON is valid.
     */
    @Test
    void testValidateJson_validJson_returnsTrue() {
        // Arrange
        String json = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                  }
                """;

        // Act
        boolean isValid = jsonService.validateJson(json);

        // Assert
        assertTrue(isValid, "Expected JSON to be valid");
    }

    /**
     * Tests the {@code validateJson} method with an invalid JSON string.
     * Verifies that the method returns {@code false}, indicating that the input JSON is not valid.
     */
    @Test
    void testValidateJson_invalidJson_returnsFalse() {
        // Arrange
        String json = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                """; // Missing closing } char

        // Act
        boolean isValid = jsonService.validateJson(json);

        // Assert
        assertFalse(isValid, "Expected JSON to be invalid");
    }

    /**
     * Tests the {@code validateJson} method with an empty JSON string.
     * Verifies that the method returns {@code false}, indicating that the input is not a valid JSON.
     */
    @Test
    void testValidateJson_emptyJson_returnsFalse() {
        // Arrange
        String json = "";

        // Act
        boolean isValid = jsonService.validateJson(json);

        // Assert
        assertFalse(isValid, "Expected empty string to be invalid JSON");
    }

    /**
     * Tests the {@code validateJson} method with a {@code null} JSON input.
     * Verifies that the method returns {@code false}, indicating that the input is not valid JSON.
     */
    @Test
    void testValidateJson_nullJson_returnsFalse() {
        // Arrange
        String json = null;

        // Act
        boolean isValid = jsonService.validateJson(json);

        // Assert
        assertFalse(isValid, "Expected null input to be invalid JSON");
    }

    /**
     * Tests the {@code prettyPrintJson} method with a valid JSON string.
     * Verifies that the method returns the JSON in a pretty-printed format with indentation and line breaks.
     */
    @Test
    void testPrettyPrintJson_validJson_returnsPrettyJson() {
        // Arrange
        String json = "{\"company\":{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}}}";

        String expectedPrettyJson = """
                {
                  "company" : {
                    "name" : "Tech Solutions Inc.",
                    "founded" : 1998,
                    "employees" : [ {
                      "id" : 1,
                      "name" : "Alice Smith",
                      "title" : "CEO",
                      "contact" : {
                        "email" : "alice.smith@techsolutions.com",
                        "phone" : "+1-555-1234",
                        "address" : {
                          "street" : "123 Tech Lane",
                          "city" : "Innovate City",
                          "state" : "CA",
                          "zip" : "90001",
                          "coordinates" : {
                            "lat" : 34.0522,
                            "lng" : -118.2437
                          }
                        }
                      },
                      "projects" : [ {
                        "projectId" : "p-101",
                        "name" : "AI Assistant",
                        "status" : "active",
                        "budget" : 1200000.5,
                        "milestones" : [ {
                          "milestoneId" : "m-001",
                          "description" : "Initial Research",
                          "dueDate" : "2025-06-30",
                          "completed" : true
                        }, {
                          "milestoneId" : "m-002",
                          "description" : "Prototype Development",
                          "dueDate" : "2025-09-15",
                          "completed" : false
                        } ]
                      } ]
                    }, {
                      "id" : 2,
                      "name" : "Bob Johnson",
                      "title" : "CTO",
                      "contact" : {
                        "email" : "bob.johnson@techsolutions.com",
                        "phone" : "+1-555-5678",
                        "address" : {
                          "street" : "456 Dev Avenue",
                          "city" : "Code Town",
                          "state" : "NY",
                          "zip" : "10001",
                          "coordinates" : {
                            "lat" : 40.7128,
                            "lng" : -74.006
                          }
                        }
                      },
                      "projects" : [ ]
                    } ],
                    "departments" : {
                      "engineering" : {
                        "head" : "Bob Johnson",
                        "teams" : [ {
                          "teamName" : "Backend",
                          "members" : [ "Charlie", "Dana", "Eli" ],
                          "technologies" : [ "Node.js", "Python", "PostgreSQL" ]
                        }, {
                          "teamName" : "Frontend",
                          "members" : [ "Fiona", "George" ],
                          "technologies" : [ "React", "Vue", "HTML5", "CSS3" ]
                        } ]
                      },
                      "hr" : {
                        "head" : "Grace Lee",
                        "teams" : [ ]
                      }
                    },
                    "offices" : [ {
                      "location" : "San Francisco",
                      "capacity" : 50,
                      "openSince" : "2010-05-15",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : false,
                        "parking" : {
                          "available" : true,
                          "spots" : 20
                        }
                      }
                    }, {
                      "location" : "New York",
                      "capacity" : 30,
                      "openSince" : "2015-08-20",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : true,
                        "parking" : {
                          "available" : false,
                          "spots" : 0
                        }
                      }
                    } ],
                    "financials" : {
                      "fiscalYear" : 2024,
                      "revenue" : 5600000,
                      "expenses" : 3200000,
                      "profit" : 2400000,
                      "currency" : "USD",
                      "investors" : [ {
                        "name" : "Capital Ventures",
                        "investment" : 1000000,
                        "equity" : 10
                      }, {
                        "name" : "Tech Angels",
                        "investment" : 500000,
                        "equity" : 5
                      } ]
                    }
                  }
                }""";

        // Act
        String prettyJson = jsonService.prettyPrintJson(json);

        // Assert
        assertEquals(expectedPrettyJson, prettyJson, "Expected pretty-printed JSON output");
    }

    /**
     * Tests the {@code prettyPrintJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} indicating the JSON is invalid.
     */
    @Test
    void testPrettyPrintJson_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{\"company\":{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}}"; // Missing closing brace


        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.prettyPrintJson(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        // Assert
        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code prettyPrintJson} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not a valid JSON.
     */
    @Test
    void testPrettyPrintJson_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.prettyPrintJson(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        // Assert
        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code mergeJson} method with two valid JSON strings.
     * Verifies that the method returns a merged JSON string containing the combined fields of both JSONs.
     */
    @Test
    void testMergeJson_validJsons_mergesSuccessfully() {
        // Arrange
        String json1 = """
                {
                    "company": {
                      "name": "Tech Solutions Inc.",
                      "founded": 1998,
                      "employees": [
                        {
                          "id": 1,
                          "name": "Alice Smith",
                          "title": "CEO",
                          "contact": {
                            "email": "alice.smith@techsolutions.com",
                            "phone": "+1-555-1234",
                            "address": {
                              "street": "123 Tech Lane",
                              "city": "Innovate City",
                              "state": "CA",
                              "zip": "90001",
                              "coordinates": {
                                "lat": 34.0522,
                                "lng": -118.2437
                              }
                            }
                          },
                          "projects": [
                            {
                              "projectId": "p-101",
                              "name": "AI Assistant",
                              "status": "active",
                              "budget": 1200000.5,
                              "milestones": [
                                {
                                  "milestoneId": "m-001",
                                  "description": "Initial Research",
                                  "dueDate": "2025-06-30",
                                  "completed": true
                                },
                                {
                                  "milestoneId": "m-002",
                                  "description": "Prototype Development",
                                  "dueDate": "2025-09-15",
                                  "completed": false
                                }
                              ]
                            }
                          ]
                        },
                        {
                          "id": 2,
                          "name": "Bob Johnson",
                          "title": "CTO",
                          "contact": {
                            "email": "bob.johnson@techsolutions.com",
                            "phone": "+1-555-5678",
                            "address": {
                              "street": "456 Dev Avenue",
                              "city": "Code Town",
                              "state": "NY",
                              "zip": "10001",
                              "coordinates": {
                                "lat": 40.7128,
                                "lng": -74.006
                              }
                            }
                          },
                          "projects": []
                        }
                      ],
                      "departments": {
                        "engineering": {
                          "head": "Bob Johnson",
                          "teams": [
                            {
                              "teamName": "Backend",
                              "members": [
                                "Charlie",
                                "Dana",
                                "Eli"
                              ],
                              "technologies": [
                                "Node.js",
                                "Python",
                                "PostgreSQL"
                              ]
                            },
                            {
                              "teamName": "Frontend",
                              "members": [
                                "Fiona",
                                "George"
                              ],
                              "technologies": [
                                "React",
                                "Vue",
                                "HTML5",
                                "CSS3"
                              ]
                            }
                          ]
                        },
                        "hr": {
                          "head": "Grace Lee",
                          "teams": []
                        }
                      },
                      "offices": [
                        {
                          "location": "San Francisco",
                          "capacity": 50,
                          "openSince": "2010-05-15",
                          "facilities": {
                            "cafeteria": true,
                            "gym": false,
                            "parking": {
                              "available": true,
                              "spots": 20
                            }
                          }
                        },
                        {
                          "location": "New York",
                          "capacity": 30,
                          "openSince": "2015-08-20",
                          "facilities": {
                            "cafeteria": true,
                            "gym": true,
                            "parking": {
                              "available": false,
                              "spots": 0
                            }
                          }
                        }
                      ],
                      "financials": {
                        "fiscalYear": 2024,
                        "revenue": 5600000,
                        "expenses": 3200000,
                        "profit": 2400000,
                        "currency": "USD",
                        "investors": [
                          {
                            "name": "Capital Ventures",
                            "investment": 1000000,
                            "equity": 10
                          },
                          {
                            "name": "Tech Angels",
                            "investment": 500000,
                            "equity": 5
                          }
                        ]
                      }
                    }
                  }
                """;
        String json2 = """
                {
                    "company": {
                      "name": "Future Innovations Ltd.",
                      "founded": 2005,
                      "employees": [
                        {
                          "id": 1,
                          "name": "Jane Doe",
                          "title": "Founder",
                          "contact": {
                            "email": "jane.doe@futureinnovations.com",
                            "phone": "+44-20-9876",
                            "address": {
                              "street": "789 Future Blvd",
                              "city": "Tech Haven",
                              "state": "TX",
                              "zip": "73301",
                              "coordinates": {
                                "lat": 30.2672,
                                "lng": -97.7431
                              }
                            }
                          },
                          "projects": [
                            {
                              "projectId": "p-201",
                              "name": "Smart Home Automation",
                              "status": "completed",
                              "budget": 2000000,
                              "milestones": [
                                {
                                  "milestoneId": "m-101",
                                  "description": "Market Research",
                                  "dueDate": "2024-05-15",
                                  "completed": true
                                },
                                {
                                  "milestoneId": "m-102",
                                  "description": "Prototype Deployment",
                                  "dueDate": "2024-12-01",
                                  "completed": true
                                }
                              ]
                            }
                          ]
                        },
                        {
                          "id": 2,
                          "name": "Mark Twain",
                          "title": "COO",
                          "contact": {
                            "email": "mark.twain@futureinnovations.com",
                            "phone": "+44-20-5432",
                            "address": {
                              "street": "321 Innovation Road",
                              "city": "Tech Haven",
                              "state": "TX",
                              "zip": "73301",
                              "coordinates": {
                                "lat": 30.2672,
                                "lng": -97.7431
                              }
                            }
                          },
                          "projects": []
                        }
                      ],
                      "departments": {
                        "engineering": {
                          "head": "Mark Twain",
                          "teams": [
                            {
                              "teamName": "Cloud",
                              "members": [
                                "Liam",
                                "Emma",
                                "Olivia"
                              ],
                              "technologies": [
                                "AWS",
                                "Azure",
                                "Google Cloud"
                              ]
                            },
                            {
                              "teamName": "IoT",
                              "members": [
                                "Noah",
                                "Ava"
                              ],
                              "technologies": [
                                "Raspberry Pi",
                                "Arduino",
                                "MQTT"
                              ]
                            }
                          ]
                        },
                        "hr": {
                          "head": "Sophie Turner",
                          "teams": []
                        }
                      },
                      "offices": [
                        {
                          "location": "Austin",
                          "capacity": 40,
                          "openSince": "2018-03-10",
                          "facilities": {
                            "cafeteria": true,
                            "gym": true,
                            "parking": {
                              "available": true,
                              "spots": 25
                            }
                          }
                        },
                        {
                          "location": "London",
                          "capacity": 20,
                          "openSince": "2021-11-05",
                          "facilities": {
                            "cafeteria": true,
                            "gym": false,
                            "parking": {
                              "available": true,
                              "spots": 10
                            }
                          }
                        }
                      ],
                      "financials": {
                        "fiscalYear": 2024,
                        "revenue": 8000000,
                        "expenses": 4500000,
                        "profit": 3500000,
                        "currency": "GBP",
                        "investors": [
                          {
                            "name": "Global Tech Fund",
                            "investment": 2000000,
                            "equity": 15
                          },
                          {
                            "name": "Startup Angels",
                            "investment": 750000,
                            "equity": 7
                          }
                        ]
                      }
                    }
                  }
                """;

        List<String> jsons = List.of(json1, json2);

        String expectedMergedJson = "[{\"company\":{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}}},{\"company\":{\"name\":\"Future Innovations Ltd.\",\"founded\":2005,\"employees\":[{\"id\":1,\"name\":\"Jane Doe\",\"title\":\"Founder\",\"contact\":{\"email\":\"jane.doe@futureinnovations.com\",\"phone\":\"+44-20-9876\",\"address\":{\"street\":\"789 Future Blvd\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[{\"projectId\":\"p-201\",\"name\":\"Smart Home Automation\",\"status\":\"completed\",\"budget\":2000000,\"milestones\":[{\"milestoneId\":\"m-101\",\"description\":\"Market Research\",\"dueDate\":\"2024-05-15\",\"completed\":true},{\"milestoneId\":\"m-102\",\"description\":\"Prototype Deployment\",\"dueDate\":\"2024-12-01\",\"completed\":true}]}]},{\"id\":2,\"name\":\"Mark Twain\",\"title\":\"COO\",\"contact\":{\"email\":\"mark.twain@futureinnovations.com\",\"phone\":\"+44-20-5432\",\"address\":{\"street\":\"321 Innovation Road\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Mark Twain\",\"teams\":[{\"teamName\":\"Cloud\",\"members\":[\"Liam\",\"Emma\",\"Olivia\"],\"technologies\":[\"AWS\",\"Azure\",\"Google Cloud\"]},{\"teamName\":\"IoT\",\"members\":[\"Noah\",\"Ava\"],\"technologies\":[\"Raspberry Pi\",\"Arduino\",\"MQTT\"]}]},\"hr\":{\"head\":\"Sophie Turner\",\"teams\":[]}},\"offices\":[{\"location\":\"Austin\",\"capacity\":40,\"openSince\":\"2018-03-10\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":true,\"spots\":25}}},{\"location\":\"London\",\"capacity\":20,\"openSince\":\"2021-11-05\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":10}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":8000000,\"expenses\":4500000,\"profit\":3500000,\"currency\":\"GBP\",\"investors\":[{\"name\":\"Global Tech Fund\",\"investment\":2000000,\"equity\":15},{\"name\":\"Startup Angels\",\"investment\":750000,\"equity\":7}]}}}]";

        // Act
        String mergedJson = jsonService.mergeJson(jsons);

        // Assert
        assertEquals(expectedMergedJson, mergedJson, "Expected two JSONs are merged into one JSON (mergedJson), and it is identical to expectedMergedJson");
    }

    /**
     * Tests the {@code mergeJson} method with an invalid first JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the first input is invalid.
     */
    @Test
    void testMergeJson_invalidFirstJson_throwsException() {
        // Arrange
        String invalidJson = "{\"name\":\"John\""; // Missing closing brace
        String validJson = "{\"city\":\"New York\"}";

        List<String> jsons = List.of(invalidJson, validJson);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.mergeJson(jsons),
                "Expected invalid first JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code mergeJson} method with an invalid second JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the second input is invalid.
     */
    @Test
    void testMergeJson_invalidSecondJson_throwsException() {
        // Arrange
        String validJson = "{\"name\":\"John\"}";
        String invalidJson = "{\"city\":\"New York\""; // Missing closing brace

        List<String> jsons = List.of(validJson, invalidJson);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.mergeJson(jsons),
                "Expected invalid second JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code mergeJson} method with two empty JSON objects.
     * Verifies that the method returns an empty JSON object, indicating a successful merge with no content.
     */
    @Test
    void testMergeJson_emptyJsonObjects_mergesSuccessfully() {
        // Arrange
        String json1 = "{}";
        String json2 = "{}";

        List<String> jsons = List.of(json1, json2);

        // Act
        String mergedJson = jsonService.mergeJson(jsons);

        // Assert
        assertEquals("[{},{}]", mergedJson, "Expected merged JSON to be an empty object");
    }

    /**
     * Tests the {@code validateJsonWithSchema} method with a valid JSON string and a valid JSON schema.
     * Verifies that the method returns {@code true}, indicating the JSON conforms to the provided schema.
     */
    @Test
    void testValidateJsonWithSchema_validJsonAndSchema_returnsTrue() {
        // Arrange
        String json = "{\"name\": \"John\", \"age\": 30}";
        String schema = """
                {
                  "$schema": "http://json-schema.org/draft-04/schema#",
                  "type": "object",
                  "properties": {
                    "name": { "type": "string" },
                    "age": { "type": "number" }
                  },
                  "required": ["name", "age"]
                }
                """;

        // Act
        boolean isValid = jsonService.validateJsonWithSchema(json, schema);

        // Assert
        assertTrue(isValid, "Expected JSON to be valid against schema");
    }

    /**
     * Tests the {@code validateJsonWithSchema} method when the JSON is missing a required field specified in the schema.
     * Verifies that the method returns {@code false} indicating the JSON does not conform to the provided schema.
     */
    @Test
    void testValidateJsonWithSchema_jsonMissingRequiredField_returnsFalse() {
        // Arrange
        String json = "{\"name\": \"John\"}"; // Missing "age"
        String schema = """
                {
                  "$schema": "http://json-schema.org/draft-04/schema#",
                  "type": "object",
                  "properties": {
                    "name": { "type": "string" },
                    "age": { "type": "number" }
                  },
                  "required": ["name", "age"]
                }
                """;

        // Act
        boolean isValid = jsonService.validateJsonWithSchema(json, schema);

        // Assert
        assertFalse(isValid, "Expected JSON missing required field to be invalid");
    }

    /**
     * Tests the {@code validateJsonWithSchema} method with an invalid JSON string and a valid JSON schema.
     * Verifies that the method throws an {@code IllegalArgumentException} because the JSON is invalid and cannot be parsed.
     */
    @Test
    void testValidateJsonWithSchema_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{\"name\": \"John\", \"age\": 30"; // Missing closing brace
        String schema = """
                {
                  "$schema": "http://json-schema.org/draft-04/schema#",
                  "type": "object",
                  "properties": {
                    "name": { "type": "string" },
                    "age": { "type": "number" }
                  },
                  "required": ["name", "age"]
                }
                """;

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.validateJsonWithSchema(invalidJson, schema),
                "Expected invalid JSON to throw IllegalArgumentException");
        assertTrue(ex.getMessage().contains("Invalid JSON or schema"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code validateJsonWithSchema} method with a valid JSON string and an invalid JSON schema.
     * Verifies that the method throws an {@code IllegalArgumentException} because the provided schema is invalid.
     */
    @Test
    void testValidateJsonWithSchema_invalidSchema_throwsException() {
        // Arrange
        String json = "{\"name\": \"John\", \"age\": 30}";
        String invalidSchema = "{ invalid json schema }"; // Clearly invalid JSON

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.validateJsonWithSchema(json, invalidSchema),
                "Expected invalid schema to throw IllegalArgumentException");
        assertTrue(ex.getMessage().contains("Invalid JSON or schema"), "Expected error message to indicate invalid schema");
    }

    /**
     * Tests the {@code minifyJson} method with a valid JSON string.
     * Verifies that the method returns a minified JSON string without unnecessary spaces or line breaks.
     */
    @Test
    void testMinifyJson_validJson_returnsMinifiedJson() {
        // Arrange
        String prettyJson = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                  }
                """;
        String expectedMinifiedJson = "{\"companies\":[{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}},{\"name\":\"Future Innovations Ltd.\",\"founded\":2005,\"employees\":[{\"id\":1,\"name\":\"Jane Doe\",\"title\":\"Founder\",\"contact\":{\"email\":\"jane.doe@futureinnovations.com\",\"phone\":\"+44-20-9876\",\"address\":{\"street\":\"789 Future Blvd\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[{\"projectId\":\"p-201\",\"name\":\"Smart Home Automation\",\"status\":\"completed\",\"budget\":2000000,\"milestones\":[{\"milestoneId\":\"m-101\",\"description\":\"Market Research\",\"dueDate\":\"2024-05-15\",\"completed\":true},{\"milestoneId\":\"m-102\",\"description\":\"Prototype Deployment\",\"dueDate\":\"2024-12-01\",\"completed\":true}]}]},{\"id\":2,\"name\":\"Mark Twain\",\"title\":\"COO\",\"contact\":{\"email\":\"mark.twain@futureinnovations.com\",\"phone\":\"+44-20-5432\",\"address\":{\"street\":\"321 Innovation Road\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Mark Twain\",\"teams\":[{\"teamName\":\"Cloud\",\"members\":[\"Liam\",\"Emma\",\"Olivia\"],\"technologies\":[\"AWS\",\"Azure\",\"Google Cloud\"]},{\"teamName\":\"IoT\",\"members\":[\"Noah\",\"Ava\"],\"technologies\":[\"Raspberry Pi\",\"Arduino\",\"MQTT\"]}]},\"hr\":{\"head\":\"Sophie Turner\",\"teams\":[]}},\"offices\":[{\"location\":\"Austin\",\"capacity\":40,\"openSince\":\"2018-03-10\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":true,\"spots\":25}}},{\"location\":\"London\",\"capacity\":20,\"openSince\":\"2021-11-05\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":10}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":8000000,\"expenses\":4500000,\"profit\":3500000,\"currency\":\"GBP\",\"investors\":[{\"name\":\"Global Tech Fund\",\"investment\":2000000,\"equity\":15},{\"name\":\"Startup Angels\",\"investment\":750000,\"equity\":7}]}}]}";

        // Act
        String minifiedJson = jsonService.minifyJson(prettyJson);

        // Assert
        assertEquals(expectedMinifiedJson, minifiedJson, "Expected minified JSON output");
    }

    /**
     * Tests the {@code minifyJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input JSON cannot be parsed.
     */
    @Test
    void testMinifyJson_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{\"name\": \"John\", \"age\": 30"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.minifyJson(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code minifyJson} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testMinifyJson_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.minifyJson(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code sortJson} method with a JSON object input.
     * Verifies that the method returns a JSON string with the object's keys sorted in alphabetical order.
     */
    @Test
    void testSortJson_objectKeysSortedAlphabetically() {
        // Arrange
        String json = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                  }
                """;

        String sortByField = "name";

        String expectedSortedJson = """
                {
                  "companies" : [ {
                    "name" : "Future Innovations Ltd.",
                    "founded" : 2005,
                    "employees" : [ {
                      "id" : 1,
                      "name" : "Jane Doe",
                      "title" : "Founder",
                      "contact" : {
                        "email" : "jane.doe@futureinnovations.com",
                        "phone" : "+44-20-9876",
                        "address" : {
                          "street" : "789 Future Blvd",
                          "city" : "Tech Haven",
                          "state" : "TX",
                          "zip" : "73301",
                          "coordinates" : {
                            "lat" : 30.2672,
                            "lng" : -97.7431
                          }
                        }
                      },
                      "projects" : [ {
                        "projectId" : "p-201",
                        "name" : "Smart Home Automation",
                        "status" : "completed",
                        "budget" : 2000000,
                        "milestones" : [ {
                          "milestoneId" : "m-101",
                          "description" : "Market Research",
                          "dueDate" : "2024-05-15",
                          "completed" : true
                        }, {
                          "milestoneId" : "m-102",
                          "description" : "Prototype Deployment",
                          "dueDate" : "2024-12-01",
                          "completed" : true
                        } ]
                      } ]
                    }, {
                      "id" : 2,
                      "name" : "Mark Twain",
                      "title" : "COO",
                      "contact" : {
                        "email" : "mark.twain@futureinnovations.com",
                        "phone" : "+44-20-5432",
                        "address" : {
                          "street" : "321 Innovation Road",
                          "city" : "Tech Haven",
                          "state" : "TX",
                          "zip" : "73301",
                          "coordinates" : {
                            "lat" : 30.2672,
                            "lng" : -97.7431
                          }
                        }
                      },
                      "projects" : [ ]
                    } ],
                    "departments" : {
                      "engineering" : {
                        "head" : "Mark Twain",
                        "teams" : [ {
                          "teamName" : "Cloud",
                          "members" : [ "Liam", "Emma", "Olivia" ],
                          "technologies" : [ "AWS", "Azure", "Google Cloud" ]
                        }, {
                          "teamName" : "IoT",
                          "members" : [ "Noah", "Ava" ],
                          "technologies" : [ "Raspberry Pi", "Arduino", "MQTT" ]
                        } ]
                      },
                      "hr" : {
                        "head" : "Sophie Turner",
                        "teams" : [ ]
                      }
                    },
                    "offices" : [ {
                      "location" : "Austin",
                      "capacity" : 40,
                      "openSince" : "2018-03-10",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : true,
                        "parking" : {
                          "available" : true,
                          "spots" : 25
                        }
                      }
                    }, {
                      "location" : "London",
                      "capacity" : 20,
                      "openSince" : "2021-11-05",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : false,
                        "parking" : {
                          "available" : true,
                          "spots" : 10
                        }
                      }
                    } ],
                    "financials" : {
                      "fiscalYear" : 2024,
                      "revenue" : 8000000,
                      "expenses" : 4500000,
                      "profit" : 3500000,
                      "currency" : "GBP",
                      "investors" : [ {
                        "name" : "Global Tech Fund",
                        "investment" : 2000000,
                        "equity" : 15
                      }, {
                        "name" : "Startup Angels",
                        "investment" : 750000,
                        "equity" : 7
                      } ]
                    }
                  }, {
                    "name" : "Tech Solutions Inc.",
                    "founded" : 1998,
                    "employees" : [ {
                      "id" : 1,
                      "name" : "Alice Smith",
                      "title" : "CEO",
                      "contact" : {
                        "email" : "alice.smith@techsolutions.com",
                        "phone" : "+1-555-1234",
                        "address" : {
                          "street" : "123 Tech Lane",
                          "city" : "Innovate City",
                          "state" : "CA",
                          "zip" : "90001",
                          "coordinates" : {
                            "lat" : 34.0522,
                            "lng" : -118.2437
                          }
                        }
                      },
                      "projects" : [ {
                        "projectId" : "p-101",
                        "name" : "AI Assistant",
                        "status" : "active",
                        "budget" : 1200000.5,
                        "milestones" : [ {
                          "milestoneId" : "m-001",
                          "description" : "Initial Research",
                          "dueDate" : "2025-06-30",
                          "completed" : true
                        }, {
                          "milestoneId" : "m-002",
                          "description" : "Prototype Development",
                          "dueDate" : "2025-09-15",
                          "completed" : false
                        } ]
                      } ]
                    }, {
                      "id" : 2,
                      "name" : "Bob Johnson",
                      "title" : "CTO",
                      "contact" : {
                        "email" : "bob.johnson@techsolutions.com",
                        "phone" : "+1-555-5678",
                        "address" : {
                          "street" : "456 Dev Avenue",
                          "city" : "Code Town",
                          "state" : "NY",
                          "zip" : "10001",
                          "coordinates" : {
                            "lat" : 40.7128,
                            "lng" : -74.006
                          }
                        }
                      },
                      "projects" : [ ]
                    } ],
                    "departments" : {
                      "engineering" : {
                        "head" : "Bob Johnson",
                        "teams" : [ {
                          "teamName" : "Backend",
                          "members" : [ "Charlie", "Dana", "Eli" ],
                          "technologies" : [ "Node.js", "Python", "PostgreSQL" ]
                        }, {
                          "teamName" : "Frontend",
                          "members" : [ "Fiona", "George" ],
                          "technologies" : [ "React", "Vue", "HTML5", "CSS3" ]
                        } ]
                      },
                      "hr" : {
                        "head" : "Grace Lee",
                        "teams" : [ ]
                      }
                    },
                    "offices" : [ {
                      "location" : "San Francisco",
                      "capacity" : 50,
                      "openSince" : "2010-05-15",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : false,
                        "parking" : {
                          "available" : true,
                          "spots" : 20
                        }
                      }
                    }, {
                      "location" : "New York",
                      "capacity" : 30,
                      "openSince" : "2015-08-20",
                      "facilities" : {
                        "cafeteria" : true,
                        "gym" : true,
                        "parking" : {
                          "available" : false,
                          "spots" : 0
                        }
                      }
                    } ],
                    "financials" : {
                      "fiscalYear" : 2024,
                      "revenue" : 5600000,
                      "expenses" : 3200000,
                      "profit" : 2400000,
                      "currency" : "USD",
                      "investors" : [ {
                        "name" : "Capital Ventures",
                        "investment" : 1000000,
                        "equity" : 10
                      }, {
                        "name" : "Tech Angels",
                        "investment" : 500000,
                        "equity" : 5
                      } ]
                    }
                  } ]
                }""";

        // Act
        String sortedJson = jsonService.sortJson(json, sortByField);

        // Assert
        assertEquals(expectedSortedJson, sortedJson, "Expected keys to be sorted alphabetically");
    }

    /**
     * Tests the {@code sortJson} method with a JSON array input, specifying a field to sort by.
     * Verifies that the method returns a JSON array sorted by the given field's value.
     */
    @Test
    void testSortJson_arraySortedByField() throws Exception {
        // Arrange
        String json = """
                [
                  { "name": "John", "age": 30 },
                  { "name": "Alice", "age": 25 },
                  { "name": "Bob", "age": 28 }
                ]
                """;
        String expectedSortedJson = """
                [
                  { "name": "Alice", "age": 25 },
                  { "name": "Bob", "age": 28 },
                  { "name": "John", "age": 30 }
                ]
                """;

        // Act
        String sortedJson = jsonService.sortJson(json, "name");

        // Assert
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.readTree(expectedSortedJson);
        JsonNode actualNode = mapper.readTree(sortedJson);

        assertEquals(expectedNode, actualNode, "Expected JSON array to be sorted by 'name' field");
    }

    /**
     * Tests the {@code sortJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input JSON cannot be parsed.
     */
    @Test
    void testSortJson_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"John\", \"age\": 30"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.sortJson(invalidJson, null),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code sortJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testSortJson_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.sortJson(emptyJson, null),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code findInJson} method with a valid JSON string and a search term.
     * Verifies that the method returns a list of JSON paths where the search term matches either a key or a value.
     */
    @Test
    void testFindInJson_findMatchingKeysAndValues() {
        // Arrange
        String json = """
                {
                  "user": {
                    "name": "Alice",
                    "address": {
                      "city": "Wonderland",
                      "zip": "12345"
                    }
                  },
                  "tags": ["Alice", "friend"]
                }
                """;

        // Act
        List<String> matches = jsonService.findInJson(json, "Alice");

        // Assert
        assertTrue(matches.contains("$.user.name (value)"), "Expected to find 'Alice' as value in $.user.name");
        assertTrue(matches.contains("$.tags[0] (value)"), "Expected to find 'Alice' in tags array");
        assertEquals(2, matches.size(), "Expected 2 matches for 'Alice'");
    }

    /**
     * Tests the {@code findInJson} method with a valid JSON string and a search term.
     * Verifies that the method returns JSON paths where the search term matches a key.
     */
    @Test
    void testFindInJson_findMatchingKey() {
        // Arrange
        String json = """
                {
                  "search": "value",
                  "nested": {
                    "search": "anotherValue"
                  }
                }
                """;

        // Act
        List<String> matches = jsonService.findInJson(json, "search");

        // Assert
        assertTrue(matches.contains("$.search (key)"), "Expected to find key 'search' at root level");
        assertTrue(matches.contains("$.nested.search (key)"), "Expected to find key 'search' in nested object");
        assertEquals(2, matches.size(), "Expected 2 matches for key 'search'");
    }

    /**
     * Tests the {@code findInJson} method with a valid JSON string and a search term that does not exist in the JSON.
     * Verifies that the method returns an empty list, indicating no matches were found.
     */
    @Test
    void testFindInJson_noMatch_returnsEmptyList() {
        // Arrange
        String json = """
                {
                  "user": {
                    "name": "Alice",
                    "age": 30
                  }
                }
                """;

        // Act
        List<String> matches = jsonService.findInJson(json, "Bob");

        // Assert
        assertTrue(matches.isEmpty(), "Expected no matches for 'Bob'");
    }

    /**
     * Tests the {@code findInJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testFindInJson_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\" "; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.findInJson(invalidJson, "Alice"),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code findInJson} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testFindInJson_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.findInJson(emptyJson, "Alice"),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToCsv} method with a valid JSON array of objects.
     * Verifies that the method returns a CSV string representation of the JSON data.
     */
    @Test
    void testConvertJsonToCsv_validjson_returnsCsv() {
        // Arrange
        String json = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                  }
                """;

        String expectedCsv = """
                name,founded,employees,departments,offices,financials
                Tech Solutions Inc.,1998,,,,
                Future Innovations Ltd.,2005,,,,""";

        // Act
        String csv = jsonService.convertJsonToCsv(json).replaceAll("\\r\\n?", "\n").trim();

        // Assert
        assertEquals(expectedCsv, csv, "Expected valid CSV output");
    }

    /**
     * Tests the {@code convertJsonToCsv} method with a JSON object that is not an array.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not an array of objects.
     */
    @Test
    void testConvertJsonToCsv_jsonNotArray_throwsException() {
        // Arrange
        String json = "{ \"name\": \"Alice\", \"age\": 25 }";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToCsv(json),
                "Expected JSON not being array to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("array of objects"), "Expected error to mention array of objects");
    }

    /**
     * Tests the {@code convertJsonToCsv} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input cannot be parsed as JSON.
     */
    @Test
    void testConvertJsonToCsv_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\", \"age\": 25"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToCsv(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToCsv} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testConvertJsonToCsv_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToCsv(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertCsvToJson} method with a valid CSV string.
     * Verifies that the method returns a JSON array representation of the CSV data.
     */
    @Test
    void testConvertCsvToJson_validCsv_returnsjson() throws Exception {
        // Arrange
        String csv = """
                name,age
                Alice,25
                Bob,30
                """;
        String expectedJson = """
                [
                  {
                    "name" : "Alice",
                    "age" : "25"
                  },
                  {
                    "name" : "Bob",
                    "age" : "30"
                  }
                ]
                """;

        // Act
        String json = jsonService.convertCsvToJson(csv);

        // Assert: Compare as JSON trees for consistent formatting
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.readTree(expectedJson);
        JsonNode actualNode = mapper.readTree(json);

        assertEquals(expectedNode, actualNode, "Expected JSON array converted from CSV");
    }

    /**
     * Tests the {@code convertCsvToJson} method with a CSV string that only contains headers and no data rows.
     * Verifies that the method returns an empty JSON array, indicating no data rows were found in the CSV.
     */
    @Test
    void testConvertCsvToJson_emptyCsv_returnsEmptyArray() throws Exception {
        // Arrange
        String csv = "name,age\n";

        // Act
        String json = jsonService.convertCsvToJson(csv);

        // Assert: Should be an empty array
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(json);

        assertTrue(actualNode.isArray() && actualNode.isEmpty(), "Expected empty JSON array for empty CSV rows");
    }

    /**
     * Tests the {@code convertCsvToJson} method with an invalid CSV string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the CSV format is not valid.
     */
    @Test
    void testConvertCsvToJson_invalidCsv_throwsException() {
        // Arrange
        String invalidCsv = "\"name\",\"age\"\n\"Alice\""; // Missing second field

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertCsvToJson(invalidCsv),
                "Expected invalid CSV to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid CSV"), "Expected error message to indicate invalid CSV");
    }

    /**
     * Tests the {@code convertCsvToJson} method with an empty input string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is empty and not valid CSV.
     */
    @Test
    void testConvertCsvToJson_emptyInput_throwsException() {
        // Arrange
        String emptyCsv = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertCsvToJson(emptyCsv),
                "Expected empty CSV to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid CSV"), "Expected error message to indicate invalid CSV");
    }

    /**
     * Tests the {@code convertJsonToXml} method with a valid JSON string.
     * Verifies that the method returns an XML string representation of the JSON data.
     */
    @Test
    void testConvertJsonToXml_validJson_returnsXml() throws Exception {
        // Arrange
        String json = """
                {
                    "companies": [
                      {
                        "name": "Tech Solutions Inc.",
                        "founded": 1998,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Alice Smith",
                            "title": "CEO",
                            "contact": {
                              "email": "alice.smith@techsolutions.com",
                              "phone": "+1-555-1234",
                              "address": {
                                "street": "123 Tech Lane",
                                "city": "Innovate City",
                                "state": "CA",
                                "zip": "90001",
                                "coordinates": {
                                  "lat": 34.0522,
                                  "lng": -118.2437
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-101",
                                "name": "AI Assistant",
                                "status": "active",
                                "budget": 1200000.5,
                                "milestones": [
                                  {
                                    "milestoneId": "m-001",
                                    "description": "Initial Research",
                                    "dueDate": "2025-06-30",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-002",
                                    "description": "Prototype Development",
                                    "dueDate": "2025-09-15",
                                    "completed": false
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Bob Johnson",
                            "title": "CTO",
                            "contact": {
                              "email": "bob.johnson@techsolutions.com",
                              "phone": "+1-555-5678",
                              "address": {
                                "street": "456 Dev Avenue",
                                "city": "Code Town",
                                "state": "NY",
                                "zip": "10001",
                                "coordinates": {
                                  "lat": 40.7128,
                                  "lng": -74.006
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Bob Johnson",
                            "teams": [
                              {
                                "teamName": "Backend",
                                "members": [
                                  "Charlie",
                                  "Dana",
                                  "Eli"
                                ],
                                "technologies": [
                                  "Node.js",
                                  "Python",
                                  "PostgreSQL"
                                ]
                              },
                              {
                                "teamName": "Frontend",
                                "members": [
                                  "Fiona",
                                  "George"
                                ],
                                "technologies": [
                                  "React",
                                  "Vue",
                                  "HTML5",
                                  "CSS3"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Grace Lee",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "San Francisco",
                            "capacity": 50,
                            "openSince": "2010-05-15",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 20
                              }
                            }
                          },
                          {
                            "location": "New York",
                            "capacity": 30,
                            "openSince": "2015-08-20",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": false,
                                "spots": 0
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 5600000,
                          "expenses": 3200000,
                          "profit": 2400000,
                          "currency": "USD",
                          "investors": [
                            {
                              "name": "Capital Ventures",
                              "investment": 1000000,
                              "equity": 10
                            },
                            {
                              "name": "Tech Angels",
                              "investment": 500000,
                              "equity": 5
                            }
                          ]
                        }
                      },
                      {
                        "name": "Future Innovations Ltd.",
                        "founded": 2005,
                        "employees": [
                          {
                            "id": 1,
                            "name": "Jane Doe",
                            "title": "Founder",
                            "contact": {
                              "email": "jane.doe@futureinnovations.com",
                              "phone": "+44-20-9876",
                              "address": {
                                "street": "789 Future Blvd",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": [
                              {
                                "projectId": "p-201",
                                "name": "Smart Home Automation",
                                "status": "completed",
                                "budget": 2000000,
                                "milestones": [
                                  {
                                    "milestoneId": "m-101",
                                    "description": "Market Research",
                                    "dueDate": "2024-05-15",
                                    "completed": true
                                  },
                                  {
                                    "milestoneId": "m-102",
                                    "description": "Prototype Deployment",
                                    "dueDate": "2024-12-01",
                                    "completed": true
                                  }
                                ]
                              }
                            ]
                          },
                          {
                            "id": 2,
                            "name": "Mark Twain",
                            "title": "COO",
                            "contact": {
                              "email": "mark.twain@futureinnovations.com",
                              "phone": "+44-20-5432",
                              "address": {
                                "street": "321 Innovation Road",
                                "city": "Tech Haven",
                                "state": "TX",
                                "zip": "73301",
                                "coordinates": {
                                  "lat": 30.2672,
                                  "lng": -97.7431
                                }
                              }
                            },
                            "projects": []
                          }
                        ],
                        "departments": {
                          "engineering": {
                            "head": "Mark Twain",
                            "teams": [
                              {
                                "teamName": "Cloud",
                                "members": [
                                  "Liam",
                                  "Emma",
                                  "Olivia"
                                ],
                                "technologies": [
                                  "AWS",
                                  "Azure",
                                  "Google Cloud"
                                ]
                              },
                              {
                                "teamName": "IoT",
                                "members": [
                                  "Noah",
                                  "Ava"
                                ],
                                "technologies": [
                                  "Raspberry Pi",
                                  "Arduino",
                                  "MQTT"
                                ]
                              }
                            ]
                          },
                          "hr": {
                            "head": "Sophie Turner",
                            "teams": []
                          }
                        },
                        "offices": [
                          {
                            "location": "Austin",
                            "capacity": 40,
                            "openSince": "2018-03-10",
                            "facilities": {
                              "cafeteria": true,
                              "gym": true,
                              "parking": {
                                "available": true,
                                "spots": 25
                              }
                            }
                          },
                          {
                            "location": "London",
                            "capacity": 20,
                            "openSince": "2021-11-05",
                            "facilities": {
                              "cafeteria": true,
                              "gym": false,
                              "parking": {
                                "available": true,
                                "spots": 10
                              }
                            }
                          }
                        ],
                        "financials": {
                          "fiscalYear": 2024,
                          "revenue": 8000000,
                          "expenses": 4500000,
                          "profit": 3500000,
                          "currency": "GBP",
                          "investors": [
                            {
                              "name": "Global Tech Fund",
                              "investment": 2000000,
                              "equity": 15
                            },
                            {
                              "name": "Startup Angels",
                              "investment": 750000,
                              "equity": 7
                            }
                          ]
                        }
                      }
                    ]
                  }
                """;

        String expectedXml = """
                <ObjectNode>
                  <companies>
                    <name>Tech Solutions Inc.</name>
                    <founded>1998</founded>
                    <employees>
                      <id>1</id>
                      <name>Alice Smith</name>
                      <title>CEO</title>
                      <contact>
                        <email>alice.smith@techsolutions.com</email>
                        <phone>+1-555-1234</phone>
                        <address>
                          <street>123 Tech Lane</street>
                          <city>Innovate City</city>
                          <state>CA</state>
                          <zip>90001</zip>
                          <coordinates>
                            <lat>34.0522</lat>
                            <lng>-118.2437</lng>
                          </coordinates>
                        </address>
                      </contact>
                      <projects>
                        <projectId>p-101</projectId>
                        <name>AI Assistant</name>
                        <status>active</status>
                        <budget>1200000.5</budget>
                        <milestones>
                          <milestoneId>m-001</milestoneId>
                          <description>Initial Research</description>
                          <dueDate>2025-06-30</dueDate>
                          <completed>true</completed>
                        </milestones>
                        <milestones>
                          <milestoneId>m-002</milestoneId>
                          <description>Prototype Development</description>
                          <dueDate>2025-09-15</dueDate>
                          <completed>false</completed>
                        </milestones>
                      </projects>
                    </employees>
                    <employees>
                      <id>2</id>
                      <name>Bob Johnson</name>
                      <title>CTO</title>
                      <contact>
                        <email>bob.johnson@techsolutions.com</email>
                        <phone>+1-555-5678</phone>
                        <address>
                          <street>456 Dev Avenue</street>
                          <city>Code Town</city>
                          <state>NY</state>
                          <zip>10001</zip>
                          <coordinates>
                            <lat>40.7128</lat>
                            <lng>-74.006</lng>
                          </coordinates>
                        </address>
                      </contact>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Bob Johnson</head>
                        <teams>
                          <teamName>Backend</teamName>
                          <members>Charlie</members>
                          <members>Dana</members>
                          <members>Eli</members>
                          <technologies>Node.js</technologies>
                          <technologies>Python</technologies>
                          <technologies>PostgreSQL</technologies>
                        </teams>
                        <teams>
                          <teamName>Frontend</teamName>
                          <members>Fiona</members>
                          <members>George</members>
                          <technologies>React</technologies>
                          <technologies>Vue</technologies>
                          <technologies>HTML5</technologies>
                          <technologies>CSS3</technologies>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Grace Lee</head>
                      </hr>
                    </departments>
                    <offices>
                      <location>San Francisco</location>
                      <capacity>50</capacity>
                      <openSince>2010-05-15</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>false</gym>
                        <parking>
                          <available>true</available>
                          <spots>20</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <offices>
                      <location>New York</location>
                      <capacity>30</capacity>
                      <openSince>2015-08-20</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>true</gym>
                        <parking>
                          <available>false</available>
                          <spots>0</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>5600000</revenue>
                      <expenses>3200000</expenses>
                      <profit>2400000</profit>
                      <currency>USD</currency>
                      <investors>
                        <name>Capital Ventures</name>
                        <investment>1000000</investment>
                        <equity>10</equity>
                      </investors>
                      <investors>
                        <name>Tech Angels</name>
                        <investment>500000</investment>
                        <equity>5</equity>
                      </investors>
                    </financials>
                  </companies>
                  <companies>
                    <name>Future Innovations Ltd.</name>
                    <founded>2005</founded>
                    <employees>
                      <id>1</id>
                      <name>Jane Doe</name>
                      <title>Founder</title>
                      <contact>
                        <email>jane.doe@futureinnovations.com</email>
                        <phone>+44-20-9876</phone>
                        <address>
                          <street>789 Future Blvd</street>
                          <city>Tech Haven</city>
                          <state>TX</state>
                          <zip>73301</zip>
                          <coordinates>
                            <lat>30.2672</lat>
                            <lng>-97.7431</lng>
                          </coordinates>
                        </address>
                      </contact>
                      <projects>
                        <projectId>p-201</projectId>
                        <name>Smart Home Automation</name>
                        <status>completed</status>
                        <budget>2000000</budget>
                        <milestones>
                          <milestoneId>m-101</milestoneId>
                          <description>Market Research</description>
                          <dueDate>2024-05-15</dueDate>
                          <completed>true</completed>
                        </milestones>
                        <milestones>
                          <milestoneId>m-102</milestoneId>
                          <description>Prototype Deployment</description>
                          <dueDate>2024-12-01</dueDate>
                          <completed>true</completed>
                        </milestones>
                      </projects>
                    </employees>
                    <employees>
                      <id>2</id>
                      <name>Mark Twain</name>
                      <title>COO</title>
                      <contact>
                        <email>mark.twain@futureinnovations.com</email>
                        <phone>+44-20-5432</phone>
                        <address>
                          <street>321 Innovation Road</street>
                          <city>Tech Haven</city>
                          <state>TX</state>
                          <zip>73301</zip>
                          <coordinates>
                            <lat>30.2672</lat>
                            <lng>-97.7431</lng>
                          </coordinates>
                        </address>
                      </contact>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Mark Twain</head>
                        <teams>
                          <teamName>Cloud</teamName>
                          <members>Liam</members>
                          <members>Emma</members>
                          <members>Olivia</members>
                          <technologies>AWS</technologies>
                          <technologies>Azure</technologies>
                          <technologies>Google Cloud</technologies>
                        </teams>
                        <teams>
                          <teamName>IoT</teamName>
                          <members>Noah</members>
                          <members>Ava</members>
                          <technologies>Raspberry Pi</technologies>
                          <technologies>Arduino</technologies>
                          <technologies>MQTT</technologies>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Sophie Turner</head>
                      </hr>
                    </departments>
                    <offices>
                      <location>Austin</location>
                      <capacity>40</capacity>
                      <openSince>2018-03-10</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>true</gym>
                        <parking>
                          <available>true</available>
                          <spots>25</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <offices>
                      <location>London</location>
                      <capacity>20</capacity>
                      <openSince>2021-11-05</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>false</gym>
                        <parking>
                          <available>true</available>
                          <spots>10</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>8000000</revenue>
                      <expenses>4500000</expenses>
                      <profit>3500000</profit>
                      <currency>GBP</currency>
                      <investors>
                        <name>Global Tech Fund</name>
                        <investment>2000000</investment>
                        <equity>15</equity>
                      </investors>
                      <investors>
                        <name>Startup Angels</name>
                        <investment>750000</investment>
                        <equity>7</equity>
                      </investors>
                    </financials>
                  </companies>
                </ObjectNode>
                """;

        // Act
        String xml = jsonService.convertJsonToXml(json);

        // Assert: Check that the XML contains expected elements
        assertEquals(expectedXml, xml);
    }

    /**
     * Tests the {@code convertJsonToXml} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testConvertJsonToXml_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\", \"age\": 25"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToXml(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToXml} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testConvertJsonToXml_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToXml(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertXmlToJson} method with a valid XML string.
     * Verifies that the method returns a JSON string representation of the XML data.
     */
    @Test
    void testConvertXmlToJson_validXml_returnsJson() throws Exception {
        // Arrange
        String xml = """
                <ObjectNode>
                  <companies>
                    <name>Tech Solutions Inc.</name>
                    <founded>1998</founded>
                    <employees>
                      <id>1</id>
                      <name>Alice Smith</name>
                      <title>CEO</title>
                      <contact>
                        <email>alice.smith@techsolutions.com</email>
                        <phone>+1-555-1234</phone>
                        <address>
                          <street>123 Tech Lane</street>
                          <city>Innovate City</city>
                          <state>CA</state>
                          <zip>90001</zip>
                          <coordinates>
                            <lat>34.0522</lat>
                            <lng>-118.2437</lng>
                          </coordinates>
                        </address>
                      </contact>
                      <projects>
                        <projectId>p-101</projectId>
                        <name>AI Assistant</name>
                        <status>active</status>
                        <budget>1200000.5</budget>
                        <milestones>
                          <milestoneId>m-001</milestoneId>
                          <description>Initial Research</description>
                          <dueDate>2025-06-30</dueDate>
                          <completed>true</completed>
                        </milestones>
                        <milestones>
                          <milestoneId>m-002</milestoneId>
                          <description>Prototype Development</description>
                          <dueDate>2025-09-15</dueDate>
                          <completed>false</completed>
                        </milestones>
                      </projects>
                    </employees>
                    <employees>
                      <id>2</id>
                      <name>Bob Johnson</name>
                      <title>CTO</title>
                      <contact>
                        <email>bob.johnson@techsolutions.com</email>
                        <phone>+1-555-5678</phone>
                        <address>
                          <street>456 Dev Avenue</street>
                          <city>Code Town</city>
                          <state>NY</state>
                          <zip>10001</zip>
                          <coordinates>
                            <lat>40.7128</lat>
                            <lng>-74.006</lng>
                          </coordinates>
                        </address>
                      </contact>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Bob Johnson</head>
                        <teams>
                          <teamName>Backend</teamName>
                          <members>Charlie</members>
                          <members>Dana</members>
                          <members>Eli</members>
                          <technologies>Node.js</technologies>
                          <technologies>Python</technologies>
                          <technologies>PostgreSQL</technologies>
                        </teams>
                        <teams>
                          <teamName>Frontend</teamName>
                          <members>Fiona</members>
                          <members>George</members>
                          <technologies>React</technologies>
                          <technologies>Vue</technologies>
                          <technologies>HTML5</technologies>
                          <technologies>CSS3</technologies>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Grace Lee</head>
                      </hr>
                    </departments>
                    <offices>
                      <location>San Francisco</location>
                      <capacity>50</capacity>
                      <openSince>2010-05-15</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>false</gym>
                        <parking>
                          <available>true</available>
                          <spots>20</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <offices>
                      <location>New York</location>
                      <capacity>30</capacity>
                      <openSince>2015-08-20</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>true</gym>
                        <parking>
                          <available>false</available>
                          <spots>0</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>5600000</revenue>
                      <expenses>3200000</expenses>
                      <profit>2400000</profit>
                      <currency>USD</currency>
                      <investors>
                        <name>Capital Ventures</name>
                        <investment>1000000</investment>
                        <equity>10</equity>
                      </investors>
                      <investors>
                        <name>Tech Angels</name>
                        <investment>500000</investment>
                        <equity>5</equity>
                      </investors>
                    </financials>
                  </companies>
                  <companies>
                    <name>Future Innovations Ltd.</name>
                    <founded>2005</founded>
                    <employees>
                      <id>1</id>
                      <name>Jane Doe</name>
                      <title>Founder</title>
                      <contact>
                        <email>jane.doe@futureinnovations.com</email>
                        <phone>+44-20-9876</phone>
                        <address>
                          <street>789 Future Blvd</street>
                          <city>Tech Haven</city>
                          <state>TX</state>
                          <zip>73301</zip>
                          <coordinates>
                            <lat>30.2672</lat>
                            <lng>-97.7431</lng>
                          </coordinates>
                        </address>
                      </contact>
                      <projects>
                        <projectId>p-201</projectId>
                        <name>Smart Home Automation</name>
                        <status>completed</status>
                        <budget>2000000</budget>
                        <milestones>
                          <milestoneId>m-101</milestoneId>
                          <description>Market Research</description>
                          <dueDate>2024-05-15</dueDate>
                          <completed>true</completed>
                        </milestones>
                        <milestones>
                          <milestoneId>m-102</milestoneId>
                          <description>Prototype Deployment</description>
                          <dueDate>2024-12-01</dueDate>
                          <completed>true</completed>
                        </milestones>
                      </projects>
                    </employees>
                    <employees>
                      <id>2</id>
                      <name>Mark Twain</name>
                      <title>COO</title>
                      <contact>
                        <email>mark.twain@futureinnovations.com</email>
                        <phone>+44-20-5432</phone>
                        <address>
                          <street>321 Innovation Road</street>
                          <city>Tech Haven</city>
                          <state>TX</state>
                          <zip>73301</zip>
                          <coordinates>
                            <lat>30.2672</lat>
                            <lng>-97.7431</lng>
                          </coordinates>
                        </address>
                      </contact>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Mark Twain</head>
                        <teams>
                          <teamName>Cloud</teamName>
                          <members>Liam</members>
                          <members>Emma</members>
                          <members>Olivia</members>
                          <technologies>AWS</technologies>
                          <technologies>Azure</technologies>
                          <technologies>Google Cloud</technologies>
                        </teams>
                        <teams>
                          <teamName>IoT</teamName>
                          <members>Noah</members>
                          <members>Ava</members>
                          <technologies>Raspberry Pi</technologies>
                          <technologies>Arduino</technologies>
                          <technologies>MQTT</technologies>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Sophie Turner</head>
                      </hr>
                    </departments>
                    <offices>
                      <location>Austin</location>
                      <capacity>40</capacity>
                      <openSince>2018-03-10</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>true</gym>
                        <parking>
                          <available>true</available>
                          <spots>25</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <offices>
                      <location>London</location>
                      <capacity>20</capacity>
                      <openSince>2021-11-05</openSince>
                      <facilities>
                        <cafeteria>true</cafeteria>
                        <gym>false</gym>
                        <parking>
                          <available>true</available>
                          <spots>10</spots>
                        </parking>
                      </facilities>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>8000000</revenue>
                      <expenses>4500000</expenses>
                      <profit>3500000</profit>
                      <currency>GBP</currency>
                      <investors>
                        <name>Global Tech Fund</name>
                        <investment>2000000</investment>
                        <equity>15</equity>
                      </investors>
                      <investors>
                        <name>Startup Angels</name>
                        <investment>750000</investment>
                        <equity>7</equity>
                      </investors>
                    </financials>
                  </companies>
                </ObjectNode>
                """;

        String expectedJson = """
                {
                   "companies":[
                      {
                         "name":"Tech Solutions Inc.",
                         "founded":"1998",
                         "employees":[
                            {
                               "id":"1",
                               "name":"Alice Smith",
                               "title":"CEO",
                               "contact":{
                                  "email":"alice.smith@techsolutions.com",
                                  "phone":"+1-555-1234",
                                  "address":{
                                     "street":"123 Tech Lane",
                                     "city":"Innovate City",
                                     "state":"CA",
                                     "zip":"90001",
                                     "coordinates":{
                                        "lat":"34.0522",
                                        "lng":"-118.2437"
                                     }
                                  }
                               },
                               "projects":{
                                  "projectId":"p-101",
                                  "name":"AI Assistant",
                                  "status":"active",
                                  "budget":"1200000.5",
                                  "milestones":[
                                     {
                                        "milestoneId":"m-001",
                                        "description":"Initial Research",
                                        "dueDate":"2025-06-30",
                                        "completed":"true"
                                     },
                                     {
                                        "milestoneId":"m-002",
                                        "description":"Prototype Development",
                                        "dueDate":"2025-09-15",
                                        "completed":"false"
                                     }
                                  ]
                               }
                            },
                            {
                               "id":"2",
                               "name":"Bob Johnson",
                               "title":"CTO",
                               "contact":{
                                  "email":"bob.johnson@techsolutions.com",
                                  "phone":"+1-555-5678",
                                  "address":{
                                     "street":"456 Dev Avenue",
                                     "city":"Code Town",
                                     "state":"NY",
                                     "zip":"10001",
                                     "coordinates":{
                                        "lat":"40.7128",
                                        "lng":"-74.006"
                                     }
                                  }
                               }
                            }
                         ],
                         "departments":{
                            "engineering":{
                               "head":"Bob Johnson",
                               "teams":[
                                  {
                                     "teamName":"Backend",
                                     "members":[
                                        "Charlie",
                                        "Dana",
                                        "Eli"
                                     ],
                                     "technologies":[
                                        "Node.js",
                                        "Python",
                                        "PostgreSQL"
                                     ]
                                  },
                                  {
                                     "teamName":"Frontend",
                                     "members":[
                                        "Fiona",
                                        "George"
                                     ],
                                     "technologies":[
                                        "React",
                                        "Vue",
                                        "HTML5",
                                        "CSS3"
                                     ]
                                  }
                               ]
                            },
                            "hr":{
                               "head":"Grace Lee"
                            }
                         },
                         "offices":[
                            {
                               "location":"San Francisco",
                               "capacity":"50",
                               "openSince":"2010-05-15",
                               "facilities":{
                                  "cafeteria":"true",
                                  "gym":"false",
                                  "parking":{
                                     "available":"true",
                                     "spots":"20"
                                  }
                               }
                            },
                            {
                               "location":"New York",
                               "capacity":"30",
                               "openSince":"2015-08-20",
                               "facilities":{
                                  "cafeteria":"true",
                                  "gym":"true",
                                  "parking":{
                                     "available":"false",
                                     "spots":"0"
                                  }
                               }
                            }
                         ],
                         "financials":{
                            "fiscalYear":"2024",
                            "revenue":"5600000",
                            "expenses":"3200000",
                            "profit":"2400000",
                            "currency":"USD",
                            "investors":[
                               {
                                  "name":"Capital Ventures",
                                  "investment":"1000000",
                                  "equity":"10"
                               },
                               {
                                  "name":"Tech Angels",
                                  "investment":"500000",
                                  "equity":"5"
                               }
                            ]
                         }
                      },
                      {
                         "name":"Future Innovations Ltd.",
                         "founded":"2005",
                         "employees":[
                            {
                               "id":"1",
                               "name":"Jane Doe",
                               "title":"Founder",
                               "contact":{
                                  "email":"jane.doe@futureinnovations.com",
                                  "phone":"+44-20-9876",
                                  "address":{
                                     "street":"789 Future Blvd",
                                     "city":"Tech Haven",
                                     "state":"TX",
                                     "zip":"73301",
                                     "coordinates":{
                                        "lat":"30.2672",
                                        "lng":"-97.7431"
                                     }
                                  }
                               },
                               "projects":{
                                  "projectId":"p-201",
                                  "name":"Smart Home Automation",
                                  "status":"completed",
                                  "budget":"2000000",
                                  "milestones":[
                                     {
                                        "milestoneId":"m-101",
                                        "description":"Market Research",
                                        "dueDate":"2024-05-15",
                                        "completed":"true"
                                     },
                                     {
                                        "milestoneId":"m-102",
                                        "description":"Prototype Deployment",
                                        "dueDate":"2024-12-01",
                                        "completed":"true"
                                     }
                                  ]
                               }
                            },
                            {
                               "id":"2",
                               "name":"Mark Twain",
                               "title":"COO",
                               "contact":{
                                  "email":"mark.twain@futureinnovations.com",
                                  "phone":"+44-20-5432",
                                  "address":{
                                     "street":"321 Innovation Road",
                                     "city":"Tech Haven",
                                     "state":"TX",
                                     "zip":"73301",
                                     "coordinates":{
                                        "lat":"30.2672",
                                        "lng":"-97.7431"
                                     }
                                  }
                               }
                            }
                         ],
                         "departments":{
                            "engineering":{
                               "head":"Mark Twain",
                               "teams":[
                                  {
                                     "teamName":"Cloud",
                                     "members":[
                                        "Liam",
                                        "Emma",
                                        "Olivia"
                                     ],
                                     "technologies":[
                                        "AWS",
                                        "Azure",
                                        "Google Cloud"
                                     ]
                                  },
                                  {
                                     "teamName":"IoT",
                                     "members":[
                                        "Noah",
                                        "Ava"
                                     ],
                                     "technologies":[
                                        "Raspberry Pi",
                                        "Arduino",
                                        "MQTT"
                                     ]
                                  }
                               ]
                            },
                            "hr":{
                               "head":"Sophie Turner"
                            }
                         },
                         "offices":[
                            {
                               "location":"Austin",
                               "capacity":"40",
                               "openSince":"2018-03-10",
                               "facilities":{
                                  "cafeteria":"true",
                                  "gym":"true",
                                  "parking":{
                                     "available":"true",
                                     "spots":"25"
                                  }
                               }
                            },
                            {
                               "location":"London",
                               "capacity":"20",
                               "openSince":"2021-11-05",
                               "facilities":{
                                  "cafeteria":"true",
                                  "gym":"false",
                                  "parking":{
                                     "available":"true",
                                     "spots":"10"
                                  }
                               }
                            }
                         ],
                         "financials":{
                            "fiscalYear":"2024",
                            "revenue":"8000000",
                            "expenses":"4500000",
                            "profit":"3500000",
                            "currency":"GBP",
                            "investors":[
                               {
                                  "name":"Global Tech Fund",
                                  "investment":"2000000",
                                  "equity":"15"
                               },
                               {
                                  "name":"Startup Angels",
                                  "investment":"750000",
                                  "equity":"7"
                               }
                            ]
                         }
                      }
                   ]
                }
                """;

        // Act
        String json = jsonService.convertXmlToJson(xml);

        // Assert: Compare as JSON trees for consistent formatting
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.readTree(expectedJson);
        JsonNode actualNode = mapper.readTree(json);

        assertEquals(expectedNode, actualNode, "Expected JSON equivalent of the XML input");
    }

    /**
     * Tests the {@code convertXmlToJson} method with an invalid XML string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid XML.
     */
    @Test
    void testConvertXmlToJson_invalidXml_throwsException() {
        // Arrange
        String invalidXml = "<root><name>Alice</name><age>25</age>"; // Missing closing root tag

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertXmlToJson(invalidXml),
                "Expected invalid XML to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid XML"), "Expected error message to indicate invalid XML");
    }

    /**
     * Tests the {@code convertXmlToJson} method with an empty XML string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid XML.
     */
    @Test
    void testConvertXmlToJson_emptyXml_throwsException() {
        // Arrange
        String emptyXml = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertXmlToJson(emptyXml),
                "Expected empty XML to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid XML"), "Expected error message to indicate invalid XML");
    }

    /**
     * Tests the {@code convertJsonToExcel} method with a valid JSON array of objects.
     * Verifies that the method returns a Base64-encoded Excel file representing the JSON data.
     */
    @Test
    void testConvertJsonToExcel_validJson_returnsBase64Excel() throws Exception {
        // Arrange

        String json = "[{\"company\":{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}}},{\"company\":{\"name\":\"Future Innovations Ltd.\",\"founded\":2005,\"employees\":[{\"id\":1,\"name\":\"Jane Doe\",\"title\":\"Founder\",\"contact\":{\"email\":\"jane.doe@futureinnovations.com\",\"phone\":\"+44-20-9876\",\"address\":{\"street\":\"789 Future Blvd\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[{\"projectId\":\"p-201\",\"name\":\"Smart Home Automation\",\"status\":\"completed\",\"budget\":2000000,\"milestones\":[{\"milestoneId\":\"m-101\",\"description\":\"Market Research\",\"dueDate\":\"2024-05-15\",\"completed\":true},{\"milestoneId\":\"m-102\",\"description\":\"Prototype Deployment\",\"dueDate\":\"2024-12-01\",\"completed\":true}]}]},{\"id\":2,\"name\":\"Mark Twain\",\"title\":\"COO\",\"contact\":{\"email\":\"mark.twain@futureinnovations.com\",\"phone\":\"+44-20-5432\",\"address\":{\"street\":\"321 Innovation Road\",\"city\":\"Tech Haven\",\"state\":\"TX\",\"zip\":\"73301\",\"coordinates\":{\"lat\":30.2672,\"lng\":-97.7431}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Mark Twain\",\"teams\":[{\"teamName\":\"Cloud\",\"members\":[\"Liam\",\"Emma\",\"Olivia\"],\"technologies\":[\"AWS\",\"Azure\",\"Google Cloud\"]},{\"teamName\":\"IoT\",\"members\":[\"Noah\",\"Ava\"],\"technologies\":[\"Raspberry Pi\",\"Arduino\",\"MQTT\"]}]},\"hr\":{\"head\":\"Sophie Turner\",\"teams\":[]}},\"offices\":[{\"location\":\"Austin\",\"capacity\":40,\"openSince\":\"2018-03-10\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":true,\"spots\":25}}},{\"location\":\"London\",\"capacity\":20,\"openSince\":\"2021-11-05\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":10}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":8000000,\"expenses\":4500000,\"profit\":3500000,\"currency\":\"GBP\",\"investors\":[{\"name\":\"Global Tech Fund\",\"investment\":2000000,\"equity\":15},{\"name\":\"Startup Angels\",\"investment\":750000,\"equity\":7}]}}}]";

        // Act
        String base64Excel = jsonService.convertJsonToExcel(json);

        // Assert: Verify Base64 string can be decoded and has some data
        byte[] excelBytes = Base64.getDecoder().decode(base64Excel);
        assertNotNull(excelBytes, "Expected non-null Base64-decoded Excel bytes");
        assertTrue(excelBytes.length > 0, "Expected non-empty Excel bytes");

        // Optionally, verify that the workbook can be read back
        try (InputStream in = new ByteArrayInputStream(excelBytes);
             Workbook workbook = new XSSFWorkbook(in)) {
            Sheet sheet = workbook.getSheetAt(0);
            assertEquals("Sheet1", sheet.getSheetName(), "Expected sheet name 'Sheet1'");
            assertEquals("company", sheet.getRow(0).getCell(0).getStringCellValue(), "Expected header 'company'");
        }
    }

    /**
     * Tests the {@code convertJsonToExcel} method with a JSON object that is not an array.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not an array of objects.
     */
    @Test
    void testConvertJsonToExcel_jsonNotArray_throwsException() {
        // Arrange
        String json = "{ \"name\": \"Alice\", \"age\": 25 }";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToExcel(json),
                "Expected JSON not being array to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("array of objects"), "Expected error to mention array of objects");
    }

    /**
     * Tests the {@code convertJsonToExcel} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input cannot be parsed as JSON.
     */
    @Test
    void testConvertJsonToExcel_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\", \"age\": 25"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToExcel(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToExcel} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testConvertJsonToExcel_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToExcel(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToYaml} method with a valid JSON string.
     * Verifies that the method returns a YAML string representation of the JSON data.
     */
    @Test
    void testConvertJsonToYaml_validJson_returnsYaml() {
        // Arrange
        String json = """
                {
                    "company": {
                      "name": "Tech Solutions Inc.",
                      "founded": 1998,
                      "employees": [
                        {
                          "id": 1,
                          "name": "Alice Smith",
                          "title": "CEO",
                          "contact": {
                            "email": "alice.smith@techsolutions.com",
                            "phone": "+1-555-1234",
                            "address": {
                              "street": "123 Tech Lane",
                              "city": "Innovate City",
                              "state": "CA",
                              "zip": "90001",
                              "coordinates": {
                                "lat": 34.0522,
                                "lng": -118.2437
                              }
                            }
                          },
                          "projects": [
                            {
                              "projectId": "p-101",
                              "name": "AI Assistant",
                              "status": "active",
                              "budget": 1200000.5,
                              "milestones": [
                                {
                                  "milestoneId": "m-001",
                                  "description": "Initial Research",
                                  "dueDate": "2025-06-30",
                                  "completed": true
                                },
                                {
                                  "milestoneId": "m-002",
                                  "description": "Prototype Development",
                                  "dueDate": "2025-09-15",
                                  "completed": false
                                }
                              ]
                            }
                          ]
                        },
                        {
                          "id": 2,
                          "name": "Bob Johnson",
                          "title": "CTO",
                          "contact": {
                            "email": "bob.johnson@techsolutions.com",
                            "phone": "+1-555-5678",
                            "address": {
                              "street": "456 Dev Avenue",
                              "city": "Code Town",
                              "state": "NY",
                              "zip": "10001",
                              "coordinates": {
                                "lat": 40.7128,
                                "lng": -74.006
                              }
                            }
                          },
                          "projects": []
                        }
                      ],
                      "departments": {
                        "engineering": {
                          "head": "Bob Johnson",
                          "teams": [
                            {
                              "teamName": "Backend",
                              "members": [
                                "Charlie",
                                "Dana",
                                "Eli"
                              ],
                              "technologies": [
                                "Node.js",
                                "Python",
                                "PostgreSQL"
                              ]
                            },
                            {
                              "teamName": "Frontend",
                              "members": [
                                "Fiona",
                                "George"
                              ],
                              "technologies": [
                                "React",
                                "Vue",
                                "HTML5",
                                "CSS3"
                              ]
                            }
                          ]
                        },
                        "hr": {
                          "head": "Grace Lee",
                          "teams": []
                        }
                      },
                      "offices": [
                        {
                          "location": "San Francisco",
                          "capacity": 50,
                          "openSince": "2010-05-15",
                          "facilities": {
                            "cafeteria": true,
                            "gym": false,
                            "parking": {
                              "available": true,
                              "spots": 20
                            }
                          }
                        },
                        {
                          "location": "New York",
                          "capacity": 30,
                          "openSince": "2015-08-20",
                          "facilities": {
                            "cafeteria": true,
                            "gym": true,
                            "parking": {
                              "available": false,
                              "spots": 0
                            }
                          }
                        }
                      ],
                      "financials": {
                        "fiscalYear": 2024,
                        "revenue": 5600000,
                        "expenses": 3200000,
                        "profit": 2400000,
                        "currency": "USD",
                        "investors": [
                          {
                            "name": "Capital Ventures",
                            "investment": 1000000,
                            "equity": 10
                          },
                          {
                            "name": "Tech Angels",
                            "investment": 500000,
                            "equity": 5
                          }
                        ]
                      }
                    }
                  }
                """;

        String expectedYaml = """
                ---
                company:
                  name: "Tech Solutions Inc."
                  founded: 1998
                  employees:
                  - id: 1
                    name: "Alice Smith"
                    title: "CEO"
                    contact:
                      email: "alice.smith@techsolutions.com"
                      phone: "+1-555-1234"
                      address:
                        street: "123 Tech Lane"
                        city: "Innovate City"
                        state: "CA"
                        zip: "90001"
                        coordinates:
                          lat: 34.0522
                          lng: -118.2437
                    projects:
                    - projectId: "p-101"
                      name: "AI Assistant"
                      status: "active"
                      budget: 1200000.5
                      milestones:
                      - milestoneId: "m-001"
                        description: "Initial Research"
                        dueDate: "2025-06-30"
                        completed: true
                      - milestoneId: "m-002"
                        description: "Prototype Development"
                        dueDate: "2025-09-15"
                        completed: false
                  - id: 2
                    name: "Bob Johnson"
                    title: "CTO"
                    contact:
                      email: "bob.johnson@techsolutions.com"
                      phone: "+1-555-5678"
                      address:
                        street: "456 Dev Avenue"
                        city: "Code Town"
                        state: "NY"
                        zip: "10001"
                        coordinates:
                          lat: 40.7128
                          lng: -74.006
                    projects: []
                  departments:
                    engineering:
                      head: "Bob Johnson"
                      teams:
                      - teamName: "Backend"
                        members:
                        - "Charlie"
                        - "Dana"
                        - "Eli"
                        technologies:
                        - "Node.js"
                        - "Python"
                        - "PostgreSQL"
                      - teamName: "Frontend"
                        members:
                        - "Fiona"
                        - "George"
                        technologies:
                        - "React"
                        - "Vue"
                        - "HTML5"
                        - "CSS3"
                    hr:
                      head: "Grace Lee"
                      teams: []
                  offices:
                  - location: "San Francisco"
                    capacity: 50
                    openSince: "2010-05-15"
                    facilities:
                      cafeteria: true
                      gym: false
                      parking:
                        available: true
                        spots: 20
                  - location: "New York"
                    capacity: 30
                    openSince: "2015-08-20"
                    facilities:
                      cafeteria: true
                      gym: true
                      parking:
                        available: false
                        spots: 0
                  financials:
                    fiscalYear: 2024
                    revenue: 5600000
                    expenses: 3200000
                    profit: 2400000
                    currency: "USD"
                    investors:
                    - name: "Capital Ventures"
                      investment: 1000000
                      equity: 10
                    - name: "Tech Angels"
                      investment: 500000
                      equity: 5
                """;

        // Act
        String yaml = jsonService.convertJsonToYaml(json);

        // Assert
        assertEquals(expectedYaml, yaml, "An actual YAML should be identical to an expected YAML");
    }

    /**
     * Tests the {@code convertJsonToYaml} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input cannot be parsed as valid JSON.
     */
    @Test
    void testConvertJsonToYaml_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\", \"age\": 25"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToYaml(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertJsonToYaml} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testConvertJsonToYaml_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertJsonToYaml(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code convertExcelToJson} method with a valid Base64-encoded Excel file.
     * Verifies that the method returns a JSON array representation of the Excel data.
     */
    @Test
    void testConvertExcelToJson_validExcel_returnsjson() throws Exception {
        // Arrange: Create an Excel file in memory
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("name");
        headerRow.createCell(1).setCellValue("age");
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Alice");
        row1.createCell(1).setCellValue("25");
        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Bob");
        row2.createCell(1).setCellValue("30");

        // Convert to Base64
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        String base64Excel = Base64.getEncoder().encodeToString(out.toByteArray());

        // Act
        String json = jsonService.convertExcelToJson(base64Excel);

        // Assert: Verify JSON array contains expected records
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(json);

        assertTrue(actualNode.isArray(), "Expected JSON array");
        assertEquals(2, actualNode.size(), "Expected 2 records");

        assertEquals("Alice", actualNode.get(0).get("name").asText());
        assertEquals("25", actualNode.get(0).get("age").asText());
        assertEquals("Bob", actualNode.get(1).get("name").asText());
        assertEquals("30", actualNode.get(1).get("age").asText());
    }

    /**
     * Tests the {@code convertExcelToJson} method with an invalid Base64-encoded Excel string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input cannot be decoded as valid Excel.
     */
    @Test
    void testConvertExcelToJson_invalidBase64_throwsException() {
        // Arrange
        String invalidBase64 = "InvalidBase64==";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertExcelToJson(invalidBase64),
                "Expected invalid Base64 to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid Excel"), "Expected error message to indicate invalid Excel");
    }

    /**
     * Tests the {@code convertExcelToJson} method with an empty input string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid Base64-encoded Excel.
     */
    @Test
    void testConvertExcelToJson_emptyInput_throwsException() {
        // Arrange
        String emptyInput = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertExcelToJson(emptyInput),
                "Expected empty input to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid Excel"), "Expected error message to indicate invalid Excel");
    }

    /**
     * Tests the {@code convertYamlToJson} method with a valid YAML string.
     * Verifies that the method returns a JSON string representation of the YAML data.
     */
    @Test
    void testConvertYamlToJson_validYaml_returnsJson() throws Exception {
        // Arrange
        String yaml = """
                ---
                company:
                  name: "Tech Solutions Inc."
                  founded: 1998
                  employees:
                  - id: 1
                    name: "Alice Smith"
                    title: "CEO"
                    contact:
                      email: "alice.smith@techsolutions.com"
                      phone: "+1-555-1234"
                      address:
                        street: "123 Tech Lane"
                        city: "Innovate City"
                        state: "CA"
                        zip: "90001"
                        coordinates:
                          lat: 34.0522
                          lng: -118.2437
                    projects:
                    - projectId: "p-101"
                      name: "AI Assistant"
                      status: "active"
                      budget: 1200000.5
                      milestones:
                      - milestoneId: "m-001"
                        description: "Initial Research"
                        dueDate: "2025-06-30"
                        completed: true
                      - milestoneId: "m-002"
                        description: "Prototype Development"
                        dueDate: "2025-09-15"
                        completed: false
                  - id: 2
                    name: "Bob Johnson"
                    title: "CTO"
                    contact:
                      email: "bob.johnson@techsolutions.com"
                      phone: "+1-555-5678"
                      address:
                        street: "456 Dev Avenue"
                        city: "Code Town"
                        state: "NY"
                        zip: "10001"
                        coordinates:
                          lat: 40.7128
                          lng: -74.006
                    projects: []
                  departments:
                    engineering:
                      head: "Bob Johnson"
                      teams:
                      - teamName: "Backend"
                        members:
                        - "Charlie"
                        - "Dana"
                        - "Eli"
                        technologies:
                        - "Node.js"
                        - "Python"
                        - "PostgreSQL"
                      - teamName: "Frontend"
                        members:
                        - "Fiona"
                        - "George"
                        technologies:
                        - "React"
                        - "Vue"
                        - "HTML5"
                        - "CSS3"
                    hr:
                      head: "Grace Lee"
                      teams: []
                  offices:
                  - location: "San Francisco"
                    capacity: 50
                    openSince: "2010-05-15"
                    facilities:
                      cafeteria: true
                      gym: false
                      parking:
                        available: true
                        spots: 20
                  - location: "New York"
                    capacity: 30
                    openSince: "2015-08-20"
                    facilities:
                      cafeteria: true
                      gym: true
                      parking:
                        available: false
                        spots: 0
                  financials:
                    fiscalYear: 2024
                    revenue: 5600000
                    expenses: 3200000
                    profit: 2400000
                    currency: "USD"
                    investors:
                    - name: "Capital Ventures"
                      investment: 1000000
                      equity: 10
                    - name: "Tech Angels"
                      investment: 500000
                      equity: 5
                """;

        String expectedJson = """
                {
                    "company": {
                      "name": "Tech Solutions Inc.",
                      "founded": 1998,
                      "employees": [
                        {
                          "id": 1,
                          "name": "Alice Smith",
                          "title": "CEO",
                          "contact": {
                            "email": "alice.smith@techsolutions.com",
                            "phone": "+1-555-1234",
                            "address": {
                              "street": "123 Tech Lane",
                              "city": "Innovate City",
                              "state": "CA",
                              "zip": "90001",
                              "coordinates": {
                                "lat": 34.0522,
                                "lng": -118.2437
                              }
                            }
                          },
                          "projects": [
                            {
                              "projectId": "p-101",
                              "name": "AI Assistant",
                              "status": "active",
                              "budget": 1200000.5,
                              "milestones": [
                                {
                                  "milestoneId": "m-001",
                                  "description": "Initial Research",
                                  "dueDate": "2025-06-30",
                                  "completed": true
                                },
                                {
                                  "milestoneId": "m-002",
                                  "description": "Prototype Development",
                                  "dueDate": "2025-09-15",
                                  "completed": false
                                }
                              ]
                            }
                          ]
                        },
                        {
                          "id": 2,
                          "name": "Bob Johnson",
                          "title": "CTO",
                          "contact": {
                            "email": "bob.johnson@techsolutions.com",
                            "phone": "+1-555-5678",
                            "address": {
                              "street": "456 Dev Avenue",
                              "city": "Code Town",
                              "state": "NY",
                              "zip": "10001",
                              "coordinates": {
                                "lat": 40.7128,
                                "lng": -74.006
                              }
                            }
                          },
                          "projects": []
                        }
                      ],
                      "departments": {
                        "engineering": {
                          "head": "Bob Johnson",
                          "teams": [
                            {
                              "teamName": "Backend",
                              "members": [
                                "Charlie",
                                "Dana",
                                "Eli"
                              ],
                              "technologies": [
                                "Node.js",
                                "Python",
                                "PostgreSQL"
                              ]
                            },
                            {
                              "teamName": "Frontend",
                              "members": [
                                "Fiona",
                                "George"
                              ],
                              "technologies": [
                                "React",
                                "Vue",
                                "HTML5",
                                "CSS3"
                              ]
                            }
                          ]
                        },
                        "hr": {
                          "head": "Grace Lee",
                          "teams": []
                        }
                      },
                      "offices": [
                        {
                          "location": "San Francisco",
                          "capacity": 50,
                          "openSince": "2010-05-15",
                          "facilities": {
                            "cafeteria": true,
                            "gym": false,
                            "parking": {
                              "available": true,
                              "spots": 20
                            }
                          }
                        },
                        {
                          "location": "New York",
                          "capacity": 30,
                          "openSince": "2015-08-20",
                          "facilities": {
                            "cafeteria": true,
                            "gym": true,
                            "parking": {
                              "available": false,
                              "spots": 0
                            }
                          }
                        }
                      ],
                      "financials": {
                        "fiscalYear": 2024,
                        "revenue": 5600000,
                        "expenses": 3200000,
                        "profit": 2400000,
                        "currency": "USD",
                        "investors": [
                          {
                            "name": "Capital Ventures",
                            "investment": 1000000,
                            "equity": 10
                          },
                          {
                            "name": "Tech Angels",
                            "investment": 500000,
                            "equity": 5
                          }
                        ]
                      }
                    }
                  }
                """;

        // Act
        String json = jsonService.convertYamlToJson(yaml);

        // Assert: Compare as JSON trees
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedNode = mapper.readTree(expectedJson);
        JsonNode actualNode = mapper.readTree(json);

        assertEquals(expectedNode, actualNode, "Expected JSON equivalent of the YAML input");
    }

    /**
     * Tests the {@code convertYamlToJson} method with an invalid YAML string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid YAML.
     */
    @Test
    void testConvertYamlToJson_invalidYaml_throwsException() {
        // Arrange: Truly invalid YAML (missing key for colon)
        String invalidYaml = "name: Alice\nage: 25\n: missingKey";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertYamlToJson(invalidYaml),
                "Expected invalid YAML to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid YAML"), "Expected error message to indicate invalid YAML");
    }

    /**
     * Tests the {@code convertYamlToJson} method with a YAML string that contains unexpected formatting but is still valid YAML.
     * Verifies that the method does not throw an exception and returns the JSON representation of the parsed YAML, even if the structure is not as expected.
     */
    @Test
    void testConvertYamlToJson_invalidYaml_isStillParsed() {
        // Arrange
        String invalidYaml = "name: Alice\nage: 25\n  invalidIndent";

        // Act
        String json = jsonService.convertYamlToJson(invalidYaml);

        // Assert: It does NOT throw, but parse with an unexpected key
        assertTrue(json.contains("invalidIndent"), "Expected output to contain unexpected 'invalidIndent' field");
    }

    /**
     * Tests the {@code convertYamlToJson} method with an empty YAML string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid YAML.
     */
    @Test
    void testConvertYamlToJson_emptyYaml_throwsException() {
        // Arrange
        String emptyYaml = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.convertYamlToJson(emptyYaml),
                "Expected empty YAML to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid YAML"), "Expected error message to indicate invalid YAML");
    }

    /**
     * Tests the {@code generateTypeDefinitionsFromJson} method with a valid JSON string.
     * Verifies that the method returns TypeScript type definitions representing the JSON structure, including nested interfaces.
     */
    @Test
    void testGenerateTypeDefinitionsFromJson_validJson_returnsTypeDefinitions() {
        // Arrange
        String json = """
                {
                  "name": "Alice",
                  "age": 25,
                  "address": {
                    "city": "Wonderland",
                    "zip": "12345"
                  },
                  "tags": ["friend", "adventurer"]
                }
                """;

        // Act
        String typeDefs = jsonService.generateTypeDefinitionsFromJson(json);

        // Assert: Check that the output contains expected TypeScript definitions
        assertTrue(typeDefs.contains("interface RootObject"), "Expected definition for RootObject");
        assertTrue(typeDefs.contains("name: string;"), "Expected name field as string");
        assertTrue(typeDefs.contains("age: number;"), "Expected age field as number");
        assertTrue(typeDefs.contains("address: Address;"), "Expected address field as nested Address type");
        assertTrue(typeDefs.contains("interface Address"), "Expected nested Address interface");
    }

    /**
     * Tests the {@code generateTypeDefinitionsFromJson} method with an invalid JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input cannot be parsed as valid JSON.
     */
    @Test
    void testGenerateTypeDefinitionsFromJson_invalidJson_throwsException() {
        // Arrange
        String invalidJson = "{ \"name\": \"Alice\", \"age\": 25"; // Missing closing brace

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.generateTypeDefinitionsFromJson(invalidJson),
                "Expected invalid JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

    /**
     * Tests the {@code generateTypeDefinitionsFromJson} method with an empty JSON string.
     * Verifies that the method throws an {@code IllegalArgumentException} because the input is not valid JSON.
     */
    @Test
    void testGenerateTypeDefinitionsFromJson_emptyJson_throwsException() {
        // Arrange
        String emptyJson = "";

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jsonService.generateTypeDefinitionsFromJson(emptyJson),
                "Expected empty JSON to throw IllegalArgumentException");

        assertTrue(ex.getMessage().contains("Invalid JSON"), "Expected error message to indicate invalid JSON");
    }

}