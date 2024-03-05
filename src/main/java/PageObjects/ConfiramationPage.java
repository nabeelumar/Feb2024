package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ConfiramationPage {
	
	WebDriver driver;
	public ConfiramationPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = ".hero-primary")
	WebElement confMsg;
	
	public String geconftext() {
		
		String actualText =confMsg.getText();
		return actualText;
	}
	
	//String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText();


}
