package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import pages.HomePage;

public class ProductHoverMenuTest extends TestBase {

	HomePage homeObject;
	
	@Test
	public void UserCanSelectSubCategoryFromMainMenu() 
	{
		homeObject = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(homeObject.ComputerMenu));
		homeObject.selectNotebooksMenu();
		//assertTrue(driver.getCurrentUrl().contains("notebooks"));
	}
}
