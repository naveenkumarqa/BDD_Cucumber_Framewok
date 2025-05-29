package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticePage {

	WebDriver driver;
	
	public PracticePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[.='Practice']")
	WebElement title_practice;
	
	@FindBy(xpath="//a[.='Test Login Page']")
	WebElement lnk_login;
	
	public boolean verifyPracticePage() {
		return title_practice.isDisplayed();
	}
	
	public void clickLogin() {
		lnk_login.click();
	}
}