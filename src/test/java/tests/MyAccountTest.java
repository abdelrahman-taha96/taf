package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase {
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	MyAccountPage myAccountObject;
	Faker fakeData = new Faker();
	String firstName = fakeData.name().firstName();
	String lastName = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String oldPassword = fakeData.number().digits(8).toString();
	String newPassword = "12345678";

	@Test(priority = 1, alwaysRun = true)
	public void UserCanRegisterSuccessfully() 
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, oldPassword);
		assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	}

	@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
	public void RegisteredUserCanLogin() 
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, oldPassword);
		assertTrue(loginObject.logoutLink.getText().contains("Log out"));
	}
	
	@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
	public void LoggedInUserCanChangePassword() 
	{
		myAccountObject = new MyAccountPage(driver);
		loginObject.openMyAccountPage();
		myAccountObject.openChangePasswordPage();
		myAccountObject.changePassword(oldPassword, newPassword);
		assertTrue(myAccountObject.resultLbl.getText().contains("Password was changed"));
		myAccountObject.openHomePage();
	}
	
	@Test(dependsOnMethods = {"LoggedInUserCanChangePassword"})
	public void RegisteredUserCanLoginAfterChangePassword()
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, newPassword);
		assertTrue(loginObject.logoutLink.getText().contains("Log out"));
	}
	
	@Test(dependsOnMethods = {"RegisteredUserCanLoginAfterChangePassword"})
	public void RegisteredUserCanLogout() 
	{
		loginObject.UserLogout();
	}
}
