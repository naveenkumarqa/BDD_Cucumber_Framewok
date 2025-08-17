package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
    	if (extent == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/ExtentReport.html");
			reporter.config().setDocumentTitle("BDD Automation Report");
			reporter.config().setReportName("UAT Results");
			reporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("QA Engineer", "Naveen");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("Browser", "Chrome");
		}
        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = getExtentReports().createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
