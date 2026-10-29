package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class CartPage {
	private Page page;
	
	public CartPage(Page page) {
		this.page = page;
	
	}
	public Locator getCartProduct(String productName) {
	    return page.getByLabel("View details for " + productName);
	}
	public void clickCheckout( ) {
		page.locator("[data-test=\"checkout\"]").click();
	}
	public void removeProductFromCart(String productName) {

	    String productLocator =
	            "[data-test='remove-" +
	            productName.trim().toLowerCase().replace(" ", "-") +
	            "']";

	    page.locator(productLocator).click();
	}
	public Locator getCartProductPrice(String productName) {
	    return page.locator("[data-test='inventory-item']")
	            .filter(new Locator.FilterOptions()
	                    .setHas(getCartProduct(productName)))
	            .locator("[data-test='inventory-item-price']");
	}
}

