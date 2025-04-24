package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

/**
 * Configuration class for example Eureka service.
 * It is set in {@link CustomAppConfig}
 */

public class EurekaConfiguration {

    private String service;
    private String url;


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString(){
        return "service name: " + service + " url: " + url;
    }


}
