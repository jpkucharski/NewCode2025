package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.Properties;

/**
 * Class to read external .yml config file used in {@link DbConfigFomYML}
 */

public class YamlPropertySourceFactory implements PropertySourceFactory {

    /**
     *
     * @param name
     * @param resource this is a source of external .yml file, not application.yml in resource folder
     * @return representation of .yml file that is readable for @PropertiesSource annotation
     * @throws IOException
     */
    @Override
    @NonNull
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        Properties properties = factory.getObject();

        String sourceName = name != null ? name : resource.getResource().getFilename();
        return new PropertiesPropertySource(
                sourceName != null ? sourceName : "application.yml",
                properties != null ? properties : new Properties()
        );
    }
}
