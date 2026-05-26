package utils;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNG {

	public WebDriver driver;

	public static  ExtentReports extend;

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\extentReports\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation Results");
		report.config().setDocumentTitle("Test Results");

		extend = new ExtentReports();
		extend.attachReporter(report);
		extend.setSystemInfo("Tester", "Kiran");
		
		return extend;

	}

}
