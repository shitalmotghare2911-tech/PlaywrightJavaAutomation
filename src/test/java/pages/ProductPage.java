package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class ProductPage 
{
	private Page page;
	
	private Locator productsTitle;

	private String productName; 
		
	public ProductPage(Page page)
	{
		this.page = page;
		productsTitle = page.locator("[data-test='title']");
	}
	public Locator getProductsTitle()
	{
		return productsTitle;	
	}
	public Locator getProductImage(String productName) {

	    return page.locator("img[alt='" + productName + "']");
	}
	public void clickProduct(String productName) {

	    page.locator("[data-test='inventory-item-name']")
	            .filter(new Locator.FilterOptions().setHasText(productName))
	            .locator("..")
	            .click();
	}
	public Locator getProductDetailsName() {

	    return page.locator(
	            ".inventory_details_name[data-test='inventory-item-name']"
	    );
	}
	public Locator getProductDetailsPrice( ) {
		return page.locator("[data-test=\"inventory-item-price\"]");
	}
	public Locator getProductDetailsDescription() {
		return page.locator("[data-test=\"inventory-item-desc\"]");
		
	}
	public void addProductToCart(String productName) {
	    String productLocator =
	            "[data-test='add-to-cart-" +
	            productName.trim().toLowerCase().replace(" ", "-") +
	            "']";

	    page.locator(productLocator).click();
	}
	public void addProductToCartFromDetails() {
	    page.locator("[data-test='add-to-cart']").click();
	}
	
	public void clickCart() {

	    page.locator("[data-test='shopping-cart-link']").click();
	}
	public Locator getCartBadge() {

	    return page.locator("[data-test='shopping-cart-badge']");
	}
	public void sortProducts(String value) {
	    page.locator("[data-test='product-sort-container']")
	        .selectOption(value);
	}
	public void sortProductsByLabel(String label) {
	    page.locator("[data-test='product-sort-container']")
	        .selectOption(
	            new SelectOption().setLabel(label)
	        );
	}
	public void removeProductFromCart(String productName) {

	    String productLocator =
	            "[data-test='remove-" +
	            productName.trim().toLowerCase().replace(" ", "-") +
	            "']";

	    page.locator(productLocator).click();
	}
	
	
}
