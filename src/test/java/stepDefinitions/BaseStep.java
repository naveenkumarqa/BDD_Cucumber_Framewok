package stepDefinitions;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.Scenario;
import pageObjects.HomePage;
import pageObjects.LobbyPage;
import pageObjects.LoginPage;
import pageObjects.PracticePage;
import utils.DriverUtils;

public class BaseStep {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static Logger logger;
	public static ExtentReports extent;
	public static ExtentTest extest;

	public HomePage hp;
	public PracticePage pp;
	public LoginPage lp;
	public LobbyPage lbp;	

	public void initSetup (Scenario scenario) throws Exception {
		//property file
		prop = new Properties();
		FileInputStream file = new FileInputStream("src/test/resources/config.properties");
		prop.load(file);


		//logger file
		logger = LogManager.getLogger(Steps.class.getName());

		//extent report
		extest = DriverUtils.getExtentReports().createTest(scenario.getName());

		//driver
		if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			logger.info("Driver initiated");
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + prop.getProperty("browser"));
		}

		//driver wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}	
}