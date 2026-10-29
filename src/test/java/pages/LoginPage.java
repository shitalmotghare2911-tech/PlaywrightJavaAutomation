package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
public class LoginPage {

    private Page page;

    // Locators
    private String username = "#user-name";
    private String password = "#password";
    private String loginButton = "#login-button";

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Actions
    public void enterUsername(String usernameValue) {
        page.locator(username).fill(usernameValue);
    }

    public void enterPassword(String passwordValue) {
        page.locator(password).fill(passwordValue);
    }

    public void clickLogin() {
        page.locator(loginButton).click();
    }
    public Locator getError() {
		return page.locator("[data-test=\"error\"]");
    	
    }
    

}

