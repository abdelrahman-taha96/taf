package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishListPage;

public class AddProductToWishListTest extends TestBase {

	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	WishListPage wishlistObject;
	String product_Name = "Apple MacBook Pro 13-inch";
	
	@Test(priority = 1)
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
	
	@Test(dependsOnMethods = {"UserCanSearchUsingAutoSuggest"})
	public void UserCanAddProductToWishlist() 
	{
		detailsObject.AddProductToWishlist();
		wishlistObject = new WishListPage(driver);
		assertTrue(wishlistObject.wishlistHeader.isDisplayed());
		assertTrue(wishlistObject.productCell.getText().contains(product_Name));		
	}
	
	@Test(dependsOnMethods = {"UserCanAddProductToWishlist"})
	public void UserCanRemoveProductFromWishlist() 
	{
		wishlistObject.RemoveProductFromWishlist();
		assertTrue(wishlistObject.emptyCartMessage.getText().contains("The wishlist is empty!"));
	}
}
