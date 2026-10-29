package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class FirstPlaywrightTest extends BaseTest {

    @Test(groups = "smoke")
    public void loginTest() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.enterUsername(getProperty("username"));
        loginPage.enterPassword(getProperty("password"));
        loginPage.clickLogin();

        assertThat(page).hasURL(
        	    getProperty("baseURL") + "inventory.html");
       

        assertThat(page.getByText("Products")).isVisible();
    }
}