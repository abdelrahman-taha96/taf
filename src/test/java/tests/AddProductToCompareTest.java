package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ComparePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class AddProductToCompareTest extends TestBase {

	SearchPage searchObject;

	ProductDetailsPage detailsObject;
	ComparePage compareObject;
	String firstName_Product = "Apple MacBook Pro 13-inch";
	String secondName_Product = "Asus N551JK-XO076H Laptop";
	
	@Test(priority = 1)
	public void UserCanCompareProducts() 
	{
		searchObject = new SearchPage(driver);
		detailsObject = new ProductDetailsPage(driver);
		compareObject = new ComparePage(driver);
		
		searchObject.ProductSearchUsingAutoSuggest("MacB");
		assertTrue(detailsObject.productNameBreadCrumb.getText().contains(firstName_Product));
		detailsObject.AddProductToComapre();
		
		searchObject.ProductSearchUsingAutoSuggest("asus");
		assertTrue(detailsObject.productNameBreadCrumb.getText().contains(secondName_Product));
		detailsObject.AddProductToComapre();
		
		driver.navigate().to("https://demo.nopcommerce.com/compareproducts");
		assertTrue(compareObject.firstProductName.getText().contains("Apple MacBook Pro 13-inch"));
		assertTrue(compareObject.secondProductName.getText().contains("Asus N551JK-XO076H Laptop"));
		compareObject.CompareProducts();
	}
	
	@Test(priority = 2)
	public void UserCanClearCompareList() 
	{
		compareObject.ClearCompareList();
		assertTrue(compareObject.noDataMessage.getText().contains("You have no items to compare."));
	}
}
