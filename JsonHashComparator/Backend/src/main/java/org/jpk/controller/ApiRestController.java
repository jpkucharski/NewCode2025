package org.jpk.controller;

import org.jpk.model.RequestPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Main API controller for backend application.
 */
@RestController
@RequestMapping("/api")
public class ApiRestController {

    private final String secretKey;

    public ApiRestController(@Value("${server.config.secret-key}") String secret) {
        this.secretKey = secret;
    }

    /**
     * API endpoint used for healthcheck.
     * @return information about app status as String body.
     */
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Application is running!");
    }

    /**
     * API endpoint to verify received HMAC with internally calculated one.
     * @param requestPayload contains code calculated by frontend and payload used to create it.
     * @return String with statuses: verified, failed, error.
     */
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody RequestPayload requestPayload) {
        try {
            String computedHash = hmacSha256(requestPayload.getJson(), secretKey);
            if (computedHash.equals(requestPayload.getHash())) {
                return ResponseEntity.ok(
                        "{" +
                                "\"status\": \"verified\", " +
                                "\"Hash frontend\": \"" + requestPayload.getHash() + "\", " +
                                "\"Hash backend\": \"" + computedHash + "\"" +
                                "}"
                );

            } else {
                return ResponseEntity.ok().body("{\"status\": \"failed\", \"message\": \"Hash mismatch\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Method for calculating HMAC using SHA256 algorithm.
     * @param data information that will be hashed.
     * @param key additional key that will help obtain unique hash.
     * @return hashCOde as a String.
     */
    private String hmacSha256(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            byte[] hash = sha256_HMAC.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e){
            throw new RuntimeException(e);
        }
    }
}