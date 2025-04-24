package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This class will assign values from external yml file as an example for yml external file parser.
 * Remember if you are using .properties file for @PropertySource you don't need to implement such parser.
 */

@Configuration
@PropertySource(value = "file:configs/aws/yml-config-for-paser.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "db")
public class DbConfigFomYML {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
