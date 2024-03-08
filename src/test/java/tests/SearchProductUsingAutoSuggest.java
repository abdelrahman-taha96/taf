package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutoSuggest extends TestBase {

	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	String product_Name = "Apple MacBook Pro 13-inch";
	
	@Test
	public void UserCanSearchUsingAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(product_Name));

		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());
		}
	}
}
