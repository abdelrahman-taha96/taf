package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;

public class RegisteredUserCheckoutProductTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	SearchPage searchObject;
	LoginPage loginObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartObject;
	CheckoutPage checkoutObject;
	OrderDetailsPage orderObject;
	Faker fakeData = new Faker();
	String firstName = fakeData.name().firstName();
	String lastName = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(8).toString();
	String country_name = "Egypt";
	String product_Name = "Apple MacBook Pro 13-inch";
	
	String country_check = "Egypt";
	String city_check = "Cairo";
	String address_check = "test address";
	String postalCode_check = "1181316";
	String phoneNo_check = "01141364314";
	
	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccessfully() 
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
		assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}
	
	@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		assertTrue(loginObject.logoutLink.getText().contains("Log out"));
	}
	
	@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
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
		checkoutObject.RegisteredUserCheckoutProduct(country_check, city_check, address_check, postalCode_check, phoneNo_check);
		assertTrue(checkoutObject.productName.getText().contains(product_Name));
		Thread.sleep(3000);
		checkoutObject.ConfirmOrder();
		Thread.sleep(3000);
		assertTrue(checkoutObject.successMessage.isDisplayed());
		checkoutObject.ViewOrderDetails();
		assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject = new OrderDetailsPage(driver);
		orderObject.PrintOrderDetails();
		orderObject.DownloadPdfInvoice();
	}
}
