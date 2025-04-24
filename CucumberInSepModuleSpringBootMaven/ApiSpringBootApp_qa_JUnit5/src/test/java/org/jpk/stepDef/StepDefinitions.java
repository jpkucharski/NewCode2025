package org.jpk.stepDef;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("the application is running")
    public void the_application_is_running() {
        System.out.println("Given step: Application is running");
    }

    @When("I call the GET api status endpoint")
    public void i_call_the_get_api_status_endpoint() {
        System.out.println("When step: Calling API /api/status");
        response = restTemplate.getForEntity("/api/status", String.class);
    }

    @Then("I should receive a 200 OK response with message {string}")
    public void i_should_receive_a_200_ok_response_with_message(String expectedMessage) {
        System.out.println("Then step: Verifying response");
        assertEquals(200, response.getStatusCode().value(), "Response status code is incorrect");
        assertEquals(expectedMessage, response.getBody(), "Response body is incorrect");
    }

    @When("I send a POST request to {string} with parameters a={int} and b={int}")
    public void i_send_a_post_request_with_parameters(String endpoint, int a, int b) {
        System.out.println("When step: Calling API " + endpoint);
        String url = endpoint + "?a=" + a + "&b=" + b;
        response = restTemplate.postForEntity(url, null, String.class);
    }

    @Then("I should receive a {int} OK response with result {int}")
    public void i_should_receive_a_ok_response_with_result(int expectedStatus, int expectedSum) {
        System.out.println("Then step: Verifying response");
        assertEquals(String.valueOf(expectedStatus), String.valueOf(response.getStatusCode().value()), "Response status code is incorrect");
        assertEquals(String.valueOf(expectedSum), String.valueOf(response.getBody()), "Response body is incorrect");
    }
}
