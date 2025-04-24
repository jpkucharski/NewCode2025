package org.jpk.CucumberInSepModuleSpringBootMaven.configuration;

/**
 * ApplicationConfiguration class set up in {@link CustomAppConfig} class
 */


public class AppConfiguration {

    private String name;
    private String version;
    private String custom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    @Override
    public String toString() {
        return "name: " + name + " version: " + version + custom;
    }
}
