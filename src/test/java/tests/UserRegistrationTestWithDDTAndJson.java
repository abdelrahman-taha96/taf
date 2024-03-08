package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import data.JsonDataReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndJson extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;



@Test(priority = 1, alwaysRun = true)
public void UserCanRegisterSuccessfully() throws IOException, ParseException 
{
	JsonDataReader jsonReader = new JsonDataReader(); 
	jsonReader.JsonReader();
	
	homeObject = new HomePage(driver);
	homeObject.openRegistrationPage();
	registerObject = new UserRegistrationPage(driver);
	registerObject.userRegistration(jsonReader.firstname,jsonReader.lastname,jsonReader.email,jsonReader.password);
	assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	homeObject.openLoginPage();
	loginObject = new LoginPage(driver);
	loginObject.UserLogin(jsonReader.email,jsonReader.password);
	assertTrue(loginObject.logoutLink.getText().contains("Log out"));
	loginObject.UserLogout();
}
}