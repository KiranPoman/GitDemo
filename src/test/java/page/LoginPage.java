package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;

	
	public ProductPage loginApplication(String emailId, String pass) {
		email.sendKeys(emailId);
		password.sendKeys(pass);
		loginBtn.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
		}


	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		System.out.println("Create New Branch on GitHub and push to new branch");
		
	}	

	
}
