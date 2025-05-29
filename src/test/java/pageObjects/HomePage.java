package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.DriverUtils;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[.='Hello']")
	WebElement txt_hello;
	
	@FindBy(xpath="//a[.='Practice']")
	WebElement txt_practice;
	
	public boolean verifyHomePage() {
		return txt_hello.isDisplayed();
	}	
	
	public void clickPractice() {
		DriverUtils.visibleWait(txt_practice);
		txt_practice.click();
	}
}