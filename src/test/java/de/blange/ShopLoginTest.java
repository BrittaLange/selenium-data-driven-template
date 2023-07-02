package de.blange;

import java.io.IOException;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopHomepage;
import pageObjects.ShopLoginPage;
import pageObjects.ShopYourAccount;

@Listeners(resources.Listeners.class)

public class ShopLoginTest extends Hooks {

	public ShopLoginTest() throws IOException {
		super();
	}

	@Test
	public void login() throws IOException {
		ExtentManager.log("Starting ShopLoginTest ...");
		Homepage home = new Homepage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the shop homepage.");
		shopHome.getSignInBtn().click();

		FileInputStream workbookLocation = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Erster Datensatz aus Reihe 1.
		Row row1 = sheet.getRow(1);
		Cell cellR1C0 = row1.getCell(0);
		Cell cellR1C1 = row1.getCell(1);
		String emailRow1 = cellR1C0.toString();
		String passwordRow1 = cellR1C1.toString();

		ShopLoginPage shopLogin = new ShopLoginPage();
		shopLogin.getEmail().sendKeys(emailRow1);
		shopLogin.getPassword().sendKeys(passwordRow1);
		shopLogin.getSubmitBtn().click();

		ShopYourAccount yourAcc = new ShopYourAccount();
		try {
			yourAcc.getSignOutBtn().click();
			ExtentManager.pass("User has logged in.");
		} catch (Exception e) {
			ExtentManager.fail("User access has been denied.");
			Assert.fail();
		}
		// Zweiter Datensatz: aus Reihe 2.
		Row row2 = sheet.getRow(2);
		Cell cellR2C0 = row2.getCell(0);
		Cell cellR2C1 = row2.getCell(1);
		String emailRow2 = cellR2C0.toString();
		String passwordRow2 = cellR2C1.toString();

		shopLogin.getEmail().sendKeys(emailRow2);
		shopLogin.getPassword().sendKeys(passwordRow2);
		shopLogin.getSubmitBtn().click();

		String expectedAlert = "Authentication failed.";
		Assert.assertEquals(shopLogin.getAlert().getText(), expectedAlert);
		ExtentManager.pass("Authentication failed with wrong credentials");
		shopLogin.getEmail().clear();
		
		// Dritter Datensatz: aus Reihe 3
		Row row3 = sheet.getRow(3);
		Cell cellR3C0 = row3.getCell(0);
		Cell cellR3C1 = row3.getCell(1);
		String emailRow3 = cellR3C0.toString();
		String passwordRow3 = cellR3C1.toString();

		shopLogin.getEmail().sendKeys(emailRow3);
		shopLogin.getPassword().sendKeys(passwordRow3);
		shopLogin.getSubmitBtn().click();
		
		Assert.assertEquals(shopLogin.getAlert().getText(), expectedAlert);
		ExtentManager.pass("Authentication failed with wrong credentials");

	}
}
