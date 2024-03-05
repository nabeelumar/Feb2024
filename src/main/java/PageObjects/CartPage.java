package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CartPage {

	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> items;
	
	
	@FindBy(css=".totalRow .btn-primary")
	WebElement checkOut;
	


	
	public Boolean itemPresent(String productName) throws InterruptedException {
		//System.out.println("LIST size is "+items.size());
		System.out.println("ITEM is "+productName);
	
		Thread.sleep(3000);
		boolean Present = items.stream().anyMatch(item -> item.getText().equals(productName));


		return Present ;
	}
	
	public CheckOutPage checkOutItem() {
		
		checkOut.click();
		CheckOutPage co = new CheckOutPage(driver);
		return co;
	}
	
	
	
}
