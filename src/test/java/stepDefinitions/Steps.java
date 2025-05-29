package stepDefinitions;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LobbyPage;
import pageObjects.LoginPage;
import pageObjects.PracticePage;
import utils.DriverUtils;

public class Steps extends BaseStep {

	@Before
	public void setUp(Scenario scenario) throws Exception {

		//property file
		prop = new Properties();
		FileInputStream file = new FileInputStream("src/test/resources/config.properties");
		prop.load(file);

		//logger file
		logger = LogManager.getLogger(Steps.class.getName());

		//extent report
		extest = DriverUtils.getExtentReports().createTest(scenario.getName());

		//driver
		if(prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			logger.info("Driver initiated");
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + prop.getProperty("browser"));
		}

		//wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}


	@Given("User launches the browser")
	public void user_launches_the_browser() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		driver.manage().window().maximize();
	}

	@Given("navigates to the application URL")
	public void navigates_to_the_application_url() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get(prop.getProperty("url"));
	}

	@When("User validates the Home Page")
	public void user_validates_the_home_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		hp = new HomePage(driver);
		hp.verifyHomePage();
	}

	@When("clicks on Practice")
	public void clicks_on_practice() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		hp.clickPractice();
	}

	@Then("User lands on the Practice Page")
	public void user_lands_on_the_practice_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		pp = new PracticePage(driver);
		pp.verifyPracticePage();
	}

	@When("User clicks on Test Login")
	public void user_clicks_on_test_login() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		pp.clickLogin();
	}

	@Then("User lands on the Test Page")
	public void user_lands_on_the_test_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lp = new LoginPage(driver);
		lp.isLoginPage();
	}

	@When("User navigates to Test URL")
	public void user_navigates_to_test_url() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		driver.get(prop.getProperty("test_url"));
		lp = new LoginPage(driver);
		lp.isLoginPage();
	}

	@When("User enters valid credentials")
	public void user_enters_valid_credentials() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		lp.enterCreds(username, password);
	}

	@When("clicks on Submit")
	public void clicks_on_submit() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lp.clickSubmit();	
	}

	@Then("User lands on the Login Success page")
	public void user_lands_on_the_login_success_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lbp = new LobbyPage(driver);
		lbp.isLoginSuccess();
	}

	@When("User clicks on Logout")
	public void user_clicks_on_logout() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lbp.clickLogOut();
	}

	@Then("User is logged out successfully")
	public void user_is_logged_out_successfully() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lp.isLoginPage();
	}

	@AfterStep
	public void afterEveryStep(Scenario scenario) {
		try {
			String methodName = DriverUtils.getCurrentMethodName();

			// Take screenshot as Base64
			String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			// Logging
			logger.info("Step: " + methodName);

			// Attach screenshot to Extent Report
			extest.pass("Step: " + methodName, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
		} catch (Exception e) {
			extest.warning("Failed to capture screenshot: " + e.getMessage());
		}
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {	 
			extest.fail("Scenario failed");
		} else {
			extest.pass("Scenario passed");
		}

		DriverUtils.getExtentReports().flush();
		driver.quit();
	}
}