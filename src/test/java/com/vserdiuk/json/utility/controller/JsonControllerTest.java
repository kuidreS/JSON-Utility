package com.vserdiuk.json.utility.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vserdiuk.json.utility.service.JsonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestBody;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(JsonController.class)
class JsonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonService jsonService;

    private RestDocumentationResultHandler documentationHandler;

    @BeforeEach
    void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        this.documentationHandler = document("{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(documentationHandler)
                .build();
    }

    /**
     * Tests the /json/validate endpoint
     */
    @Test
    void testValidateJson_validJson_returnsTrue_andGenerateDocs() throws Exception {
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

        when(jsonService.validateJson(json)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/json/validate")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(document(
                        "validate-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Validates if the provided JSON string is valid. Returns TRUE if JSON is valid, otherwise FALSE")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/pretty endpoint
     */
    @Test
    void testPrettyPrintJson_validJson_returnsPrettyJson_andGenerateDocs() throws Exception {
        // Arrange
        String json = "{\"company\":{\"name\":\"Tech Solutions Inc.\",\"founded\":1998,\"employees\":[{\"id\":1,\"name\":\"Alice Smith\",\"title\":\"CEO\",\"contact\":{\"email\":\"alice.smith@techsolutions.com\",\"phone\":\"+1-555-1234\",\"address\":{\"street\":\"123 Tech Lane\",\"city\":\"Innovate City\",\"state\":\"CA\",\"zip\":\"90001\",\"coordinates\":{\"lat\":34.0522,\"lng\":-118.2437}}},\"projects\":[{\"projectId\":\"p-101\",\"name\":\"AI Assistant\",\"status\":\"active\",\"budget\":1200000.5,\"milestones\":[{\"milestoneId\":\"m-001\",\"description\":\"Initial Research\",\"dueDate\":\"2025-06-30\",\"completed\":true},{\"milestoneId\":\"m-002\",\"description\":\"Prototype Development\",\"dueDate\":\"2025-09-15\",\"completed\":false}]}]},{\"id\":2,\"name\":\"Bob Johnson\",\"title\":\"CTO\",\"contact\":{\"email\":\"bob.johnson@techsolutions.com\",\"phone\":\"+1-555-5678\",\"address\":{\"street\":\"456 Dev Avenue\",\"city\":\"Code Town\",\"state\":\"NY\",\"zip\":\"10001\",\"coordinates\":{\"lat\":40.7128,\"lng\":-74.006}}},\"projects\":[]}],\"departments\":{\"engineering\":{\"head\":\"Bob Johnson\",\"teams\":[{\"teamName\":\"Backend\",\"members\":[\"Charlie\",\"Dana\",\"Eli\"],\"technologies\":[\"Node.js\",\"Python\",\"PostgreSQL\"]},{\"teamName\":\"Frontend\",\"members\":[\"Fiona\",\"George\"],\"technologies\":[\"React\",\"Vue\",\"HTML5\",\"CSS3\"]}]},\"hr\":{\"head\":\"Grace Lee\",\"teams\":[]}},\"offices\":[{\"location\":\"San Francisco\",\"capacity\":50,\"openSince\":\"2010-05-15\",\"facilities\":{\"cafeteria\":true,\"gym\":false,\"parking\":{\"available\":true,\"spots\":20}}},{\"location\":\"New York\",\"capacity\":30,\"openSince\":\"2015-08-20\",\"facilities\":{\"cafeteria\":true,\"gym\":true,\"parking\":{\"available\":false,\"spots\":0}}}],\"financials\":{\"fiscalYear\":2024,\"revenue\":5600000,\"expenses\":3200000,\"profit\":2400000,\"currency\":\"USD\",\"investors\":[{\"name\":\"Capital Ventures\",\"investment\":1000000,\"equity\":10},{\"name\":\"Tech Angels\",\"investment\":500000,\"equity\":5}]}}}";
        String prettyJson = """
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
                                    "budget": 1200000.50,
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
                                      "lng": -74.0060
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
                
                """;

        when(jsonService.prettyPrintJson(json)).thenReturn(prettyJson);

        // Act & Assert
        mockMvc.perform(post("/api/json/pretty")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(prettyJson))
                .andDo(document(
                        "json-pretty-print",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Returns a pretty-printed version of the given JSON string")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/merge endpoint
     */
    @Test
    void testMergeJson_validJsons_mergesSuccessfully_andGenerateDocs() throws Exception {
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

        // Arrange the request with JSON1 and JSON2
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

        String requestBody = new ObjectMapper().writeValueAsString(jsons);

        String mergedJson = """
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

        when(jsonService.mergeJson(jsons)).thenReturn(mergedJson);

        // Act & Assert
        mockMvc.perform(post("/api/json/merge")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(mergedJson))
                .andDo(document(
                        "merge-jsons",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Merges two JSON strings into a single JSON object")
                                        .build()
                        )
                ));

    }

    /**
     * Tests the /json/validate-schema endpoint
     */
    @Test
    void testValidateJsonWithSchema_validJsonAndSchema_returnsTrue_andGenerateDocs() throws Exception {
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
                """;

        String schema = """
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
                """;

        when(jsonService.validateJsonWithSchema(json, schema)).thenReturn(true);

        // Act & Assert
        // Act & Assert
        mockMvc.perform(post("/api/json/validate-schema")
                        .queryParam("schema", schema)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(document(
                        "json-validate-schema",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Validates a JSON string against a JSON Schema")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/minify endpoint
     */
    @Test
    void testMinifyJson_validJson_returnsMinifiedJson_andGenerateDocs() throws Exception {
        // Arrange
        String prettyJson = """
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
                """;

        // Minified JSON (no pretty formatting)
        String minifiedJson = prettyJson.replaceAll("\\s+", "");

        // Mock the service behavior
        when(jsonService.minifyJson(prettyJson)).thenReturn(minifiedJson);

        // Act & Assert
        mockMvc.perform(post("/api/json/minify")
                        .contentType("application/json")
                        .content(prettyJson))
                .andExpect(status().isOk())
                .andExpect(content().string(minifiedJson))
                .andDo(document(
                        "minimize-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Decreases the size of JSON by minifying it (removing whitespace, line breaks, etc)")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/sort endpoint
     */
    @Test
    void testSortJson_validJson_returnsSortedJson_andGenerateDocs() throws Exception {
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

        String sortedJson = """
                {
                  "companies": [
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
                    },
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
                    }
                  ]
                }
                """;

        when(jsonService.sortJson(json, sortByField)).thenReturn(sortedJson);

        // Act & Assert
        mockMvc.perform(post("/api/json/sort")
                        .queryParam("sortByField", sortByField)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(sortedJson))
                .andDo(document(
                        "sort-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Sorts the JSON object keys alphabetically")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/find endpoint
     */
    @Test
    void testFindInJson_validJson_returnsMatchingPaths_andGenerateDocs() throws Exception {
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
        String searchTerm = "Bob Johnson";

        // Expected matching paths (example)
        List<String> expectedPaths = List.of(
                "$.companies[0].employees[1].name (value)",
                "$.companies[0].departments.engineering.head (value)"
        );

        when(jsonService.findInJson(json, searchTerm)).thenReturn(expectedPaths);

        // Act & Assert
        mockMvc.perform(post("/api/json/find")
                        .queryParam("searchTerm", searchTerm)
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedPaths)))
                .andDo(document(
                        "find-in-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Searches for a term in the JSON (keys or values) and returns matching JSON paths")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/json-to-csv endpoint
     */
    @Test
    void testConvertJsonToCsv_validJson_returnsCsv_andGenerateDocs() throws Exception {
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

        String csvOutput = """
                company_name,founded,employee_id,employee_name
                Tech Solutions Inc.,1998,1,Alice Smith
                Tech Solutions Inc.,1998,2,Bob Johnson
                Future Innovations Ltd.,2005,1,Jane Doe
                Future Innovations Ltd.,2005,2,Mark Twain
                """.trim();

        when(jsonService.convertJsonToCsv(json)).thenReturn(csvOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/json-to-csv")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(csvOutput))
                .andDo(document(
                        "convert-json-to-csv",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts a JSON array of objects to CSV format")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/csv-to-json endpoint
     */
    @Test
    void testConvertCsvToJson_validCsv_returnsJson_andGenerateDocs() throws Exception {
        // Arrange
        String csvInput = """
                Company,Founded,Employee ID,Employee Name,Employee Title,Email,Phone,Street,City,State,Zip,Lat,Lng,Project ID,Project Name,Project Status,Project Budget,Milestone ID,Milestone Description,Milestone Due Date,Milestone Completed
                Tech Solutions Inc.,1998,1,Alice Smith,CEO,alice.smith@techsolutions.com,+1-555-1234,123 Tech Lane,Innovate City,CA,90001,34.0522,-118.2437,p-101,AI Assistant,active,1200000.5,m-001,Initial Research,2025-06-30,True
                Tech Solutions Inc.,1998,1,Alice Smith,CEO,alice.smith@techsolutions.com,+1-555-1234,123 Tech Lane,Innovate City,CA,90001,34.0522,-118.2437,p-101,AI Assistant,active,1200000.5,m-002,Prototype Development,2025-09-15,False
                Tech Solutions Inc.,1998,2,Bob Johnson,CTO,bob.johnson@techsolutions.com,+1-555-5678,456 Dev Avenue,Code Town,NY,10001,40.7128,-74.006,,,,,,,,
                Future Innovations Ltd.,2005,1,Jane Doe,Founder,jane.doe@futureinnovations.com,+44-20-9876,789 Future Blvd,Tech Haven,TX,73301,30.2672,-97.7431,p-201,Smart Home Automation,completed,2000000,m-101,Market Research,2024-05-15,True
                Future Innovations Ltd.,2005,1,Jane Doe,Founder,jane.doe@futureinnovations.com,+44-20-9876,789 Future Blvd,Tech Haven,TX,73301,30.2672,-97.7431,p-201,Smart Home Automation,completed,2000000,m-102,Prototype Deployment,2024-12-01,True
                Future Innovations Ltd.,2005,2,Mark Twain,COO,mark.twain@futureinnovations.com,+44-20-5432,321 Innovation Road,Tech Haven,TX,73301,30.2672,-97.7431,,,,,,,,
                """.trim();

        String expectedJsonOutput = """
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

        when(jsonService.convertCsvToJson(csvInput)).thenReturn(expectedJsonOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/csv-to-json")
                        .contentType("text/csv")
                        .content(csvInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput))
                .andDo(document(
                        "convert-csv-to-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `text/csv`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts a CSV string to a JSON array of objects")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/json-to-xml endpoint
     */
    @Test
    void testConvertJsonToXml_validJson_returnsXml_andGenerateDocs() throws Exception {
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

        String expectedXmlOutput = """
                <companies>
                  <company>
                    <name>Tech Solutions Inc.</name>
                    <founded>1998</founded>
                    <employees>
                      <employee>
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
                          <project>
                            <projectId>p-101</projectId>
                            <name>AI Assistant</name>
                            <status>active</status>
                            <budget>1200000.5</budget>
                            <milestones>
                              <milestone>
                                <milestoneId>m-001</milestoneId>
                                <description>Initial Research</description>
                                <dueDate>2025-06-30</dueDate>
                                <completed>true</completed>
                              </milestone>
                              <milestone>
                                <milestoneId>m-002</milestoneId>
                                <description>Prototype Development</description>
                                <dueDate>2025-09-15</dueDate>
                                <completed>false</completed>
                              </milestone>
                            </milestones>
                          </project>
                        </projects>
                      </employee>
                      <employee>
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
                        <projects/>
                      </employee>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Bob Johnson</head>
                        <teams>
                          <team>
                            <teamName>Backend</teamName>
                            <members>
                              <member>Charlie</member>
                              <member>Dana</member>
                              <member>Eli</member>
                            </members>
                            <technologies>
                              <technology>Node.js</technology>
                              <technology>Python</technology>
                              <technology>PostgreSQL</technology>
                            </technologies>
                          </team>
                          <team>
                            <teamName>Frontend</teamName>
                            <members>
                              <member>Fiona</member>
                              <member>George</member>
                            </members>
                            <technologies>
                              <technology>React</technology>
                              <technology>Vue</technology>
                              <technology>HTML5</technology>
                              <technology>CSS3</technology>
                            </technologies>
                          </team>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Grace Lee</head>
                        <teams/>
                      </hr>
                    </departments>
                    <offices>
                      <office>
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
                      </office>
                      <office>
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
                      </office>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>5600000</revenue>
                      <expenses>3200000</expenses>
                      <profit>2400000</profit>
                      <currency>USD</currency>
                      <investors>
                        <investor>
                          <name>Capital Ventures</name>
                          <investment>1000000</investment>
                          <equity>10</equity>
                        </investor>
                        <investor>
                          <name>Tech Angels</name>
                          <investment>500000</investment>
                          <equity>5</equity>
                        </investor>
                      </investors>
                    </financials>
                  </company>
                
                  <company>
                    <name>Future Innovations Ltd.</name>
                    <founded>2005</founded>
                    <employees>
                      <employee>
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
                          <project>
                            <projectId>p-201</projectId>
                            <name>Smart Home Automation</name>
                            <status>completed</status>
                            <budget>2000000</budget>
                            <milestones>
                              <milestone>
                                <milestoneId>m-101</milestoneId>
                                <description>Market Research</description>
                                <dueDate>2024-05-15</dueDate>
                                <completed>true</completed>
                              </milestone>
                              <milestone>
                                <milestoneId>m-102</milestoneId>
                                <description>Prototype Deployment</description>
                                <dueDate>2024-12-01</dueDate>
                                <completed>true</completed>
                              </milestone>
                            </milestones>
                          </project>
                        </projects>
                      </employee>
                      <employee>
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
                        <projects/>
                      </employee>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Mark Twain</head>
                        <teams>
                          <team>
                            <teamName>Cloud</teamName>
                            <members>
                              <member>Liam</member>
                              <member>Emma</member>
                              <member>Olivia</member>
                            </members>
                            <technologies>
                              <technology>AWS</technology>
                              <technology>Azure</technology>
                              <technology>Google Cloud</technology>
                            </technologies>
                          </team>
                          <team>
                            <teamName>IoT</teamName>
                            <members>
                              <member>Noah</member>
                              <member>Ava</member>
                            </members>
                            <technologies>
                              <technology>Raspberry Pi</technology>
                              <technology>Arduino</technology>
                              <technology>MQTT</technology>
                            </technologies>
                          </team>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Sophie Turner</head>
                        <teams/>
                      </hr>
                    </departments>
                    <offices>
                      <office>
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
                      </office>
                      <office>
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
                      </office>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>8000000</revenue>
                      <expenses>4500000</expenses>
                      <profit>3500000</profit>
                      <currency>GBP</currency>
                      <investors>
                        <investor>
                          <name>Global Tech Fund</name>
                          <investment>2000000</investment>
                          <equity>15</equity>
                        </investor>
                        <investor>
                          <name>Startup Angels</name>
                          <investment>750000</investment>
                          <equity>7</equity>
                        </investor>
                      </investors>
                    </financials>
                  </company>
                </companies>
                """;

        when(jsonService.convertJsonToXml(json)).thenReturn(expectedXmlOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/json-to-xml")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().xml(expectedXmlOutput))
                .andDo(document(
                        "convert-json-to-xml",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts JSON to XML format")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/xml-to-json endpoint
     */
    @Test
    void testConvertXmlToJson_validXml_returnsJson_andGenerateDocs() throws Exception {
        // Arrange
        String xmlInput = """
                <companies>
                  <company>
                    <name>Tech Solutions Inc.</name>
                    <founded>1998</founded>
                    <employees>
                      <employee>
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
                          <project>
                            <projectId>p-101</projectId>
                            <name>AI Assistant</name>
                            <status>active</status>
                            <budget>1200000.5</budget>
                            <milestones>
                              <milestone>
                                <milestoneId>m-001</milestoneId>
                                <description>Initial Research</description>
                                <dueDate>2025-06-30</dueDate>
                                <completed>true</completed>
                              </milestone>
                              <milestone>
                                <milestoneId>m-002</milestoneId>
                                <description>Prototype Development</description>
                                <dueDate>2025-09-15</dueDate>
                                <completed>false</completed>
                              </milestone>
                            </milestones>
                          </project>
                        </projects>
                      </employee>
                      <employee>
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
                        <projects/>
                      </employee>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Bob Johnson</head>
                        <teams>
                          <team>
                            <teamName>Backend</teamName>
                            <members>
                              <member>Charlie</member>
                              <member>Dana</member>
                              <member>Eli</member>
                            </members>
                            <technologies>
                              <technology>Node.js</technology>
                              <technology>Python</technology>
                              <technology>PostgreSQL</technology>
                            </technologies>
                          </team>
                          <team>
                            <teamName>Frontend</teamName>
                            <members>
                              <member>Fiona</member>
                              <member>George</member>
                            </members>
                            <technologies>
                              <technology>React</technology>
                              <technology>Vue</technology>
                              <technology>HTML5</technology>
                              <technology>CSS3</technology>
                            </technologies>
                          </team>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Grace Lee</head>
                        <teams/>
                      </hr>
                    </departments>
                    <offices>
                      <office>
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
                      </office>
                      <office>
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
                      </office>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>5600000</revenue>
                      <expenses>3200000</expenses>
                      <profit>2400000</profit>
                      <currency>USD</currency>
                      <investors>
                        <investor>
                          <name>Capital Ventures</name>
                          <investment>1000000</investment>
                          <equity>10</equity>
                        </investor>
                        <investor>
                          <name>Tech Angels</name>
                          <investment>500000</investment>
                          <equity>5</equity>
                        </investor>
                      </investors>
                    </financials>
                  </company>
                
                  <company>
                    <name>Future Innovations Ltd.</name>
                    <founded>2005</founded>
                    <employees>
                      <employee>
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
                          <project>
                            <projectId>p-201</projectId>
                            <name>Smart Home Automation</name>
                            <status>completed</status>
                            <budget>2000000</budget>
                            <milestones>
                              <milestone>
                                <milestoneId>m-101</milestoneId>
                                <description>Market Research</description>
                                <dueDate>2024-05-15</dueDate>
                                <completed>true</completed>
                              </milestone>
                              <milestone>
                                <milestoneId>m-102</milestoneId>
                                <description>Prototype Deployment</description>
                                <dueDate>2024-12-01</dueDate>
                                <completed>true</completed>
                              </milestone>
                            </milestones>
                          </project>
                        </projects>
                      </employee>
                      <employee>
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
                        <projects/>
                      </employee>
                    </employees>
                    <departments>
                      <engineering>
                        <head>Mark Twain</head>
                        <teams>
                          <team>
                            <teamName>Cloud</teamName>
                            <members>
                              <member>Liam</member>
                              <member>Emma</member>
                              <member>Olivia</member>
                            </members>
                            <technologies>
                              <technology>AWS</technology>
                              <technology>Azure</technology>
                              <technology>Google Cloud</technology>
                            </technologies>
                          </team>
                          <team>
                            <teamName>IoT</teamName>
                            <members>
                              <member>Noah</member>
                              <member>Ava</member>
                            </members>
                            <technologies>
                              <technology>Raspberry Pi</technology>
                              <technology>Arduino</technology>
                              <technology>MQTT</technology>
                            </technologies>
                          </team>
                        </teams>
                      </engineering>
                      <hr>
                        <head>Sophie Turner</head>
                        <teams/>
                      </hr>
                    </departments>
                    <offices>
                      <office>
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
                      </office>
                      <office>
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
                      </office>
                    </offices>
                    <financials>
                      <fiscalYear>2024</fiscalYear>
                      <revenue>8000000</revenue>
                      <expenses>4500000</expenses>
                      <profit>3500000</profit>
                      <currency>GBP</currency>
                      <investors>
                        <investor>
                          <name>Global Tech Fund</name>
                          <investment>2000000</investment>
                          <equity>15</equity>
                        </investor>
                        <investor>
                          <name>Startup Angels</name>
                          <investment>750000</investment>
                          <equity>7</equity>
                        </investor>
                      </investors>
                    </financials>
                  </company>
                </companies>
                """;

        String expectedJsonOutput = """
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

        when(jsonService.convertXmlToJson(xmlInput)).thenReturn(expectedJsonOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/xml-to-json")
                        .contentType("application/xml")
                        .content(xmlInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput))
                .andDo(document(
                        "convert-xml-to-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/xml`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts XML to JSON format")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/json-to-excel endpoint
     */
    @Test
    void testConvertJsonToExcel_validJson_returnsBase64Excel_andGenerateDocs() throws Exception {
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

        String base64ExcelOutput = "UEsDBBQABgA...";

        when(jsonService.convertJsonToExcel(json)).thenReturn(base64ExcelOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/json-to-excel")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(base64ExcelOutput))
                .andDo(document(
                        "convert-json-to-excel",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts a JSON array of objects to an Excel file (as a Base64-encoded string)")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/excel-to-json endpoint
     */
    @Test
    void testConvertExcelToJson_validBase64Excel_returnsJsonArray_andGenerateDocs() throws Exception {
        // Arrange
        String base64ExcelInput = "UEsDBBQABgA...";
        String expectedJsonOutput = """
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

        when(jsonService.convertExcelToJson(base64ExcelInput)).thenReturn(expectedJsonOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/excel-to-json")
                        .contentType("application/json")
                        .content(base64ExcelInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput))
                .andDo(document(
                        "convert-excel-to-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts a Base64-encoded Excel file to a JSON array of objects")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/json-to-yaml endpoint
     */
    @Test
    void testConvertJsonToYaml_validJson_returnsYaml_andGenerateDocs() throws Exception {
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

        String expectedYamlOutput = """
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
                              lng: -
                """;

        when(jsonService.convertJsonToYaml(json)).thenReturn(expectedYamlOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/json-to-yaml")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedYamlOutput))
                .andDo(document(
                        "convert-json-to-yaml",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts JSON to YAML format")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/yaml-to-json endpoint
     */
    @Test
    void testConvertYamlToJson_validYaml_returnsJson_andGenerateDocs() throws Exception {
        // Arrange
        String yamlInput = """
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
                """;

        String expectedJsonOutput = """
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

        when(jsonService.convertYamlToJson(yamlInput)).thenReturn(expectedJsonOutput);

        // Act & Assert
        mockMvc.perform(post("/api/json/yaml-to-json")
                        .contentType("application/x-yaml")
                        .content(yamlInput))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonOutput))
                .andDo(document(
                        "convert-yaml-to-json",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/x-yaml`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Converts YAML to JSON format")
                                        .build()
                        )
                ));
    }

    /**
     * Tests the /json/generate-type-definitions endpoint
     */
    @Test
    void testGenerateTypeDefinitions_validJson_returnsTypeDefinitions_andGenerateDocs() throws Exception {
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
                  ]
                }
                """;

        String expectedTypeDefs = """
                interface RootObject {
                  companies: Company[];
                }
                interface Company {
                  name: string;
                  founded: number;
                  employees: Employee[];
                  departments: Departments;
                  offices: Office[];
                  financials: Financials;
                }
                interface Employee {
                  id: number;
                  name: string;
                  title: string;
                  contact: Contact;
                  projects: Project[];
                }
                interface Contact {
                  email: string;
                  phone: string;
                  address: Address;
                }
                interface Address {
                  street: string;
                  city: string;
                  state: string;
                  zip: string;
                  coordinates: Coordinates;
                }
                interface Coordinates {
                  lat: number;
                  lng: number;
                }
                interface Project {
                  projectId: string;
                  name: string;
                  status: string;
                  budget: number;
                  milestones: Milestone[];
                }
                interface Milestone {
                  milestoneId: string;
                  description: string;
                  dueDate: string;
                  completed: boolean;
                }
                interface Departments {
                  engineering: Department;
                  hr: Department;
                }
                interface Department {
                  head: string;
                  teams: Team[];
                }
                interface Team {
                  teamName: string;
                  members: string[];
                  technologies: string[];
                }
                interface Office {
                  location: string;
                  capacity: number;
                  openSince: string;
                  facilities: Facilities;
                }
                interface Facilities {
                  cafeteria: boolean;
                  gym: boolean;
                  parking: Parking;
                }
                interface Parking {
                  available: boolean;
                  spots: number;
                }
                interface Financials {
                  fiscalYear: number;
                  revenue: number;
                  expenses: number;
                  profit: number;
                  currency: string;
                  investors: Investor[];
                }
                interface Investor {
                  name: string;
                  investment: number;
                  equity: number;
                }
                """;

        when(jsonService.generateTypeDefinitionsFromJson(json)).thenReturn(expectedTypeDefs);

        // Act & Assert
        mockMvc.perform(post("/api/json/generate-type-definitions")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedTypeDefs))
                .andDo(document(
                        "generate-type-difinition",
                        requestBody(),
                        responseBody(),
                        requestHeaders(
                                headerWithName("Content-Type").description("The content type, which must be `application/json`.")
                        ),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .description("Generates TypeScript type definitions and documentation from JSON")
                                        .build()
                        )
                ));
    }

}
