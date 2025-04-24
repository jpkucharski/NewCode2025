package org.jpk;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)  // Use this for JUnit 4 integration
@CucumberOptions(
        features = "src/test/resources",  // Path to your feature file(s)
        glue = "org/jpk/stepDef",  // Package for your step definition classes
        tags = "@Scenario_2"       // Features tags Filter, if deleted It will run everything
)
public class CucumberRunner {
}
