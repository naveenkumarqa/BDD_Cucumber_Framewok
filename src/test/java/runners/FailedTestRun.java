package runners;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assume;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@target/rerun.txt", // '@' refers to read from file
    glue = "stepDefinitions",
    monochrome = true,
    plugin = {
        "pretty",
        "html:reports/cucumber-rerun-report.html",
        "json:reports/cucumber-rerun-report.json"
    }
)
public class FailedTestRun {
    static {
        try {
            if (!Files.exists(Paths.get("target/rerun.txt")) ||
                Files.size(Paths.get("target/rerun.txt")) == 0) {
                System.out.println("No failed scenarios found — skipping rerun");
                Assume.assumeTrue(false);
            }
        } catch (Exception e) {
            Assume.assumeTrue(false);
        }
    }
}
