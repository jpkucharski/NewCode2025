package org.jpk.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link ApiRestController} class including CORS settings {@link org.jpk.configuration.CorsWebConfig}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiRestController_IT {

    private static final String STATUS_RESPONSE = "Application is running!";

    private String verifyUrl;
    private String statusUrl;
    private HttpHeaders headers;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        verifyUrl = "http://localhost:" + port + "/api/verify";
        statusUrl = "http://localhost:" + port + "/api/status";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * Rest Controller /api/verify with Opened CORS Origin only for http://localhost:3000
     * @throws IOException
     */
    @DisplayName("CORS origin, method, code correct. Status OK, verified ")
    @Test
    public void requestWithCorrectHash_ShouldReturnOkWithVeryfiBody() throws IOException {
        Path jsonPath = Path.of(new ClassPathResource("request_success.json").getURI());
        String jsonBody = Files.readString(jsonPath);

        headers.add("Origin", "http://localhost:3000");

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(verifyUrl, entity, String.class);

        assertThat(response.getHeaders().getAccessControlAllowOrigin()).isEqualTo("http://localhost:3000");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("verified");
    }

    /**
     * RestController /api/verify with bad CORS origin.
     * @throws IOException
     */
    @DisplayName("CORS origin forbidden. Should return status FORBIDDEN.")
    @Test
    public void requestWithCorrectHash_ShouldReturnNullForCorsOrigin() throws IOException {
        Path jsonPath = Path.of(new ClassPathResource("request_success.json").getURI());
        String jsonBody = Files.readString(jsonPath);

        headers.add("Origin", "http://some-prohibited-origin:3000");
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(verifyUrl, entity, String.class);

        assertThat(response.getHeaders().getAccessControlAllowOrigin()).isEqualTo(null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    /**
     * RestController /api/verify with correct CORS origin.
     * @throws IOException
     */
    @DisplayName("Origin, method correct but code not. Expected fail.")
    @Test
    public void requestWithIncorrectCode_ShouldReturnBodyWithFailed() throws IOException {
        Path jsonPath = Path.of(new ClassPathResource("request_invalid.json").getURI());
        String jsonBody = Files.readString(jsonPath);

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(verifyUrl, entity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).contains("failed");
    }

    /**
     * RestController /api/verify CORS origin correct but method is not allowed.
     */
    @DisplayName("Test that DELETE method is rejected by CORS")
    @Test
    public void testCorsRejectsDeleteMethod() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Origin", "http://localhost:3000");
        headers.add("Access-Control-Request-Method", "DELETE");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(verifyUrl, HttpMethod.OPTIONS, entity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getHeaders().getAccessControlAllowMethods())
                .isNotNull()
                .doesNotContain(HttpMethod.DELETE);
    }

    /**
     * Rect Controller/api/status should return OK
     */
    @DisplayName("Test getStatus method. Return CorrectBody OK")
    @Test
    public void testGetStatus_ShouldReturnOKWithCorrectBody(){
        headers.add("Origin", "http://localhost:3000");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                statusUrl,
                HttpMethod.GET,
                entity,
                String.class
        );
        assertThat(response.getHeaders().getAccessControlAllowOrigin()).isEqualTo("http://localhost:3000");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(STATUS_RESPONSE);
    }
}