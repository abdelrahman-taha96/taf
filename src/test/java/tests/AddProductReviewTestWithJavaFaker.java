package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.EmailFriendPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class AddProductReviewTestWithJavaFaker extends TestBase {


	HomePage homeObject;
	UserRegistrationPage registerObject;
	SearchPage searchObject;
	LoginPage loginObject;
	ProductDetailsPage detailsObject;
	EmailFriendPage emailObject;
	ProductReviewPage reviewObject;
	Faker fakeData = new Faker();
	String firstName = fakeData.name().firstName();
	String lastName = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(8).toString();
	String product_Name = "Apple MacBook Pro 13-inch";
	
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
	
	@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
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
	public void RegisteredUserCanReviewProduct() 
	{
		detailsObject.OpenAddReviewPage();
		reviewObject = new ProductReviewPage(driver);
		reviewObject.AddProductReview("new review", "the product is very good");
		assertTrue(reviewObject.reviewNotification.getText().contains("Product review is successfully added"));
	}
	
	@Test(dependsOnMethods = {"RegisteredUserCanReviewProduct"})
	public void RegisteredUserCanLogout() 
	{
		loginObject.UserLogout();
	}
}
