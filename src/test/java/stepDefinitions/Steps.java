package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.ConfigLoader;
import driver.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LobbyPage;
import pageObjects.LoginPage;
import pageObjects.PracticePage;
import utils.DriverUtils;
import static org.junit.Assert.assertTrue;

public class Steps {

	private static final Logger logger = LogManager.getLogger(Steps.class);

	private HomePage hp;
	private PracticePage pp;
	private LoginPage lp;
	private LobbyPage lbp;


	@Given("User launches the browser")
	public void user_launches_the_browser() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		DriverFactory.getDriver().manage().window().maximize();
		logger.info("Browser launched and maximized");
	}

	@Given("navigates to the application URL")
	public void navigates_to_the_application_url() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		String appUrl = ConfigLoader.getProperty("url");
		DriverFactory.getDriver().get(appUrl);
		logger.info("Navigated to application URL: {}", appUrl);
	}

	@When("User validates the Home Page")
	public void user_validates_the_home_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		hp = new HomePage(DriverFactory.getDriver());
		assertTrue("Home Page not displayed!", hp.verifyHomePage());
		logger.info("Home Page validated successfully");
	}

	@When("clicks on Practice")
	public void clicks_on_practice() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		hp.clickPractice();
		logger.info("Clicked on Practice link");
	}

	@Then("User lands on the Practice Page")
	public void user_lands_on_the_practice_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		pp = new PracticePage(DriverFactory.getDriver());
		assertTrue("Practice Page not displayed!", pp.verifyPracticePage());
		logger.info("Practice Page verified successfully");
	}

	@When("User clicks on Test Login")
	public void user_clicks_on_test_login() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		pp.clickLogin();
		logger.info("Clicked on Test Login link");
	}

	@Then("User lands on the Test Page")
	public void user_lands_on_the_test_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lp = new LoginPage(DriverFactory.getDriver());
		assertTrue("Login Page not displayed!", lp.isLoginPage());
		logger.info("Test Page validated successfully");
	}

	@When("User navigates to Test URL")
	public void user_navigates_to_test_url() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		String testUrl = ConfigLoader.getProperty("test_url");
		DriverFactory.getDriver().get(testUrl);
		lp = new LoginPage(DriverFactory.getDriver());
		assertTrue("Login Page not displayed after navigating!", lp.isLoginPage());
		logger.info("Navigated to Test URL: {}", testUrl);
	}

	@When("User enters valid credentials")
	public void user_enters_valid_credentials() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		String username = ConfigLoader.getProperty("username");
		String password = ConfigLoader.getProperty("password");
		lp.enterCreds(username, password);
		logger.info("Entered credentials: username={} password=******", username);
	}

	@When("clicks on Submit")
	public void clicks_on_submit() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lp.clickSubmit();
		logger.info("Clicked Submit button");
	}

	@Then("User lands on the Login Success page")
	public void user_lands_on_the_login_success_page() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lbp = new LobbyPage(DriverFactory.getDriver());
		assertTrue("Login Success page not displayed!", lbp.isLoginSuccess());
		logger.info("Login Success page verified");
	}

	@When("User clicks on Logout")
	public void user_clicks_on_logout() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		lbp.clickLogOut();
		logger.info("Clicked Logout button");
	}

	@Then("User is logged out successfully")
	public void user_is_logged_out_successfully() {
		DriverUtils.setCurrentMethodName(new Object(){}.getClass().getEnclosingMethod().getName());
		assertTrue("User is not logged out properly!", lp.isLoginPage());
		logger.info("Verified user is logged out successfully");
	}
}