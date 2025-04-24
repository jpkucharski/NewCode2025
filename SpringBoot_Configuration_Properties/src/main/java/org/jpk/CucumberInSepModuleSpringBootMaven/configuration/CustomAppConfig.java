package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Class is assigning correct properties data to correct Configuration instances.
 * Beans are created for further use.
 */

@Configuration
@PropertySources({
        @PropertySource("file:configs/aws/eureka-aws-config.properties"),
        @PropertySource("file:configs/aws/aws-config.properties")
})
//@PropertySource("classpath:custom-application.properties") <-- use if only one file need to be loaded
// works only for .properties file. For .yml you need yml custom parser class.
public class CustomAppConfig {

    @Value("${custom.value}")
    private String customValue;

    /**
     * It will create AppConfiguration instance with "app" prefix valuers and custom value.
     * @return AppConfiguration bean
     */
    @Bean
    @ConfigurationProperties(prefix = "app")
    public AppConfiguration appConfiguration(){
        AppConfiguration appConfiguration = new AppConfiguration();
        appConfiguration.setCustom(customValue);
        return appConfiguration;
    }

    /**
     * Will create EurekaConfiguration instance using "eureka" prefix data from properties file.
     * @return EurekaConfiguration bean
     */
    @Bean
    @ConfigurationProperties(prefix = "eureka")
    public EurekaConfiguration eurekaConfiguration(){
        return new EurekaConfiguration();

    }
}
