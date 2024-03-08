package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase {

	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartObject;
	String firstName_Product = "Apple MacBook Pro 13-inch";
	String newQuantity = "4";
	
	@Test(priority = 1)
	public void UserCanSearchUsingAutoSuggest() 
	{
		try {
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			assertTrue(detailsObject.productNameBreadCrumb.getText().equalsIgnoreCase(firstName_Product));

		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void UserCanAddProductTOCart() 
	{
		detailsObject.AddToCart();
		driver.navigate().to("https://demo.nopcommerce.com/cart");
		cartObject = new ShoppingCartPage(driver);
		assertTrue(cartObject.totalLbl.getText().contains("3,600.00"));
	}
	
	 @Test(priority = 3)
	public void UserCanUpdateShoppingCart() 
	{
		cartObject = new ShoppingCartPage(driver);
		cartObject.UpdateProductQuantityInCart(newQuantity);
		assertTrue(cartObject.totalLbl.getText().contains("7,200.00"));
	}
	
	@Test(priority = 4)
	public void UserCanRemoveProductFromCart() 
	{
		cartObject.RemoveProductFromCart();
		assertTrue(cartObject.emptyMessage.getText().contains("Your Shopping Cart is empty!"));
	}
}
