package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import stepDefinitions.BaseStep;

public class DriverUtils extends BaseStep {

	public static void visibleWait (WebElement locator) {
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

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

	
	/*  ******To achieve ThreadSafety*****
	private static final ThreadLocal<String> currentMethodName = new ThreadLocal<>();

	public static void setCurrentMethodName(String name) {
		currentMethodName.set(name);
	}

	public static String getCurrentMethodName() {
		return currentMethodName.get();
	}*/
	
	private static String currentMethodName;

	public static void setCurrentMethodName(String name) {
	    currentMethodName = name;
	}

	public static String getCurrentMethodName() {
	    return currentMethodName;
	}
}