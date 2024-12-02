package StepDef;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features/flow.feature",
        glue = {"StepDef"},
        monochrome = true,
        plugin = {"pretty", "html:target/HTMLReport"}
)
public class TestRunner {

}
