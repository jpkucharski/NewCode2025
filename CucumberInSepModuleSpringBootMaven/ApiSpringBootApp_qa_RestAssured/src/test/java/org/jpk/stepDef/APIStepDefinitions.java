package org.jpk.stepDef;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APIStepDefinitions {

    private List<Response> responses = new ArrayList<>();
    private List<Map<String, String>> testData = new ArrayList<>();

    @LocalServerPort
    private int port;

    @Given("the service is up")
    public void the_service_is_up() {
        RestAssured.baseURI = "http://localhost:" + port + "/api";
    }

    @When("I request service status")
    public void I_request_service_status() {
        Response response = RestAssured
                .given()
                .when()
                .get("/status");
        responses.add(response);
    }

    @When("I send request to add fallowing numbers")
    public void I_send_request_to_add_fallowing_numbers(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : data) {
            int num1 = Integer.parseInt(row.get("a"));
            int num2 = Integer.parseInt(row.get("b"));


            Response response = RestAssured
                    .given().
                    queryParam("a", num1)
                    .queryParam("b", num2)
                    .when()
                    .post("/add");
            responses.add(response);
        }
    }

    @Then("the response status code is {int}")
    public void the_response_status_code_is(int expectedCode) {
        for (Response response : responses) {
            Assertions.assertEquals(expectedCode, response.getStatusCode());
        }
    }

    @And("the response message is correct")
    public void the_response_message_is_correct() {
        if (responses.size() == testData.size()) {
            for (int i = 0; i < responses.size(); i++) {
                String actual = responses.get(i).getBody().asString();
                String expected = testData.get(i).get("sum");
                Assertions.assertEquals(expected, actual, "Response body dont math expected sum.");
            }
        }
    }

    @And("the response message is {string}")
    public void the_response_message_is(String responseMessage) {
        Assertions.assertEquals(responses.getFirst().getBody().asString(), responseMessage, "Response message is incorrect!");
    }
}
