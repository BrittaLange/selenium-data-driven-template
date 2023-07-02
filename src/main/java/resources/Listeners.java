package resources;

import base.BasePage;
import base.ExtentManager;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Delete scope in pom.xml to make testng visible in this package.
 * 
 * @author Britta
 *
 */
public class Listeners extends BasePage implements ITestListener {

	public Listeners() throws IOException {
		super();
	}
	
	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReport();
		ExtentManager.createTest(context.getName(),context.getName());
	}

	public synchronized void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail(result.getThrowable());
		try {
			System.out.println("Test failed: " + result.getName());
			takeSnapshot(result.getMethod().getMethodName());
			ExtentManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}
}