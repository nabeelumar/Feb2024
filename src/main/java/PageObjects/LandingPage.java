package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement username;

	@FindBy(css = "#userPassword")
	WebElement password;

	@FindBy(css = "#login")
	WebElement submit;

	public void goTo() {

		driver.get("http://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String uname, String pwd) {
		
		username.sendKeys(uname);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
		
	}
}
