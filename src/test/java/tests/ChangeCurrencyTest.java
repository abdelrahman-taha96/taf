package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase {

	HomePage homeObject;
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	String product_Name = "Apple MacBook Pro 13-inch";
	
	@Test(priority = 1)
	public void UserCanChangeCurrency() 
	{
		homeObject = new HomePage(driver);
		homeObject.changeCurrency();
	}
	
	@Test(priority = 2)
	public void UserCanSearchUsingAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(product_Name));
			assertTrue(detailsObject.productPriceLbl.getText().contains("€"));
			System.out.println(detailsObject.productPriceLbl.getText());

		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());
		}
	}
}