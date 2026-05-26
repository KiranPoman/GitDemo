package test;

import org.testng.annotations.Test;

import base.Base;
import base.RetryTest;
import page.CartPage;

public class CartTest extends Base {

	
	@Test
	public void Cart() throws InterruptedException {
		CartPage cartPage = new CartPage(driver);
		
		cartPage.verifyProductDisplay("ZARA COAT 3");
		cartPage.checkOutProducts();
		cartPage.placeOrder("india");

		}
	
	@Test(retryAnalyzer = RetryTest.class)
	public void confermOrder() {
		CartPage cartPage = new CartPage(driver);
		
		cartPage.confirmOrder("THANKYOU FOR THE ORDER.");
	}
}
