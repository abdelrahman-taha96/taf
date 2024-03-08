package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase {

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "button.remove-btn")
	WebElement removeCheck;
	
	@FindBy(id = "updatecart")
	WebElement updateCartBtn;
	
	@FindBy(css = ".cart td.quantity input")
	WebElement quantityTxt;
	
	@FindBy(css = "span.product-subtotal")
	public WebElement totalLbl;
	
	@FindBy(css = "div.no-data")
	public WebElement emptyMessage;
	
	@FindBy(id = "checkout")
	WebElement checkoutBtn;
	
	@FindBy(id = "termsofservice")
	WebElement agreeCheckbox;
	
	public void UpdateProductQuantityInCart(String quantity) 
	{
		clearText(quantityTxt);
		setTextInElementText(quantityTxt, quantity);
		clickButton(updateCartBtn);
	}
	
	public void RemoveProductFromCart() 
	{
		clickButton(removeCheck);
	}
	
	public void openCheckoutPage() 
	{
		clickButton(agreeCheckbox);
		clickButton(checkoutBtn);
	}

}
