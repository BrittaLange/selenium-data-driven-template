package base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends BasePage {

	private static ExtentReports extentReport;
	private static String extentReportPrefix;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public ExtentManager() throws IOException {
		super();
	}

	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}

	public synchronized static ExtentTest createTest(String name, String description) {
		ExtentTest test = extentReport.createTest(name, description);
		extentTest.set(test);
		return getTest();
	}

	/**
	 * Der Name des Extent Reports wird aus dem Namen des Projekts erzeugt.
	 * @return gibt einen ExtentRport zurück, wenn noch keiner existiert.
	 */
	public static ExtentReports getReport() {
		if (extentReport == null) {
			StringBuffer projectName = new StringBuffer(System.getProperty("user.dir"));
			projectName.reverse();
			int indexOfSlash = projectName.indexOf("\\");
			String name = projectName.substring(0, indexOfSlash);
			name = new StringBuffer(name).reverse().toString();
			System.out.println(name);
			setupExtentReport(name);
		}
		return extentReport;
	}

	private static ExtentReports setupExtentReport(String testName) {
		extentReport = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/report/" + extentReportsPrefix_Name(testName) + ".html");
		extentReport.attachReporter(spark);
		extentReport.setSystemInfo("Tester", "Tester Name");

		spark.config().setReportName("Regression");
		spark.config().setDocumentTitle("Test Results");
		spark.config().setTheme(Theme.STANDARD);

		return extentReport;
	}

	private static String extentReportsPrefix_Name(String testName) {
		String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		extentReportPrefix = date + "_" + testName;
		return extentReportPrefix;
	}

	public static void flushReport() {
		extentReport.flush();
	}

	// Logging Methoden.
	public synchronized static void log(String message) {
		getTest().info(message);
	}

	public synchronized static void pass(String message) {
		getTest().pass(message);
	}

	public synchronized static void fail(String message) {
		getTest().fail(message);
	}

	public synchronized static void attachImage() {
		getTest().addScreenCaptureFromPath(getScreenshotDestinationPath());
	}
}
