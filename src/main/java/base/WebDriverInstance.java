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
			System.out.println("Kein WebDriver in diesem Thread!");
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
		
		// System.getProperty("user.dir") zeigt auf das oberste Verzeichnis dieses Projekts, also auf "liveproject1".
		Properties prop = new Properties();
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
		
		if(prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
			webdriver = new ChromeDriver();
		} else if(prop.getProperty("browser").equals("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\msedgedriver.exe");
			webdriver = new EdgeDriver();
		} else if(prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\geckodriver.exe");
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
