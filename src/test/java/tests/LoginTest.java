package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest
{
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {
	    return new Object[][] {
	        {"standard_user", "secret_sauce", true},
	        {"locked_out_user", "secret_sauce", false}
	    };
	}
	
	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password, boolean shouldLogin) {
		LoginPage loginPage = new LoginPage(page);
		waitForObservation();
		loginPage.enterUsername(username);
		waitForObservation();
		loginPage.enterPassword(password);
		waitForObservation();
		loginPage.clickLogin();
		waitForObservation();
		
		if(shouldLogin) {
			assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");
		}
		else {
			assertThat(page.locator("[data-test='error']")).
			hasText("Epic sadface: Sorry, this user has been locked out.");
		}
	    
	}
}
