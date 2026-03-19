package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features", 
    glue = {"steps"},                 
    plugin = {"pretty"}, 
     tags = "@random",
    strict = true                      
)
public class RunnerTest {}