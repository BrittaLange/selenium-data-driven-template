package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Diese Klasse ist für die Initialisierung des Webdrivers verantwortlich.
 * Die Ausführung von parallelen Tests ist möglich durch die Verwendung von ThreadLocal.
 * Für jeden WebDriver wird ein Thread gestartet.
 * 
 * @author Britta
 *
 */
public class WebDriverInstance {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	/**
	 * Wenn dem Thread noch keine Webdriver-Instanz zugewiesen bekommen wurde,
	 * wird eine neue Instanz nur für diesen Thread erzeugt.
	 * @return der Webdriver für den zugehörigen Thread.
	 */
	public static WebDriver getDriver() {
		// Hat der Thread noch keinen WebDriver, dann wird der Kopie des Thread-lokalen WebDrivers des aktuellen 
		// Threads der neu erzeugte WebDriver zugewiesen.
		if(driver.get() == null) {
			try {
				driver.set(createDriver());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return driver.get();
	}
	
	public static WebDriver createDriver() throws IOException {
		System.out.println("New driver created!");
		WebDriver webdriver = null;
		
		// System.getProperty("user.dir") zeigt auf das oberste Verzeichnis dieses Projekts.
		Properties prop = new Properties();
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
		
		if(prop.getProperty("browser").equals("chrome")) {
			webdriver = new ChromeDriver();
		} else if(prop.getProperty("browser").equals("edge")) {
			webdriver = new EdgeDriver();
		} else if(prop.getProperty("browser").equals("firefox")) {
			webdriver = new FirefoxDriver();
		} else {
			System.out.println("Driver not found.");
		}
		
		webdriver.manage().window().maximize();
		// TODO Do not mix up implicit and explicit waits.
		webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return webdriver;
	}
	
	public static void cleanupDriver() {
		driver.get().quit();
		driver.remove();
	}
}
