package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase {

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		jse = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}
	
	@FindBy(linkText = "Register")
	WebElement registerLink;
	
	@FindBy(linkText = "Log in")
	WebElement loginLink;
	
	@FindBy(linkText = "Contact us")
	WebElement contactUsLink;
	
	@FindBy(id = "customerCurrency")
	WebElement currencyDrList;
	
	@FindBy(linkText = "Computers")
	public WebElement ComputerMenu;
	
	@FindBy(linkText = "Notebooks")
	WebElement NotebooksMenu;
	
	@FindBy(name = "Notebooks")
	public WebElement notebookTxt;
	
	public void openRegistrationPage () 
	{
		//registerLink.click();
		clickButton(registerLink);
	}
	
	public void openLoginPage() 
	{
		clickButton(loginLink);
	}
	
	public void openContactUsPage() 
	{
		ScrollToBottom();
		clickButton(contactUsLink);
	}
	
	public void changeCurrency() 
	{
		select = new Select(currencyDrList);
		select.selectByVisibleText("Euro");
	}
	
	public void selectNotebooksMenu() 
	{
		/*action
		.moveToElement(ComputerMenu)
		.click(NotebooksMenu)
		.build()
		.perform();*/
		
		clickButton(ComputerMenu);
		clickButton(NotebooksMenu);
	}
}
