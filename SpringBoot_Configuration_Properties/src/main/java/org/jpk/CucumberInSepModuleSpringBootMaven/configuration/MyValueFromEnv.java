package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class will assign value by using @Value from application.properties to "pass" variable
 * and after that override it with data from external secretFile.
 */


@Component
public class MyValueFromEnv {

    @Value("${spring.datasource.password}") //<--- Value from application.properties
    private String pass;


    /**
     * This method will read conf from configs/secrets/secretFile and set it as a pass.
     */
    @PostConstruct
    public void printPass() {
        System.out.println("Configuration inside @PostConstruct in @Component class:");
        System.out.println(pass);
        System.out.println("Overriding EnvVariable.");
        try {
            pass = Files.readString(Paths.get("configs/secrets/secretFile")).trim();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get the Secret from secretFile");

        }
        System.out.println(pass);
    }

    public String getPassword() {
        return pass;
    }

}
