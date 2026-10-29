package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ConfigReader;
import utils.TestDataReader;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

   
	private ConfigReader configReader;
	private TestDataReader testDataReader;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException { 
 	
    	configReader = new ConfigReader();
    	testDataReader = new TestDataReader();
     
        // Start Playwright
        playwright = Playwright.create();

        // Read browser from config
        String browserName = configReader.getProperty("browser");
        
        // Read headless value from config
        boolean headless = Boolean.parseBoolean(configReader.getProperty("headless"));
        
        // Browser launch options
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                        .setHeadless(headless);

        
        // Launch selected browser
        if (browserName.equalsIgnoreCase("chromium")) {

            browser = playwright.chromium().launch(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {

            browser = playwright.firefox().launch(options);

        } else if (browserName.equalsIgnoreCase("webkit")) {

            browser = playwright.webkit().launch(options);

        } else {

            throw new IllegalArgumentException(
                    "Invalid browser: " + browserName
            );
        }

        // Create browser context
        context = browser.newContext();

        // Create page
        page = context.newPage();
        

        // Open application URL
        page.navigate(configReader.getProperty("baseURL"));
    }
    protected String getProperty(String key) {
        return configReader.getProperty(key);
    }
    
    protected String getTestData(String key) {
        return testDataReader.getTestData(key);
    }
    protected void waitForObservation() {
        page.waitForTimeout(2000);
    }
   
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
    	
        page.close();
      
        context.close();
        browser.close();
        playwright.close();
    }
}