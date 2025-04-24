package jpk.cqrs.config;

import jpk.cqrs.config.constants.AppConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Configuration class for setting up the write data source in a CQRS-app.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "jpk.cqrs.repository.write", // Repository location package
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
public class WriteDataSourceConfig {

    private static final String APPLICATION_PROPERTIES_PREFIX = "spring.datasource.write"; //prefix for write operations
    private static final String MODEL_PACKAGE_LOCATION = AppConstants.MODEL_PACKAGE_LOCATION.getValue();

    @Bean
    @ConfigurationProperties(APPLICATION_PROPERTIES_PREFIX)
    public DataSourceProperties writeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource writeDataSource() {
        return writeDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(
            @Qualifier("writeDataSource") DataSource writeDataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(writeDataSource);
        factory.setPackagesToScan(MODEL_PACKAGE_LOCATION);
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factory;
    }

    @Bean
    public JpaTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory") LocalContainerEntityManagerFactoryBean writeEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(writeEntityManagerFactory.getObject());
        return transactionManager;
    }
}
