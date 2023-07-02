package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopHomepage extends BasePage {

	WebDriver driver;
	
	By signinBtn = By.cssSelector("[title] .hidden-sm-down");
	By productOne = By.linkText("Hummingbird Printed T-Shirt");
	By productTwo = By.linkText("Hummingbird Printed Sweater");
	By productThree = By.linkText("The Best Is Yet To Come'...");
	By productFour = By.linkText("The Adventure Begins Framed...");
	By productFive = By.linkText("Today Is A Good Day Framed...");
	By productSix = By.linkText("Mug The Best Is Yet To Come");
	By productSeven = By.linkText("Mug The Adventure Begins");
	By productEight = By.linkText("Mug Today Is A Good Day");
	
	public ShopHomepage() throws IOException {
		super();
		this.driver = getDriver();
	}
	
	public WebElement getSignInBtn() {
		return driver.findElement(signinBtn);
	}
	
	public WebElement getProdOne() {
		return driver.findElement(productOne);
	}
	
	public WebElement getProdTwo() {
		return driver.findElement(productTwo);
	}
	
	public WebElement getProdThree() {
		return driver.findElement(productThree);
	}	
	
	public WebElement getProdFour() {
		return driver.findElement(productFour);
	}	

	public WebElement getProdFive() {
		return driver.findElement(productFive);
	}	
	
	public WebElement getProdSix() {
		return driver.findElement(productSix);
	}	
	
	public WebElement getProdSeven() {
		return driver.findElement(productSeven);
	}	
	
	public WebElement getProdEight() {
		return driver.findElement(productEight);
	}
}
