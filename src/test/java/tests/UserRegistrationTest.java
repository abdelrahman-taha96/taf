package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;

@Test(priority = 1, alwaysRun = true)
public void UserCanRegisterSuccessfully() 
{
	homeObject = new HomePage(driver);
	homeObject.openRegistrationPage();
	registerObject = new UserRegistrationPage(driver);
	registerObject.userRegistration("Abdelrahman", "Hassan", "abdo.tests@gmail.com", "1234567890");
	assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
}

@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
public void RegisteredUserCanLogin() 
{
	homeObject.openLoginPage();
	loginObject = new LoginPage(driver);
	loginObject.UserLogin("abdo.tests@gmail.com", "1234567890");
	assertTrue(loginObject.logoutLink.getText().contains("Log out"));
}

@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
public void RegisteredUserCanLogout() 
{
	loginObject.UserLogout();
}
}