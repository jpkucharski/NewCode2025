package org.jpk.CucumberInSepModuleSpringBootMaven.controller;

import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.StorageClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link UploadRestController}
 * Standalone MockMvc to test ony REST_Controller behavior.
 * Without usage of application-test.properties
 */

@ExtendWith(MockitoExtension.class)
class UploadRestControllerTest {

    private final static String TEST_FILE_NAME = "TestFileName";
    private final static String STATUS_RESPONSE = "Service is UP!";

    private MockMvc mockMvc;

    @Mock
    private StorageClient storageClient;

    @InjectMocks
    private UploadRestController uploadRestController;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(uploadRestController).build();
    }

    /**
     * Test of functionality for upload method in RestController.
     * And verification of correct StorageClient invocation.
     * @throws Exception
     */
    @Test
    public void uploadingFile_shouldUploadItCorrectly() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/upload")
                .param("fileName", TEST_FILE_NAME))
                .andExpect(status().isOk())
                .andExpect(content().string("Uploaded: " + TEST_FILE_NAME))
                .andReturn();
        verify(storageClient, times(1)).upload(TEST_FILE_NAME, "DUMMY DATA".getBytes());
    }

    /**
     * Test for status method in RestController.
     * @throws Exception
     */
    @Test
    public void callStatus_shouldResponseStatusOkAndCorrectMessage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/status"))
                .andExpect(status().isOk())
                .andExpect(content().string(STATUS_RESPONSE))
                .andReturn();
    }
}