package stepDefinitions;

import io.cucumber.java.*;
import io.cucumber.junit.CucumberOptions;
import runners.TestRun;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;

import driver.DriverFactory;
import utils.DriverUtils;
import utils.ReportManager;

public class Hooks {
	
	private String categoryFromRunner;

    @Before
    public void setUp(Scenario scenario) {
        // Initialize driver using default config inside DriverFactory
        DriverFactory.initDriver();
        
        // Determine category
        CucumberOptions opts = TestRun.class.getAnnotation(CucumberOptions.class);
        if (opts != null && opts.tags().length() > 0) {
            // Use tags from runner
            categoryFromRunner = String.join(",", opts.tags()).replaceAll("@", "");
        } else if (!scenario.getSourceTagNames().isEmpty()) {
            // Use tags from scenario
            categoryFromRunner = String.join(",", scenario.getSourceTagNames()).replaceAll("@", "");
        } else {
            // Fallback
            categoryFromRunner = "Default";
        }

        // Create Extent Report test
        ReportManager.createTest(scenario.getName());
        ReportManager.getTest().assignCategory(categoryFromRunner);
        ReportManager.getTest().createNode(scenario.getName());
    }

    @AfterStep
    public void afterEveryStep(Scenario scenario) {
        try {
            // Retrieve step name from DriverUtils
            String stepName = DriverUtils.getCurrentMethodName();

            // Take screenshot as Base64
            String base64Screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BASE64);

            // Log in Extent
            ReportManager.getTest().pass("Step: " + stepName,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        } catch (Exception e) {
            ReportManager.getTest().warning("Failed to capture screenshot: " + e.getMessage());
        }
    }


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ReportManager.getTest().fail("Scenario failed");
        } else {
            ReportManager.getTest().pass("Scenario passed");
        }

        // Flush report
        ReportManager.flushReports();

        // Quit driver
        DriverFactory.quitDriver();
    }
}