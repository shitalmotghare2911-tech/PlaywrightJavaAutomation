//package tests;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
//
//import base.BaseTest;
//import pages.HomePage;
//import pages.ProductPage;
//
//public class ProductSearchTest extends BaseTest
//{
//	@Test
//	public void searchProduct()
//	{
//		HomePage homePage = new HomePage(page);
//		ProductPage productPage = homePage.clickProducts();
//		productPage.searchProduct("tshirt");
//
//		assertThat(page.getByText("Searched Products")).isVisible();
//
//		assertThat(productPage.getProduct("Tshirt")).isVisible();
//		
//	}
//
//}
