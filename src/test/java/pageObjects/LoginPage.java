package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="login")
	WebElement title_Login;
	
	@FindBy(id="username")
	WebElement field_username;
	
	@FindBy(id="password")
	WebElement field_password;
	
	@FindBy(id="submit")
	WebElement btn_submit;
	
	public boolean isLoginPage() {
		return title_Login.isDisplayed();
	}
	
	public void enterCreds(String uname, String pass) {
		field_username.sendKeys(uname);
		field_password.sendKeys(pass);
	}
	
	public void clickSubmit() {
		btn_submit.click();
	}
}