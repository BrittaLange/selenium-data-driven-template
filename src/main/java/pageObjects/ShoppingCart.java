package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShoppingCart extends BasePage {

	WebDriver driver;
	
	By havePromo = By.cssSelector(".promo-code-button .collapse-button");
	By promoTextbox = By.cssSelector("input[name='discount_name']");
	By promoAddBtn = By.cssSelector("form[method='post']  span");
	By proceedToCheckoutBtn = By.cssSelector(".cart-detailed-actions .btn-primary");
	By deleteItemOne = By.cssSelector(".cart-items .cart-item:nth-of-type(1) .float-xs-left");
	By deleteItemTwo = By.cssSelector(".cart-items .cart-item:nth-of-type(2) .float-xs-left");
	By totalValue = By.cssSelector(".cart-total .value");
	
	public ShoppingCart() throws IOException {
		super();
		this.driver = getDriver();
	}
	
	public WebElement getHavePromo() {
		return driver.findElement(havePromo);
	}
	
	public WebElement getPromoTextbox() {
		return driver.findElement(promoTextbox);
	}

	public WebElement getPromoAddBtn() {
		return driver.findElement(promoAddBtn);
	}
	
	public WebElement getProceedCheckoutBtn() {
		return driver.findElement(proceedToCheckoutBtn);
	}
	
	public WebElement getDeleteItemOne() {
		return driver.findElement(deleteItemOne);
	}
	
	public WebElement getDeleteItemTwo() {
		return driver.findElement(deleteItemTwo);
	}
	
	public WebElement getTotalAmount() {
		return driver.findElement(totalValue);
	}
}
