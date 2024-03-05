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

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	By results = By.cssSelector(".ta-results");

	@FindBy(css = ".ta-results button:last-of-type")
	WebElement selectedCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	public void selectCountry(String ctry) {

		Actions a = new Actions(driver);
		a.sendKeys(country, ctry).build().perform();
		waitForElements(results);
		selectedCountry.click();

	}

	public ConfiramationPage placeOrder() {
		submit.click();
		ConfiramationPage cop = new ConfiramationPage(driver);
		return cop;

	}

//	wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	// driver.findElement(By.cssSelector(".ta-results
	// button:last-of-type")).click();
	// driver.findElement(By.cssSelector(".action__submit")).click();
}
