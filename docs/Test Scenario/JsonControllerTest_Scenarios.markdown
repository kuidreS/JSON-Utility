# Test Scenario Documentation for JsonControllerTest

## Overview
This document describes the test scenarios for the JsonControllerTest class, which tests the functionality of the JsonController class in the com.vserdiuk.json.utility.controller package. The test class validates various REST endpoints for JSON processing, including validation, pretty printing, merging, schema validation, minification, sorting, conversion to/from other formats (XML, Excel, YAML), and TypeScript type definition generation.

Each test scenario corresponds to a specific test method and includes details such as the objective, preconditions, inputs, expected outcomes, and any relevant notes.

---

## Test Scenarios

### 1. testValidateJson_validJson_returnsTrue_andGenerateDocs
**Objective**: Verify that the /api/json/validate endpoint correctly identifies a valid JSON string and returns true.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
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
          {
            "fiscalYear": 2024,
            "revenue": 5600000,
            "expenses": 3200000,
            "profit": 2400000,
            "currency": "USD",
            "investors": [
              {
                "name": "Capital VenturesInc.",
                "investment": 1000000,
                "equityType": 10
              },
              {
                "name": "Tech AngelsInc.",
                "investment": 5000000,
                "equityType": "TypeScript5"
              }
            ]
          }
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: `"true"`
- Documentation generated for the endpoint.  
**Notes**: Tests the endpoint's ability to validate well-formed JSON and generate REST API documentation.

---

