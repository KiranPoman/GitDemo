package test;

import org.testng.annotations.Test;

import base.Base;
import page.ProductPage;

public class ProductTest extends Base{
	

	@Test
	public void Product() throws InterruptedException {
		

		
		  ProductPage productPage = new ProductPage(driver);
		  productPage.getProductList();
		  productPage.getProductByName("ZARA COAT 3");
		  productPage.addProductToCart("ZARA COAT 3");
		  productPage.goToCartPage();
		 }
}
