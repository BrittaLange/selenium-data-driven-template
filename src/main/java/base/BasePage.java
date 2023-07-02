package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private String url;
	private Properties prop;
	private static String screenshotDestinationPath;
	
	public BasePage() throws IOException {
		// System.getProperty("user.dir") zeigt auf das oberste Verzeichnis dieses Projekts, also auf "liveproject1".
		prop = new Properties();
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
	}

	public static WebDriver getDriver() {
		return WebDriverInstance.getDriver();
	}
	
	public String getUrl() {
		url = prop.getProperty("url");
		return url;
	}
	/**
	 * Nimmt einen Screenshot vom Browserfenster auf und speichert ihn unter resources\screenshots.
	 * @param webdriver
	 * @throws IOException
	 */
	public static String takeSnapshot(String name) throws IOException {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		screenshotDestinationPath = System.getProperty("user.dir") + "\\target\\screenshots\\" + timestamp() + "_" + name +".png";
		try {
			FileUtils.copyFile(srcFile, new File(screenshotDestinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * Erzeugt einen Zeitstempel, der in Dateinamen des Screenshots verwendet wird.
	 * @return eine Zeichenkette mit dem aktuellen Datum und Uhrzeit.
	 */
	private static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
	public static String getScreenshotDestinationPath() {
		return screenshotDestinationPath;
	}
	
}
