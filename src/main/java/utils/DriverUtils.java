package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

import java.time.Duration;

public class DriverUtils  {
	
	
	private static final int DEFAULT_TIMEOUT = 10;

    private static WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    private static WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
    }

    public static WebElement waitForVisibility(WebElement element) {
        return getWait(DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebElement element) {
        return getWait(DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageTitle(String title) {
        getWait(DEFAULT_TIMEOUT).until(ExpectedConditions.titleContains(title));
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