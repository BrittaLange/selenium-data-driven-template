package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopLoginPage extends BasePage {

	WebDriver driver;
	
	By alert = By.cssSelector(".alert-danger");
	By email = By.cssSelector("section input[name='email']");
	By password = By.cssSelector("input[name='password']");
	By submitBtn = By.id("submit-login");

	
	public ShopLoginPage() throws IOException {
		super();
		this.driver = getDriver();
	}
	
	public WebElement getAlert() {
		return driver.findElement(alert);
	}
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getSubmitBtn() {
		return driver.findElement(submitBtn);
	}

}
