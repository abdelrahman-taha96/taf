package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.HomePage;

public class ContactUsTest extends TestBase {

	HomePage home;
	ContactUsPage contactPage;
	
	String full_Name = "Abdelrahman Taha";
	String email = "testt@admin.com";
	String enquiry = "Hello Admin, this is for test";
	
	@Test
	public void UserCanUseContactUs() 
	{
		home = new HomePage(driver);
		home.openContactUsPage();
		contactPage = new ContactUsPage(driver);
		contactPage.ContactUs(full_Name, email, enquiry);
		assertTrue(contactPage.successMessage.getText().contains("Your enquiry has been successfully sent to the store owner"));
	}
}
