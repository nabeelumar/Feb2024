package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponenets.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	
	By toastcontainer = By.cssSelector("#toast-container");
	
 

	public List<WebElement> getProductList() {
		waitForElements(productsBy);
		return products;
	}
	
	
	public WebElement getProduct(String productName) {
		
		WebElement productItem = getProductList().stream().filter(product->product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return productItem;
	}
	

	public void addToCartItem(String productName) {
		WebElement productItem = getProduct(productName);
		Actions a = new Actions(driver);
		a.click(productItem.findElement(addToCart)).build().perform();
		waitForElements(toastcontainer);
	}

	



}
