package org.jpk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS configuration class allowing body requests from browsers to {@link org.jpk.controller.ApiRestController}.
 */
@Configuration
public class CorsWebConfig {

    /**
     * CORS setup.
     * @return CORES rules bean.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // Frontend URL
                        .allowedMethods("GET", "POST");
            }
        };
    }
}
