package andoorathodi.MavenFeb2024;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Ecommerce {

	@Test
	public void placeOrder() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("http://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("nabeelans@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Abcd@123");
		driver.findElement(By.cssSelector("#login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement productItem = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals("ADIDAS ORIGINAL")).findFirst()
				.orElse(null);
		Thread.sleep(2000);
		System.out.println(productItem.findElement(By.tagName("b")).getText());
		// wd.until(ExpectedConditions.elementToBeClickable(productItem.findElement(By.cssSelector(".card-body
		// button:last-of-type"))));
		Actions a = new Actions(driver);
		a.click(productItem.findElement(By.cssSelector(".card-body button:last-of-type"))).build().perform();

		wd.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		a.click(driver.findElement(By.cssSelector("[routerlink*='cart']"))).build().perform();
//	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> items = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean itemPresent = items.stream().anyMatch(item -> item.getText().equals("ADIDAS ORIGINAL"));
		Assert.assertTrue(itemPresent);

		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		// productItem.findElement(By.cssSelector(".card-body
		// button:last-of-type")).click();

		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String actualText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println("Acutal msg is : " + actualText);
		Assert.assertEquals(actualText, "THANKYOU FOR THE ORDER.");

	}

}
