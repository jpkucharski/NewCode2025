package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This Class will assign value to password variable
 * from application.properties spring.datasource.password_to_char
 */
@Component
public class EnvVariablesPassHolder {

    @Value("${spring.datasource.password_to_char}")
    private String passwordEnv;

    private char[] password;

    @PostConstruct
    public void init() {
        if (passwordEnv == null || passwordEnv.isEmpty()) {
            throw new IllegalStateException("Environmental variable BD_PASS_TO_CHAR is not set.");
        }

        password = passwordEnv.toCharArray();

        passwordEnv = null;

    }

    public char[] getPassword() {
        return password;
    }

    /**
     * This method will set char password after usage into null character's '\0'.
     */
    @PreDestroy
    public void clearPassword() {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }

}
