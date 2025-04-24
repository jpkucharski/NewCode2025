package org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient;

public class MockS3Client implements StorageClient{
    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("[STAGING] Pretending to upload to S3: " + fileName);
    }
}
