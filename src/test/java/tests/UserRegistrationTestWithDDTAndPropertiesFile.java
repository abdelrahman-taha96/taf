package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import data.loadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndPropertiesFile extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;
String firstName = loadProperties.userData.getProperty("first_name");
String lastName = loadProperties.userData.getProperty("last_name");
String email = loadProperties.userData.getProperty("email_");
String passwordAuth = loadProperties.userData.getProperty("password");

@Test(priority = 1, alwaysRun = true)
public void UserCanRegisterSuccessfully() 
{
	System.out.println(loadProperties.userData);
	homeObject = new HomePage(driver);
	homeObject.openRegistrationPage();
	registerObject = new UserRegistrationPage(driver);
	registerObject.userRegistration(firstName, lastName, email, passwordAuth);
	assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
}

@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
public void RegisteredUserCanLogin() 
{
	homeObject.openLoginPage();
	loginObject = new LoginPage(driver);
	loginObject.UserLogin(email, passwordAuth);
	assertTrue(loginObject.logoutLink.getText().contains("Log out"));
}

@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
public void RegisteredUserCanLogout() 
{
	loginObject.UserLogout();
}
}