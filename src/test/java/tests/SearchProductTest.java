package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase {
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	String product_Name = "Apple MacBook Pro 13-inch";
	
	@Test
	public void UserCanSearchForProducts() 
	{
		searchObject = new SearchPage(driver);
		searchObject.ProductSearch(product_Name);
		detailsObject = new ProductDetailsPage(driver);
		detailsObject.ClickOnProductTitleToGetMOreDetails();
		assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(product_Name));
		assertEquals(detailsObject.productNameBreadCrumb.getText(), product_Name);
	}
}
