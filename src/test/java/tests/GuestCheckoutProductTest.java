package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class GuestCheckoutProductTest extends TestBase {

	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartObject;
	CheckoutPage checkoutObject;
	OrderDetailsPage orderObject;
	String firstName_check = "Abdelrahman";
	String lastName_check = "Hassan";
	String email_check = "abdo.582codes@gmail.com";
	String country_check = "Egypt";
	String city_check = "Cairo";
	String address_check = "test address";
	String postalCode_check = "1181315";
	String phoneNo_check = "01141364313";
	String product_Name = "Apple MacBook Pro 13-inch";
	
	@Test(priority = 1, alwaysRun = true)
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
	public void UserCanAddProductTOCart() 
	{
		detailsObject.AddToCart();
		driver.navigate().to("https://demo.nopcommerce.com/cart");
		cartObject = new ShoppingCartPage(driver);
		assertTrue(cartObject.totalLbl.getText().contains("3,600.00"));
	}
	
	@Test(dependsOnMethods = {"UserCanAddProductTOCart"})
	public void UserCanCheckoutProduct() throws InterruptedException 
	{
		cartObject = new ShoppingCartPage(driver);
		cartObject.openCheckoutPage();
		checkoutObject = new CheckoutPage(driver);
		checkoutObject.CheckoutProduct(firstName_check, lastName_check, email_check, country_check, city_check, address_check, postalCode_check, phoneNo_check);
		assertTrue(checkoutObject.productName.getText().contains(product_Name));
		Thread.sleep(3000);
		checkoutObject.ConfirmOrder();
		Thread.sleep(3000);
		assertTrue(checkoutObject.successMessage.isDisplayed());
	}
	
	@Test(dependsOnMethods = {"UserCanCheckoutProduct"})
	public void UserCanViewOrderDetails() throws InterruptedException 
	{
		checkoutObject.ViewOrderDetails();
		assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject = new OrderDetailsPage(driver);
		orderObject.PrintOrderDetails();
		orderObject.DownloadPdfInvoice();
	}
}
