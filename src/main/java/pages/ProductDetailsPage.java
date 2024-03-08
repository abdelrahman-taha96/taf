package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase {

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	WebElement productTitle;
	
	@FindBy(css = "strong.current-item")
	public WebElement productNameBreadCrumb;
	
	@FindBy(css = "button.button-2.email-a-friend-button")
	WebElement emailFriendBtn;

	@FindBy(id = "price-value-4")
	public WebElement productPriceLbl;
	
	@FindBy(linkText = "Add your review")
	WebElement addYourReviewLink;
	
	@FindBy(id = "add-to-wishlist-button-4")
    WebElement addToWishlistBtn;
	
	@FindBy(css = "a.ico-wishlist")
	WebElement wishlistPageLink;
	
	@FindBy(css = "button.button-2.add-to-compare-list-button")
	WebElement addToCompareBtn;
	
	@FindBy(id = "add-to-cart-button-4")
	WebElement addToCartBtn;
	
	public void ClickOnProductTitleToGetMOreDetails() 
	{
		clickButton(productTitle);
	}
	
	public void OpenSendEmailToFriend() 
	{
		clickButton(emailFriendBtn);
	}
	
	public void OpenAddReviewPage() 
	{
		clickButton(addYourReviewLink);
	}
	
	public void AddProductToWishlist() 
	{
		clickButton(addToWishlistBtn);
		clickButton(wishlistPageLink);
	}
	
	public void AddProductToComapre() 
	{
		clickButton(addToCompareBtn);
	}
	
	public void AddToCart() 
	{
		clickButton(addToCartBtn);
	}
}
