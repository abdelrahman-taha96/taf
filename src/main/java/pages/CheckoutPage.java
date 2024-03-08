package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends PageBase {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
		
	@FindBy(css = "button.button-1.checkout-as-guest-button")
	WebElement checkoutAsGuestBtn;
	
	@FindBy(id = "BillingNewAddress_FirstName")
	WebElement fnText;
	
	@FindBy(id = "BillingNewAddress_LastName")
	WebElement lnText;
	
	@FindBy(id = "BillingNewAddress_Email")
	WebElement emailText;
	
	@FindBy(id = "BillingNewAddress_CountryId")
	WebElement countryList;
	
	@FindBy(id = "BillingNewAddress_City")
	WebElement cityText;
	
	@FindBy(id = "BillingNewAddress_Address1")
	WebElement addressOneTxt;
	
	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	WebElement postalCodeTxt;
	
	@FindBy(id = "BillingNewAddress_PhoneNumber")
	WebElement phoneTxt;
	
	@FindBy(css = "button.button-1.new-address-next-step-button")
	WebElement continueBtn;
	
	@FindBy(id = "shippingoption_1")
	WebElement shippingMethodRdo;
	
	@FindBy(css = "button.button-1.shipping-method-next-step-button")
	WebElement continueShippingBtn;
	
	@FindBy(id = "paymentmethod_0")
	WebElement paymentMethodRdo;
	
	@FindBy(css = "button.button-1.payment-method-next-step-button")
	WebElement continuePaymentBtn;
		
	@FindBy(css = "button.button-1.payment-info-next-step-button")
	WebElement continueInfoBtn;
	
	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	public WebElement productName;
	
	@FindBy(css = "button.button-1.confirm-order-next-step-button")
	WebElement confirmBtn;

	@FindBy(css = "h1")
	public WebElement thankYouLbl;
	
	@FindBy(css = ".fieldset .title strong, .section .title strong")
	public WebElement successMessage;
	
	@FindBy(linkText = "Click here for order details.")
	WebElement orderDetailsLink;
	
	public void RegisteredUserCheckoutProduct(String country, String city, String address, String postalCode, String phoneNo) 
	{
		select = new Select(countryList);
		select.selectByVisibleText(country);
		setTextInElementText(cityText, city);
		setTextInElementText(addressOneTxt, address);
		setTextInElementText(postalCodeTxt, postalCode);
		setTextInElementText(phoneTxt, phoneNo);
		clickButton(continueBtn);
		clickButton(shippingMethodRdo);
		clickButton(continueShippingBtn);
		clickButton(paymentMethodRdo);
		clickButton(continuePaymentBtn);
		wait.until(ExpectedConditions.visibilityOf(continueInfoBtn));
		clickButton(continueInfoBtn);
	}
	
	public void ConfirmOrder() 
	{
		clickButton(confirmBtn);
	}
	
	public void ViewOrderDetails() throws InterruptedException 
	{
		Thread.sleep(3000);
		clickButton(orderDetailsLink);
	}
	
	public void CheckoutProduct(String firstName, String lastName, String email, String country, String city, String address, String postalCode, String phoneNo) throws InterruptedException 
	{
		clickButton(checkoutAsGuestBtn);
		Thread.sleep(3000);
		setTextInElementText(fnText, firstName);
		setTextInElementText(lnText, lastName);
		setTextInElementText(emailText, email);
		select = new Select(countryList);
		select.selectByVisibleText(country);
		setTextInElementText(cityText, city);
		setTextInElementText(addressOneTxt, address);
		setTextInElementText(postalCodeTxt, postalCode);
		setTextInElementText(phoneTxt, phoneNo);
		clickButton(continueBtn);
		clickButton(shippingMethodRdo);
		clickButton(continueShippingBtn);
		clickButton(paymentMethodRdo);
		clickButton(continuePaymentBtn);
		wait.until(ExpectedConditions.visibilityOf(continueInfoBtn));
		clickButton(continueInfoBtn);
	}
}
