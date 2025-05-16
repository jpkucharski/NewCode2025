This app contains examples of:

Cucumber test implementation:

    - Cucumber7 + JUnit4
    - Cucumber7 + JUnit5
    - Cucumber7 + RestAssured

 JunitTests:

    - Spring MockMVS in standalone mode without loading spring context.
      No web server. Best for isolation tests of Controller.
      The best for JUnit of Controller code!

    - Spring MockMVC with ApplicationContext. No web server is deployed,
      but we are using internal Spring WebApplicationContext

    - @SpringBootTest
      @AutoConfigureMockMvc
      No web server is deployed. This application still
      is using internal web server with internal WebApplicationContext.
      similar to Spring MockMVC with WebApplicationContext

    - @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
      In this way of testing we are deploying and testing real http serve.
      Where the RestTemplate is preforming the request.
      The best for IntegrationTests!

    - Parameterized Tests example