package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.AwsS3Client;
import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.LocalStorageClient;
import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.MockS3Client;
import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.uploaderClient.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StorageConfig {

    @Value("${aws.accessKey}") private String accessKey;
    @Value("${aws.secretKey}") private String secretKey;
    @Value("${aws.region}") private String region;


    @Bean
    @Profile("dev")
    public StorageClient localStorageClient(){
        printProperties();
        return new LocalStorageClient();
    }

    @Bean
    @Profile("staging")
    public StorageClient mockS3Client(){
        printProperties();
        return new MockS3Client();
    }

    @Bean
    @Profile("prod")
    public StorageClient awsS3Client(){
        printProperties();
        return new AwsS3Client();
    }
    @Bean
    @Profile("test")
    public StorageClient testStorageClient() {
        printProperties();
        return (fileName, data) -> System.out.println("Test, Simulating file upload: " + fileName);
    }


    private void printProperties(){
        System.out.println(accessKey);
        System.out.println(secretKey);
        System.out.println(region);
    }
}
