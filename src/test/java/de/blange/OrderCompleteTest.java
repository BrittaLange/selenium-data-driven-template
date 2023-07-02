package de.blange;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShopContentPanel;
import pageObjects.ShoppingCart;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormShippingMethod;
import pageObjects.OrderFormPayment;

@Listeners(resources.Listeners.class)
public class OrderCompleteTest extends Hooks {

	public OrderCompleteTest() throws IOException {
		super();
	}

	@Test
	public void endToEndTest() throws IOException {
		ExtentManager.log("Starting OrderCompleteTest ...");
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
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Successfully added promo code.");

		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMrs().click();
		pInfo.getFirstNameField().sendKeys("Jane");
		pInfo.getLastnameField().sendKeys("McDuncan");
		pInfo.getEmailField().sendKeys("teste2e@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Successfully added personal information.");

		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("202 Baker Street");
		orderDelivery.getCityField().sendKeys("Pittsburgh");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Pennsylvania");
		orderDelivery.getPostcodeField().sendKeys("77542");
		orderDelivery.getContinueBtn().click();
		ExtentManager.pass("Successfully added delivery address.");

		OrderFormShippingMethod shippingMethod = new OrderFormShippingMethod();
		shippingMethod.getDeliveryMsgTextbox().sendKeys("If I am not in, please leave my delivery on my porch.");
		shippingMethod.getContinueBtn().click();
		ExtentManager.pass("Successfully added shipping details.");

		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Successfully ordered the delivery.");
	}
}
