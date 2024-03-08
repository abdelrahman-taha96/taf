package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTAndDataProvider extends TestBase {
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	
	@DataProvider(name = "testData")
	public static Object[][] userData()
	{
		return new Object[][] {
			{"Abdelrahman", "Hassan", "hassaan.test20@gmail.com", "12345600"},
			{"Adel", "Amin", "aman.test200@gmail.com", "33334444"},
			{"Mohsen", "Mansour", "mans.test200@gmail.com", "11112222"}
		};
	}

	@Test(priority = 1, alwaysRun = true, dataProvider = "testData")
	public void UserCanRegisterSuccessfully(String fName, String lName, String email, String password) 
	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(fName, lName, email, password);
		assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		assertTrue(loginObject.logoutLink.getText().contains("Log out"));
		loginObject.UserLogout();
	}
}
