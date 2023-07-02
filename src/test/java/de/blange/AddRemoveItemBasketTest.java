package de.blange;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)

public class AddRemoveItemBasketTest extends Hooks {

	public AddRemoveItemBasketTest() throws IOException {
		super();
	}

	@Test
	public void addRemoveItem() throws IOException {
		ExtentManager.log("Starting AddRemoveItemBasketTest ...");
		Homepage home = new Homepage();
		home.getCookie().click();
		// Ist sidebar sichtbar?
//		if (home.getSidebar().getCssValue("class").equals("")) {
//			home.getToggle().click();
//			JavascriptExecutor js = (JavascriptExecutor) getDriver();
//			js.executeScript("arguments[0].scrollIntoView();", home.getTestStoreLink());
//		}
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the shop homepage.");
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the product page.");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Successfully selected size.");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Successfully increased quantity.");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Successfully added product to basket.");

		ShopContentPanel cPanel = new ShopContentPanel();
		WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOf(cPanel.getContinueShopBtn()));
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();

//		WebDriverWait wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.stalenessOf(cart.getDeleteItemTwo()));

		System.out.println(cart.getTotalAmount().getText());
		String expectedTotalAmount = "$45.14";
		try {
			Assert.assertEquals(cart.getTotalAmount().getText(), expectedTotalAmount);
			ExtentManager.pass("The total amount matches th eexpected amount.");
		} catch (AssertionError e) {
			Assert.fail("The total amount does not match the expected amount. "
					+ "It found " + cart.getTotalAmount().getText() + ", expected amount: " + expectedTotalAmount);
			ExtentManager.fail("\"The total amount does not match the expected amount."); 
		}

	}
}
