package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopContentPanel extends BasePage {

	WebDriver driver;

	By continueShoppingBtn = By.xpath("//button[contains(text(), 'Continue')]");
	By checkoutBtn = By.linkText("PROCEED TO CHECKOUT");

	public ShopContentPanel() throws IOException {
		super();
		this.driver = getDriver();
	}

	public WebElement getContinueShopBtn() {
		return driver.findElement(continueShoppingBtn);
	}

	public WebElement getCheckoutBtn() {
		return driver.findElement(checkoutBtn);
	}
}
