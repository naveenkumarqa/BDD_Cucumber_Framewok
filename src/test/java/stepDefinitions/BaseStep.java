package stepDefinitions;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pageObjects.HomePage;
import pageObjects.LobbyPage;
import pageObjects.LoginPage;
import pageObjects.PracticePage;

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
}
