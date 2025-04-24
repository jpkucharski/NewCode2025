package org.jpk;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class) //->>JUnit4
@CucumberOptions(
        features = "src/test/resources",
        glue = "org.jpk"
)
public class CucumberRunner {
}
