package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/Features",
		glue= {"stepDefinitions", "hooks"},
		dryRun=false,
		monochrome=true,
		tags= "@Smoke",
		plugin= {
				"pretty",
				"html:reports/cucumber-report.html",
				"json:reports/cucumber-report.json",
				 "rerun:target/rerun.txt"
		}
		)


public class TestRun {}
