package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//table //tr/td[2]")
	List<WebElement>  orderList;
	
	public boolean getOrderList(String orderName) {
		
		boolean orderPresent = orderList.stream().anyMatch(order->order.getText().equals(orderName));
		return orderPresent;
	}

}