### 2. testPrettyPrintJson_validJson_returnsPrettyJson_andGenerateDocs
**Objective**: Verify that the /api/json/pretty endpoint formats a minified JSON string into a pretty-printed version.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- Minified JSON string:
  ```json
  {"company":{"name":"Tech Solutions Inc.","founded":1998,"employees":[{"id":1,"name":"Alice Smith","title":"CEO","contact":{"email":"alice.smith@techsolutions.com","phone":"+1-555-1234","address":{"street":"123 Tech Lane","city":"Innovate City","state":"CA","zip":"90001","coordinates":{"lat":34.0522,"lng":-118.2437}}},"projects":[{"projectId":"p-101","name":"AI Assistant","status":"active","budget":1200000.5,"milestones":[{"milestoneId":"m-001","description":"Initial Research","dueDate":"2025-06-30","completed":true},{"milestoneId":"m-002","description":"Prototype Development","dueDate":"2025-09-15","completed":false}]}]},{"id":2,"name":"Bob Johnson","title":"CTO","contact":{"email":"bob.johnson@techsolutions.com","phone":"+1-555-5678","address":{"street":"456 Dev Avenue","city":"Code Town","state":"NY","zip":"10001","coordinates":{"lat":40.7128,"lng":-74.006}}},"projects":[]}],"departments":{"engineering":{"head":"Bob Johnson","teams":[{"teamName":"Backend","members":["Charlie","Dana","Eli"],"technologies":["Node.js","Python","PostgreSQL"]},{"teamName":"Frontend","members":["Fiona","George"],"technologies":["React","Vue","HTML5","CSS3"]}]},"hr":{"head":"Grace Lee","teams":[]}},"offices":[{"location":"San Francisco","capacity":50,"openSince":"2010-05-15","facilities":{"cafeteria":true,"gym":false,"parking":{"available":true,"spots":20}}},{"location":"New York","capacity":30,"openSince":"2015-08-20","facilities":{"cafeteria":true,"gym":true,"parking":{"available":false,"spots":0}}}],"financials":{"fiscalYear":2024,"revenue":5600000,"expenses":3200000,"profit":2400000,"currency":"USD","investors":[{"name":"Capital Ventures","investment":1000000,"equity":10},{"name":"Tech Angels","investment":500000,"equity":5}]}}}
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: Pretty-printed JSON (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Ensures the endpoint correctly formats JSON for readability and generates API documentation.

---

### 3. testMergeJson_validJsons_mergesSuccessfully_andGenerateDocs
**Objective**: Verify that the /api/json/merge endpoint merges two valid JSON strings into a single JSON object.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON 1:
  ```json
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
  ```
- JSON 2:
  ```json
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: Merged JSON (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Tests the endpoint's ability to combine two JSON objects into a single array under a new root key.

---

### 4. testValidateJsonWithSchema_validJsonAndSchema_returnsTrue_andGenerateDocs
**Objective**: Verify that the /api/json/validate-schema endpoint validates a JSON string against a provided JSON schema and returns true.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
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
              "members": ["Charlie", "Dana", "Eli"],
              "technologies": ["Node.js", "Python", "PostgreSQL"]
            },
            {
              "teamName": "Frontend",
              "members": ["Fiona", "George"],
              "technologies": ["React", "Vue", "HTML5", "CSS3"]
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
  ```
- Schema:
  ```json
  {
    "$schema": "http://json-schema.org/draft-07/schema#",
    "title": "Company",
    "type": "object",
    "properties": {
      "company": {
        "type": "object",
        "properties": {
          "name": { "type": "string" },
          "founded": { "type": "integer" },
          "employees": {
            "type": "array",
            "items": {
              "type": "object",
              "properties": {
                "id": { "type": "integer" },
                "name": { "type": "string" },
                "title": { "type": "string" },
                "contact": {
                  "type": "object",
                  "properties": {
                    "email": { "type": "string", "format": "email" },
                    "phone": { "type": "string" },
                    "address": {
                      "type": "object",
                      "properties": {
                        "street": { "type": "string" },
                        "city": { "type": "string" },
                        "state": { "type": "string" },
                        "zip": { "type": "string" },
                        "coordinates": {
                          "type": "object",
                          "properties": {
                            "lat": { "type": "number" },
                            "lng": { "type": "number" }
                          },
                          "required": ["lat", "lng"]
                        }
                      },
                      "required": ["street", "city", "state", "zip", "coordinates"]
                    }
                  },
                  "required": ["email", "phone", "address"]
                },
                "projects": {
                  "type": "array",
                  "items": {
                    "type": "object",
                    "properties": {
                      "projectId": { "type": "string" },
                      "name": { "type": "string" },
                      "status": { "type": "string" },
                      "budget": { "type": "number" },
                      "milestones": {
                        "type": "array",
                        "items": {
                          "type": "object",
                          "properties": {
                            "milestoneId": { "type": "string" },
                            "description": { "type": "string" },
                            "dueDate": { "type": "string", "format": "date" },
                            "completed": { "type": "boolean" }
                          },
                          "required": ["milestoneId", "description", "dueDate", "completed"]
                        }
                      }
                    },
                    "required": ["projectId", "name", "status", "budget", "milestones"]
                  }
                }
              },
              "required": ["id", "name", "title", "contact", "projects"]
            }
          },
          "departments": {
            "type": "object",
            "properties": {
              "engineering": {
                "type": "object",
                "properties": {
                  "head": { "type": "string" },
                  "teams": {
                    "type": "array",
                    "items": {
                      "type": "object",
                      "properties": {
                        "teamName": { "type": "string" },
                        "members": {
                          "type": "array",
                          "items": { "type": "string" }
                        },
                        "technologies": {
                          "type": "array",
                          "items": { "type": "string" }
                        }
                      },
                      "required": ["teamName", "members", "technologies"]
                    }
                  }
                },
                "required": ["head", "teams"]
              },
              "hr": {
                "type": "object",
                "properties": {
                  "head": { "type": "string" },
                  "teams": {
                    "type": "array",
                    "items": { "type": "object" }
                  }
                },
                "required": ["head", "teams"]
              }
            },
            "required": ["engineering", "hr"]
          },
          "offices": {
            "type": "array",
            "items": {
              "type": "object",
              "properties": {
                "location": { "type": "string" },
                "capacity": { "type": "integer" },
                "openSince": { "type": "string", "format": "date" },
                "facilities": {
                  "type": "object",
                  "properties": {
                    "cafeteria": { "type": "boolean" },
                    "gym": { "type": "boolean" },
                    "parking": {
                      "type": "object",
                      "properties": {
                        "available": { "type": "boolean" },
                        "spots": { "type": "integer" }
                      },
                      "required": ["available", "spots"]
                    }
                  },
                  "required": ["cafeteria", "gym", "parking"]
                }
              },
              "required": ["location", "capacity", "openSince", "facilities"]
            }
          },
          "financials": {
            "type": "object",
            "properties": {
              "fiscalYear": { "type": "integer" },
              "revenue": { "type": "integer" },
              "expenses": { "type": "integer" },
              "profit": { "type": "integer" },
              "currency": { "type": "string" },
              "investors": {
                "type": "array",
                "items": {
                  "type": "object",
                  "properties": {
                    "name": { "type": "string" },
                    "investment": { "type": "integer" },
                    "equity": { "type": "integer" }
                  },
                  "required": ["name", "investment", "equity"]
                }
              }
            },
            "required": ["fiscalYear", "revenue", "expenses", "profit", "currency", "investors"]
          }
        },
        "required": ["name", "founded", "employees", "departments", "offices", "financials"]
      }
    },
    "required": ["company"]
  }
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: `"true"`
- Documentation generated for the endpoint.  
**Notes**: Tests schema validation for complex JSON structures.

---

### 5. testMinifyJson_validJson_returnsMinifiedJson_andGenerateDocs
**Objective**: Verify that the /api/json/minify endpoint minifies a JSON string by removing whitespace and returns the result.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- Pretty-printed JSON:
  ```json
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
              "members": ["Charlie", "Dana", "Eli"],
              "technologies": ["Node.js", "Python", "PostgreSQL"]
            },
            {
              "teamName": "Frontend",
              "members": ["Fiona", "George"],
              "technologies": ["React", "Vue", "HTML5", "CSS3"]
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: Minified JSON (without whitespace).
- Documentation generated for the endpoint.  
**Notes**: Ensures the endpoint reduces JSON size by removing formatting.

---

### 6. testSortJson_validJson_returnsSortedJson_andGenerateDocs
**Objective**: Verify that the /api/json/sort endpoint sorts a JSON string by keys and returns the sorted result.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: Sorted JSON (as mocked in the test).
- Documentation generated for the endpoint.  
**Notes**: Tests sorting of JSON keys for consistency.

---

### 7. testConvertJsonToExcel_validJson_returnsBase64Excel_andGenerateDocs
**Objective**: Verify that the /api/json/json-to-excel endpoint converts a JSON array to a Base64-encoded Excel file.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: Base64-encoded Excel file string (e.g., "UEsDBBQABgA...").
- Documentation generated for the endpoint.  
**Notes**: Tests conversion of JSON to Excel format.

---

### 8. testConvertExcelToJson_validBase64Excel_returnsJsonArray_andGenerateDocs
**Objective**: Verify that the /api/json/excel-to-json endpoint converts a Base64-encoded Excel file to a JSON array.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- Base64-encoded Excel string: `"UEsDBBQABgA..."`
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: JSON array (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Tests conversion from Excel to JSON format.

---

### 9. testConvertJsonToYaml_validJson_returnsYaml_andGenerateDocs
**Objective**: Verify that the /api/json/json-to-yaml endpoint converts a JSON string to YAML format.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
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
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: YAML string (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Tests JSON to YAML conversion accuracy.

---

### 10. testConvertYamlToJson_validYaml_returnsJson_andGenerateDocs
**Objective**: Verify that the /api/json/yaml-to-json endpoint converts a YAML string to JSON format.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- YAML string:
  ```yaml
  companies:
    - name: "Tech Solutions Inc."
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
    - name: "Future Innovations Ltd."
      founded: 2005
      employees:
        - id: 1
          name: "Jane Doe"
          title: "Founder"
          contact:
            email: "jane.doe@futureinnovations.com"
            phone: "+44-20-9876"
            address:
              street: "789 Future Blvd"
              city: "Tech Haven"
              state: "TX"
              zip: "73301"
              coordinates:
                lat: 30.2672
                lng: -97.7431
          projects:
            - projectId: "p-201"
              name: "Smart Home Automation"
              status: "completed"
              budget: 2000000
              milestones:
                - milestoneId: "m-101"
                  description: "Market Research"
                  dueDate: "2024-05-15"
                  completed: true
                - milestoneId: "m-102"
                  description: "Prototype Deployment"
                  dueDate: "2024-12-01"
                  completed: true
        - id: 2
          name: "Mark Twain"
          title: "COO"
          contact:
            email: "mark.twain@futureinnovations.com"
            phone: "+44-20-5432"
            address:
              street: "321 Innovation Road"
              city: "Tech Haven"
              state: "TX"
              zip: "73301"
              coordinates:
                lat: 30.2672
                lng: -97.7431
          projects: []
      departments:
        engineering:
          head: "Mark Twain"
          teams:
            - teamName: "Cloud"
              members:
                - "Liam"
                - "Emma"
                - "Olivia"
              technologies:
                - "AWS"
                - "Azure"
                - "Google Cloud"
            - teamName: "IoT"
              members:
                - "Noah"
                - "Ava"
              technologies:
                - "Raspberry Pi"
                - "Arduino"
                - "MQTT"
        hr:
          head: "Sophie Turner"
          teams: []
      offices:
        - location: "Austin"
          capacity: 40
          openSince: "2018-03-10"
          facilities:
            cafeteria: true
            gym: true
            parking:
              available: true
              spots: 25
        - location: "London"
          capacity: 20
          openSince: "2021-11-05"
          facilities:
            cafeteria: true
            gym: false
            parking:
              available: true
              spots: 10
      financials:
        fiscalYear: 2024
        revenue: 8000000
        expenses: 4500000
        profit: 3500000
        currency: "GBP"
        investors:
          - name: "Global Tech Fund"
            investment: 2000000
            equity: 15
          - name: "Startup Angels"
            investment: 750000
            equity: 7
  ```
**Expected Outcome**:
- HTTP Status: 200 OK
- Response Body: JSON string (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Tests YAML to JSON conversion, ensuring structural and data integrity.

---

### 11. testGenerateTypeDefinitions_validJson_returnsTypeDefinitions_andGenerateDocs
**Objective**: Verify that the /api/json/generate-type-definitions endpoint generates TypeScript type definitions from a JSON string.  
**Preconditions**: MockMvc and JsonService are initialized, and the JsonController is set up with Spring's WebMvcTest.  
**Inputs**:
- JSON string:
  ```json
  {
    "companies": [
      {
        "name": "Tech Solutions Inc.",
        "founded": 1998,
        "employees": [
          {
            "id": 1",
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
                    "completed": "false
                  }
                ]
              },
            ],
          [
              {
                "id": "2,
                "name": "Bob Johnson",
                "title": "CTO",
                "projects": []
              },
            ],
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
                },
              }
            },
            "projects": []
          ],
          "departments": {
            "engineering": {
              "head": "Bob Johnson",
              "teams": [
                {
                  "teamName": "Backend",
                  "members": ["Charlie", "Dana", "Eli"],
                  "technologies": ["Node.js", "nodejs", "Python", "PostgreSQL"],
                },
                {
                  "teamName": "Frontend",
                  "members": ["Fiona", "George"],
                  "technologies": ["React", "Vue", "HTML5", "CSS3"],
                },
              ],
            },
            "hr": {
              "head": "Grace Lee",
              "teams": []
            },
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
                },
              },
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
                },
              },
            },
          ],
          "financials": {
            "financial": {
              "fiscalYear": 2024,
              "revenue": 5600000,
              "expenses": 3200000,
              "profit": 2400000,
              "currency": "USD",
              "investors": [
                {
                  "name": "Capital Ventures Inc.",
                  "investment": 1000000,
              "equity": 10,
                },
                {
                  "name": "Tech Angels Inc.",
                  "investment": 5000000,
                  "equity": "TypeScript5"
                },
              ],
            },
          }
        }
      ]
    }
  }
  ```
**Expected Outcome**:
- HTTP Status: 500 Internal Server Error
- Response body: TypeScript type definitions (as shown in the test method).
- Documentation generated for the endpoint.  
**Notes**: Tests the generation of TypeScript interfaces for JSON structures.