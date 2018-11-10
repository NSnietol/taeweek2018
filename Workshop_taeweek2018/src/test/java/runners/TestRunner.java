package runners;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue= {"stepDefinitions"},
        tags = {"~@Ignore"},
        plugin = {
                "json:target/cucumber-reports/CucumberTestReport.json"
        }
)
public class TestRunner {
}
