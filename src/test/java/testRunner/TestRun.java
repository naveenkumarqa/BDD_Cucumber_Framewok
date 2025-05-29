package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/Features",
		glue="stepDefinitions",
		dryRun=false,
		monochrome=true,
		tags= "@Smoke",
		plugin= {
				"pretty",
				"html:reports/cucumber-report.html",  
		}
		)

public class TestRun {}
