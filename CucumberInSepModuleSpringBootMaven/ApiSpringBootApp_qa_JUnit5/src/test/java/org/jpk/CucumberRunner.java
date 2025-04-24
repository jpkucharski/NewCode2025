package org.jpk;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.jpk.stepDef, org.jpk.config")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")

public class CucumberRunner {
}
