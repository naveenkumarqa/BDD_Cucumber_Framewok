package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LobbyPage {

	WebDriver driver;

	public LobbyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}


	@FindBy(xpath="//h1[contains(text(),'Successfully')]")
	WebElement txt_success;
	
	@FindBy(xpath="//a[text()='Log out']")
	WebElement btn_Logout;
	
	
	public boolean isLoginSuccess() {
		return txt_success.isDisplayed();
	}
	
	public void clickLogOut() {
		btn_Logout.click();
	}
}
