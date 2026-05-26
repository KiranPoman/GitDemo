package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAappear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;

	public void waitForElementToDisappear(WebElement findBy) throws InterruptedException {
		Thread.sleep(1000);
		/*
		 * WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * w.until(ExpectedConditions.invisibilityOf(findBy));
		 */
	}

	public void goToCartPage() {
		cartHeader.click();
	}
}
