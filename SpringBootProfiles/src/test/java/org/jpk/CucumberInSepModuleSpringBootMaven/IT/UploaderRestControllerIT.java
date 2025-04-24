package org.jpk.CucumberInSepModuleSpringBootMaven.IT;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Class for Integration tests (IT) it is using application-test.properties
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UploaderRestControllerIT {

    private static final String TEST_FILE = "integrationTest.txt";
    private static final String AWS_TEST_ACCESS_KEY = "test-access-key";
    private static final String AWS_TEST_SECRET_KEY = "test-secret-key";
    private static final String AWS_S3_BUCKET = "my-test-bucket";

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test of Rest Controller upload() method. With usage of  ApplicationContext.
     * @throws Exception
     */
    @Test
    public void shouldUploadTestProfileI() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/upload")
                .param("fileName", TEST_FILE))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Uploaded: "+ TEST_FILE);
    }

    /**
     * Additional test for conformation that application-test.properties was used.
     */
    @Test
    public void shouldReturnTestProfileProperty(){
        assertThat(awsAccessKey).isEqualTo(AWS_TEST_ACCESS_KEY);
        assertThat(awsSecretKey).isEqualTo(AWS_TEST_SECRET_KEY);
        assertThat(awsS3Bucket).isEqualTo(AWS_S3_BUCKET);
    }
}
