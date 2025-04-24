package org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient;

public class AwsS3Client implements StorageClient{


    @Override
    public void upload(String fileName, byte[] data) {
        System.out.println("Prod, Uploading to AWS S3: " + fileName);
    }
}
