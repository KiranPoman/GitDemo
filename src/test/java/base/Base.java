package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import page.LoginPage;

public class Base {

	public static WebDriver driver;

	public LoginPage landingPage;

	
	public WebDriver setup() throws IOException {
		
        Properties prop =new Properties();

        FileInputStream fis = new FileInputStream(
        System.getProperty("user.dir")
        + "//src//test//java//resources//GlobalData.properties");
        
        
        prop.load(fis);

       // String browserName =  prop.getProperty("browser");
        String browserName =  System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");

		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
				
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeSuite
	public LoginPage launchApplication() throws Exception {
		driver = setup();
		landingPage = new LoginPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//extentReports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);

		String path = System.getProperty("user.dir") + "//extentReports//" + testCaseName + ".png";

		return path;

	}

}
