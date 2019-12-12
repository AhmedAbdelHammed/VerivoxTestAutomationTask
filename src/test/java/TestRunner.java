import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = {"src/test/resources"}, glue = "StepDefs",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/report.html"})

public class TestRunner extends AbstractTestNGCucumberTests {
}
