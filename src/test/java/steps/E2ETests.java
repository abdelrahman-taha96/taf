package steps;


import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import tests.TestBase;

public class E2ETests extends TestBase {

	SearchPage searchObject;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartObject;
	CheckoutPage checkoutObject;
	OrderDetailsPage orderObject;
	String productName = "Apple MacBook Pro 13-inch";

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() {
		Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String productName) throws InterruptedException {
		searchObject = new SearchPage(driver);
		searchObject.ProductSearchUsingAutoSuggest(productName);
		productDetails = new ProductDetailsPage(driver);
		Assert.assertTrue(productDetails.productNameBreadCrumb.getText().contains(productName));
	}

	@When("^choose to buy Two items$")
	public void choose_to_buy_Two_items() throws InterruptedException {
		cartObject = new ShoppingCartPage(driver);
		productDetails.AddToCart();
		driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}

	@When("^moves to checkout cart and enter personal details on checkout page and place the order$")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException {
		checkoutObject = new CheckoutPage(driver);
		cartObject.openCheckoutPage();
		checkoutObject.CheckoutProduct("Abdelrahman", "Hassan", "abdos.test588@gmail.com", "Egypt", "Cairo", "Fatma elzahraa", "114423", "01141364321");;
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.ConfirmOrder();
		Assert.assertTrue(checkoutObject.thankYouLbl.isDisplayed());
		
	}

	@Then("^he can view the order and download the invoice$")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
		orderObject = new OrderDetailsPage(driver);
		checkoutObject.ViewOrderDetails();;
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject.DownloadPdfInvoice();
		Thread.sleep(3000);
		orderObject.PrintOrderDetails();
	}
}
