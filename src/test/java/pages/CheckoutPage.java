package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage 
{
	private Page page;
	private String firstName = "#first-name";
	private String lastName = "#last-name";
	private String postalCode = "#postal-code";
	private String continueButton = "#continue";
	private String finishButton = "#finish";
	public CheckoutPage(Page page)
	{
		this.page = page;
	}
	
	public void enterFirstName(String firstNameValue)
	{
		page.locator(firstName).fill(firstNameValue);
	}
	public void enterLastName(String lastNameValue)
	{
		page.locator(lastName).fill(lastNameValue);
	}
	public void enterPostalCode(String postalCodeValue)
	{
		page.locator(postalCode).fill(postalCodeValue);
	}
	public void clickContinue()
	{
		page.locator(continueButton).click();
	}
	public void clickFinish() {
	    page.locator(finishButton).click();
	}
	public Locator getItemTotal() {
		return page.locator("[data-test=\"subtotal-label\"]");	
	}
	public Locator getTax() {
		return page.locator("[data-test=\"tax-label\"]");
	}
	public Locator getLabel() {
		return page.locator("[data-test=\"total-label\"]");	
	}
	public Locator getCompleteHeader() {
	    return page.locator("[data-test='complete-header']");
	}
}
