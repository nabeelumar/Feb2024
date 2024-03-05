package AbstractComponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartLink;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersLink;
	

	public void waitForElements(By FindBy) {

		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(3));
		wd.until(ExpectedConditions.visibilityOfElementLocated(FindBy));

	}

	public CartPage cart() {

		Actions a = new Actions(driver);
		a.click(cartLink).build().perform();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrdersPage clickOrdersLink() {
		
		Actions a = new Actions(driver);
		a.click(ordersLink).build().perform();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}
}
