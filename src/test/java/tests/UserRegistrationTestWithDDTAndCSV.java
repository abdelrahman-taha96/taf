package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndCSV extends TestBase {
HomePage homeObject;
UserRegistrationPage registerObject;
LoginPage loginObject;

CSVReader reader ;


@Test(priority = 1, alwaysRun = true)
public void UserCanRegisterSuccessfully() throws CsvValidationException, IOException 
{
	// get path of CSV file 
			String CSV_file = System.getProperty("user.dir")+"/src/test/java/data/UserData.csv";
			reader = new CSVReader(new FileReader(CSV_file)); 

			String[] csvCell ; 

			// while loop will be executed till the last value in CSV file . 
			while((csvCell = reader.readNext()) != null) 
			{
				String firstname = csvCell[0]; 
				String lastName = csvCell[1]; 
				String email_ = csvCell[2]; 
				String password_ = csvCell[3];
	
	homeObject = new HomePage(driver);
	homeObject.openRegistrationPage();
	registerObject = new UserRegistrationPage(driver);
	registerObject.userRegistration(firstname, lastName, email_, password_);
	assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	homeObject.openLoginPage();
	loginObject = new LoginPage(driver);
	loginObject.UserLogin(email_, password_);
	assertTrue(loginObject.logoutLink.getText().contains("Log out"));
	loginObject.UserLogout();
}
}
}