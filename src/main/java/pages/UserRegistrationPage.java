package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationPage extends PageBase {

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "gender-male")
	WebElement genderRdBtn;
	
	@FindBy(id = "FirstName")
	WebElement fnTxtBox;
	
	@FindBy(id = "LastName")
	WebElement lnTxtBox;
	
	@FindBy(id = "Email")
	WebElement emailTxtBox;
	
	@FindBy(id = "Password")
	WebElement passwordTxtBox;
	
	@FindBy(id = "ConfirmPassword")
	WebElement confirmPasswordTxtBox;
	
	@FindBy(id = "register-button")
	WebElement registerBtn;
	
	@FindBy(css = "div.result")
	public WebElement successMessage;
	
	public void userRegistration(String firstName, String lastName, String email, String password) 
	{
		//genderRdBtn.click();
		clickButton(genderRdBtn);
		//fnTxtBox.sendKeys(firstName);
		setTextInElementText(fnTxtBox, firstName);
		//lnTxtBox.sendKeys(lastName);
		setTextInElementText(lnTxtBox, lastName);
		//emailTxtBox.sendKeys(email);
		setTextInElementText(emailTxtBox, email);
		//passwordTxtBox.sendKeys(password);
		setTextInElementText(passwordTxtBox, password);
		//confirmPasswordTxtBox.sendKeys(password);
		setTextInElementText(confirmPasswordTxtBox, password);
		//registerBtn.click();
		clickButton(registerBtn);
	}
}
