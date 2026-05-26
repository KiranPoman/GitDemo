package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtendReportNG;

public class Listners extends Base   implements ITestListener {

	ExtentReports extentReportNG = ExtendReportNG.getReportObject();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReportNG.createTest(result.getMethod().getMethodName());
		extentTestThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
		extentTestThread.get().log(Status.PASS, "Test Passed"+ result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTestThread.get().log(Status.FAIL, result.getThrowable());
		
		try {
			driver  = (WebDriver) result.getTestClass().getRealClass()
					.getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String filepath = getScreenshot(result.getMethod().getMethodName(), driver);
			extentTestThread.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}
	
	@Override
	public void onFinish(ITestContext result) {
		extentReportNG.flush();
	}

}
