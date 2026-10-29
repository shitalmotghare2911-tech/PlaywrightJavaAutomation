package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.CheckoutPage;
public class CartTest extends BaseTest {

	@Test
	public void verifyCartProduct() {

		LoginPage loginPage = new LoginPage(page);
		loginPage.enterUsername("standard_user");
		loginPage.enterPassword("secret_sauce");
		loginPage.clickLogin();

		ProductPage productPage = new ProductPage(page);

// Add first product
		productPage.addProductToCart("Sauce Labs Backpack");
		waitForObservation();

		assertThat(productPage.getCartBadge()).hasText("1");

// Add second product
		productPage.addProductToCart("Sauce Labs Bike Light");
		waitForObservation();
        assertThat(productPage.getCartBadge()).hasText("2");

// Open cart
		CartPage cartPage = new CartPage(page);
		productPage.clickCart();
		waitForObservation();
		cartPage.removeProductFromCart("Sauce Labs Backpack");
		waitForObservation();

//Verify Backpack is removed
		assertThat(cartPage.getCartProduct("Sauce Labs Backpack")).not().isVisible();

//Verify Bike Light remains
		assertThat(cartPage.getCartProduct("Sauce Labs Bike Light")).hasText("Sauce Labs Bike Light");

//Verify badge decreases from 2 to 1
		assertThat(productPage.getCartBadge()).hasText("1");

//Verify Backpack is removed
		assertThat(cartPage.getCartProduct("Sauce Labs Backpack")).not().isVisible();

		assertThat(cartPage.getCartProductPrice("Sauce Labs Bike Light")).hasText("$9.99");
		cartPage.clickCheckout();
		waitForObservation();
		
		CheckoutPage checkoutPage = new CheckoutPage(page);

		checkoutPage.enterFirstName(getTestData("firstName"));
		waitForObservation();

		checkoutPage.enterLastName(getTestData("lastName"));
		waitForObservation();

		checkoutPage.enterPostalCode(getTestData("postalCode"));
		waitForObservation();
		
		checkoutPage.clickContinue();
		waitForObservation();
		
		assertThat(checkoutPage.getItemTotal()).hasText("Item total: $9.99");
		assertThat(checkoutPage.getTax()).hasText("Tax: $0.80");
		assertThat(checkoutPage.getLabel()).hasText("Total: $10.79");
		
		checkoutPage.clickFinish();
		
		assertThat(checkoutPage.getCompleteHeader()).hasText("Thank you for your order!");
	
	}
}
