package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndExcel extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;

@DataProvider(name="ExcelData")
public Object[][] userRegisterData() throws IOException
{
	// get data from Excel Reader class 
	ExcelReader ER = new ExcelReader();
	return ER.getExcelData();
}

@Test(priority = 1, alwaysRun = true, dataProvider = "ExcelData")
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