package org.jpk.rest;


import org.jpk.service.Adder;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Best For Integration Tests.
 * SpringBootTest with WebEnvironment is using real web http server.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiRestControllerTest_SpringBootTest_WithRealWebServer {
    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private Adder adder;

    @Test
    public void addingTwoNumbers_ShouldReturnCorrectResult() {

        given(adder.addTwoNumbers(3, 7)).willReturn(10);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/add?a=3&b=7", null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(String.valueOf(10));
    }
}
