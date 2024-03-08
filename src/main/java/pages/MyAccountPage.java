package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends PageBase {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText = "Change password")
	WebElement changePasswordLink;
	
	@FindBy(id = "OldPassword")
	WebElement oldPasswordTxtBox;
	
	@FindBy(id = "NewPassword")
	WebElement newPasswordTxtBox;
	
	@FindBy(id = "ConfirmNewPassword")
	WebElement confirmNewPasswordTxtBox;
	
	@FindBy(css = "button.button-1.change-password-button")
	WebElement changePasswordBtn;
	
	@FindBy(css = "div.bar-notification.success")
	public WebElement resultLbl;
	
	@FindBy(css = "div.header-logo")
	WebElement logoBtn;
	
	public void openChangePasswordPage() 
	{
		clickButton(changePasswordLink);
	}
	
	public void changePassword(String old_password, String new_password) 
	{
		setTextInElementText(oldPasswordTxtBox, old_password);
		setTextInElementText(newPasswordTxtBox, new_password);
		setTextInElementText(confirmNewPasswordTxtBox, new_password);
		clickButton(changePasswordBtn);
	}
	
	public void openHomePage() 
	{
		clickButton(logoBtn);
	}

}
