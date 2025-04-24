package org.jpk.CucumberInSepModuleSpringBootMaven;



import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.AppConfiguration;

import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.DbConfigFomYML;
import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.EnvVariablesPassHolder;
import org.jpk.CucumberInSepModuleSpringBootMaven.configuration.EurekaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        try {
            AppConfiguration appConfiguration = context.getBean(AppConfiguration.class);
            EurekaConfiguration eurekaConfiguration = context.getBean(EurekaConfiguration.class);
            System.out.println("Data from applicationContext after creation of beans:");
            System.out.println("AppConfiguration: " + appConfiguration);
            System.out.println("Eureka configuration: " + eurekaConfiguration);
            System.out.println(context.getBean(EnvVariablesPassHolder.class).getPassword());
            System.out.println("DB username from external .yml file: \n"
                    + "Password: " + context.getBean(DbConfigFomYML.class).getPassword()
                    + "\n"
                    + "Username: " + context.getBean(DbConfigFomYML.class).getUsername());
        }
        finally {
            SpringApplication.exit(context);
            System.out.println("App will shut down!");
        }
    }

    /**
     * This method will print out all the beans that are created in SpringApp
     *
     * @param context ApplicationContext containing all Beans
     */
    private static void printBeansNamesFromContext(ConfigurableApplicationContext context) {
        String[] list = context.getBeanDefinitionNames();
        for (String beanName : list) {
            System.out.println(beanName);
        }
    }
}