package tests;

	

	import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;

	public class ProductsTest extends BaseTest {
		
		@Test(groups = "smoke")
	    public void verifyProductsPage() {

	        LoginPage loginPage = new LoginPage(page);
	        loginPage.enterUsername(getProperty("username"));
	        loginPage.enterPassword(getProperty("password"));
	        loginPage.clickLogin();

	        ProductPage productPage = new ProductPage(page);
	        
	        productPage.sortProductsByLabel("Price (high to low)");

	        assertThat(page.locator("[data-test='product-sort-container']")).hasValue("hilo"); 
	        
	        productPage.sortProducts("lohi");
	        assertThat(page.locator("[data-test='product-sort-container']")).hasValue("lohi");

	        assertThat(productPage.getProductsTitle()).isVisible();
	        assertThat(productPage.getProductImage("Sauce Labs Backpack")).isVisible();
	        
	        productPage.clickProduct("Sauce Labs Backpack");

	        assertThat(productPage.getProductDetailsName()).hasText("Sauce Labs Backpack");

	        assertThat(productPage.getProductDetailsPrice()).hasText("$29.99");

	        assertThat(productPage.getProductDetailsDescription()).hasText(
	            "carry.allTheThings() " +
	            "with the sleek, " +
	            "streamlined Sly Pack that melds uncompromising style " +
	            "with unequaled laptop and tablet protection."
	        );

	        productPage.addProductToCartFromDetails();

	        assertThat(productPage.getCartBadge()).hasText("1");
	        productPage.clickCart();
	        
	        
	        
	       
	    }
}
