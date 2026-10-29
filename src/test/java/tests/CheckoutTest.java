package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestDataReader;

public class CheckoutTest extends BaseTest
{
    @Test
	public void verifyCheckoutPage() 
	{
		LoginPage loginPage = new LoginPage(page);
		loginPage.enterUsername(getProperty("username"));
		loginPage.enterPassword(getProperty("password"));
        loginPage.clickLogin();
        
        ProductPage productPage = new ProductPage(page);

        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.clickCart();
        
        CartPage cartPage = new CartPage(page);
        cartPage.clickCheckout();
        
        
        CheckoutPage checkoutPage = new CheckoutPage(page);
        checkoutPage.enterFirstName(getTestData("firstName"));
        checkoutPage.enterLastName(getTestData("lastName"));
        checkoutPage.enterPostalCode(getTestData("postalCode"));
        checkoutPage.clickContinue();
        assertThat(page).hasURL(
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        checkoutPage.clickFinish();
        
        assertThat(page).hasURL(
                "https://www.saucedemo.com/checkout-complete.html"
        );
	}

}
