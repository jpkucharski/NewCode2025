package org.jpk.CucumberInSepModuleSpringBootMaven.controller;

import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.StorageClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main Rest Controller
 */
@RestController
public class UploadRestController {

    private final StorageClient storageClient;

    public UploadRestController(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

    /**
     * Simple status endpoint for healthcheck requests.
     * @return response in string and status OK.
     */
    @GetMapping("/status")
    public ResponseEntity<String> status(){
        return ResponseEntity.ok("Service is UP!");
    }

    /**
     *Method for allowing data to be saved in different places depends on profile param.
     */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam String fileName){
        storageClient.upload(fileName, "DUMMY DATA".getBytes());
        return ResponseEntity.ok("Uploaded: " + fileName);
    }
}
