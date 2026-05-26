package test;

import org.testng.annotations.Test;

import base.Base;
import page.LoginPage;

public class LoginTest extends Base {

	@Test
	public void Login() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		loginPage.loginApplication("kiranpoman412@gmail.com", "Kiran@1234");

	}
}
