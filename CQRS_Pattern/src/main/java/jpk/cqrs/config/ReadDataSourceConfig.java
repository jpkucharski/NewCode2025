package jpk.cqrs.config;

import jpk.cqrs.config.constants.AppConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Configuration class for setting up the read data source in a CQRS-app.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "jpk.cqrs.repository.read", //read repository location
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)
public class ReadDataSourceConfig {

    private static final String APPLICATION_PROPERTIES_PREFIX = "spring.datasource.read"; //prefix for read operations
    private static final String MODEL_PACKAGE_LOCATION = AppConstants.MODEL_PACKAGE_LOCATION.getValue();

    @Bean
    @ConfigurationProperties(APPLICATION_PROPERTIES_PREFIX)
    public DataSourceProperties readDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource readDataSource() {
        return readDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(
            @Qualifier("readDataSource") DataSource readDataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(readDataSource);
        factory.setPackagesToScan(MODEL_PACKAGE_LOCATION);
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factory;
    }

    @Bean
    public JpaTransactionManager readTransactionManager(
            @Qualifier("readEntityManagerFactory") LocalContainerEntityManagerFactoryBean readEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(readEntityManagerFactory.getObject());
        return transactionManager;
    }
}
