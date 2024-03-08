package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithJavaFaker extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;
Faker fakeData = new Faker();
String firstname = fakeData.name().firstName(); 
String lastname = fakeData.name().lastName(); 
String email = fakeData.internet().emailAddress(); 
String password = fakeData.number().digits(8).toString();

@Test(priority = 1, alwaysRun = true)
public void UserCanRegisterSuccessfully() 
{
	homeObject = new HomePage(driver);
	homeObject.openRegistrationPage();
	registerObject = new UserRegistrationPage(driver);
	registerObject.userRegistration(firstname,lastname,email,password);
	System.out.println("The Userr Data is : "+ firstname + " " + lastname + " " + email + " " + password);
	assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
}

@Test(dependsOnMethods = {"UserCanRegisterSuccessfully"})
public void RegisteredUserCanLogin() 
{
	homeObject.openLoginPage();
	loginObject = new LoginPage(driver);
	loginObject.UserLogin(email,password);
	assertTrue(loginObject.logoutLink.getText().contains("Log out"));
}

@Test(dependsOnMethods = {"RegisteredUserCanLogin"})
public void RegisteredUserCanLogout() 
{
	loginObject.UserLogout();
}
}