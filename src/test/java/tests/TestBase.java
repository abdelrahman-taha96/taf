package tests;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utilities.Helper;

public class TestBase extends AbstractTestNGCucumberTests {
public static WebDriver driver;

public static String downloadPath = System.getProperty("user.dir")+"\\Downloads";

public static FirefoxOptions firefoxOption() {
	FirefoxOptions option = new FirefoxOptions();
	option.addPreference("browser.download.folderList", 2);
	option.addPreference("browser.download.dir", downloadPath);
	option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
	option.addPreference("browser.download.manager.showWhenStarting", false);
	return option;
}

public static ChromeOptions chromeOption() {
	ChromeOptions option = new ChromeOptions();
	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
	chromePrefs.put("profile.default.content_settings.popups", 0);
	chromePrefs.put("download.default_directory", downloadPath);
	option.setExperimentalOption("prefs", chromePrefs);
	option.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
	return option;
}

@BeforeClass
@Parameters("browser")
public void startDriver(@Optional ("firefox") String browserName) 
{
	
	if (browserName.equalsIgnoreCase("chrome")) {
		String chromePath = System.getProperty("user.dir")+"\\drivers\\chrome_proxy.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver(chromeOption());
	} 
	
	else if (browserName.equalsIgnoreCase("firefox")) {
		String firefoxPath = System.getProperty("user.dir")+"\\drivers\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		driver = new FirefoxDriver(firefoxOption());
	} 
	
	else if (browserName.equalsIgnoreCase("microsoft edge")) {
		String edgePath = System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", edgePath);
		driver = new EdgeDriver();
	}
	
	driver.manage().window().maximize();
	driver.navigate().to("https://demo.nopcommerce.com/");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
}

@AfterClass
public void stopDriver() 
{
	driver.quit();
}

// Take screenshot when test case fail and add it in the Screenshot folder
@AfterMethod
public void screenshotOnFailure(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
		System.out.println("Failed");
		System.out.println("Taking Screenshot....");
		Helper.CaptureScreenshot(driver, result.getName());
	}
}
}
