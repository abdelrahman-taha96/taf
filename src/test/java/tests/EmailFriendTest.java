package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.EmailFriendPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	SearchPage searchObject;
	LoginPage loginObject;
	ProductDetailsPage detailsObject;
	EmailFriendPage emailObject;
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
	public void RegisteredUserCanSendProductToFriend() 
	{
		detailsObject.OpenSendEmailToFriend();
		emailObject = new EmailFriendPage(driver);
		emailObject.SendEmailToFriend("friend@sadeek.com", "Hello my friend, this product is very good for you");
		assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent"));
		
	}
}
