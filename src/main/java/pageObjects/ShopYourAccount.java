package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopYourAccount extends BasePage {

	WebDriver driver;
	
	By heading = By.cssSelector("h1");
	By signOutBtn = By.cssSelector(".hidden-sm-down.logout");

	
	public ShopYourAccount() throws IOException {
		super();
		this.driver = getDriver();
	}
	
	public WebElement getHeading() {
		return driver.findElement(heading);
	}
	
	public WebElement getSignOutBtn() {
		return driver.findElement(signOutBtn);
	}
	
}
