package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopProductPage extends BasePage {

	WebDriver driver;

	By sizeOption = By.cssSelector("[data-product-attribute='1']");
	By quantityIncrease = By.cssSelector(".touchspin-up");
	By quantityDecrease = By.cssSelector(".touchspin-down");
	By addToCartBtn = By.cssSelector(".add-to-cart.btn.btn-primary");
	/*
	 * context item expression xpath
	 * https://stackoverflow.com/questions/38240763/xpath-difference-between-dot-and
	 * -text
	 */
	By homepageLink = By.xpath("//span[.='Home']");

	public ShopProductPage() throws IOException {
		super();
		this.driver = getDriver();
	}

	public WebElement getSizeOption() {
		return driver.findElement(sizeOption);
	}

	public WebElement getQuantIncrease() {
		return driver.findElement(quantityIncrease);
	}

	public WebElement getQuantDecrease() {
		return driver.findElement(quantityDecrease);
	}

	public WebElement getAddToCartBtn() {
		return driver.findElement(addToCartBtn);
	}

	public WebElement getHomepageLink() {
		return driver.findElement(homepageLink);
	}
}
