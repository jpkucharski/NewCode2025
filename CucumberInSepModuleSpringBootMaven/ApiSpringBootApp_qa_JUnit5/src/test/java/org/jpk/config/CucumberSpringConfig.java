package org.jpk.config;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.jpk.service.AdderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfig {

    @Autowired
    private AdderService adderService;   //<-- you can access all the beans form context in Hoocs class,
                                        // Also you can change them and the change will stay in them.





    @Before
    public void setUP(){                        //<--- it will be triggered before each Scenario !!!

    }

    @After
    public void tearDown(){                     //<--- it will be triggered after each Scenario !!!

    }
}
