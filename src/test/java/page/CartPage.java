package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utils.AbstractComponent;

public class CartPage extends AbstractComponent {
	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOutProducts;

	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement countrySelection;

	@FindBy(css = ".action__submit")
	WebElement proceedToPay;

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	By selectCountry = By.cssSelector("[placeholder='Select Country']");
	By result = By.cssSelector(".ta-results");
	By cartProducts = By.cssSelector(".cartSection h3");

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = driver.findElements(cartProducts).stream()
				.anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}

	public void checkOutProducts() {

		checkOutProducts.click();

	}

	public void placeOrder(String country) throws InterruptedException {
		Thread.sleep(3000);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(selectCountry), country).build().perform();
		waitForElementToAappear(result);
		countrySelection.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", proceedToPay);

		proceedToPay.click();
	}

	public void confirmOrder(String expectedMessage) {
		Assert.assertTrue(confirmMessage.getText().equalsIgnoreCase(expectedMessage));

	}
}