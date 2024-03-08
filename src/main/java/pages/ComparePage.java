package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparePage extends PageBase {

	public ComparePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "a.clear-list")
	WebElement clearListBtn;
	
	@FindBy(css = "table.compare-products-table")
	WebElement compareTable;
	
	@FindBy(css = "div.no-data")
	public WebElement noDataMessage;
	
	@FindBy(tagName = "tr")
	List<WebElement> allRows;
	
	@FindBy(tagName = "td")
	List<WebElement> allCollumns;
	
	@FindBy(linkText = "Apple MacBook Pro 13-inch")
	public WebElement firstProductName;
	
	@FindBy(linkText = "Asus N551JK-XO076H Laptop")
	public WebElement secondProductName;
	
	public void CompareProducts() 
	{
		//Get all rows
		System.out.println(allRows.size());
		
		//Get data from each row
		for(WebElement row : allRows) 
		{
			System.out.println(row.getText()+"\t");
			
			for(WebElement col : allCollumns) 
			{
				System.out.println(col.getText()+"\t");
			}
		}
	}
	
	public void ClearCompareList() 
	{
		clickButton(clearListBtn);
	}
}
