package andoorathodi.MavenFeb2024;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ConfiramationPage;
import PageObjects.LandingPage;
import PageObjects.OrdersPage;
import PageObjects.ProductCatalogue;
import TestComponenets.BaseTest;

public class standAloneTest extends BaseTest{
	String itemName = "ADIDAS ORIGINAL";
	@Test(dataProvider="dataTest", groups= {"smoke"}, retryAnalyzer=TestComponenets.Retry.class)
	public void placeOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		System.out.println("USERNAME is "+input.get("username"));
		System.out.println("PASSWROD is "+input .get("password"));
		
		
		
		ProductCatalogue pc = lp.loginApplication(input.get("username"), input .get("password"));
		
		
		//ProductCatalogue pc = new ProductCatalogue(driver);
		pc.addToCartItem(itemName);
		CartPage cp  =  pc.cart();
		
		//CartPage cp = new CartPage(driver);
		boolean present= cp.itemPresent(itemName);
		Assert.assertTrue(present);
		CheckOutPage co =cp.checkOutItem();
		co.selectCountry("India");
		ConfiramationPage cop = co.placeOrder();
		String actualText = cop.geconftext();
		System.out.println("Acutal msg is : " + actualText);
		Assert.assertEquals(actualText, "THANKYOU FOR THE ORDER.");
	}
	
	
	@DataProvider(name = "dataTest")
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/data/credentials.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
		/*HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("username", "nabeelans@gmail.com");
		map1.put("password", "Abcd@123");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("username", "sathish123@gmail.com");
		map2.put("password", "Abcd@123");
		return new Object[][] {{map1},{map2}};*/
		
		//return new Object[][] {{"nabeelans@gmail.com", "Abcd@123"}, {"sathish123@gmail.com", "Abcd@123"}};
		
	}
	
	@Test(dependsOnMethods="placeOrder", groups= {"smoke"})
	public void ordersTest() {
		
		ProductCatalogue pc = lp.loginApplication("nabeelans@gmail.com", "Abcd@123");
		OrdersPage op = pc.clickOrdersLink();
		boolean orderPresent= op.getOrderList(itemName);
		System.out.println("Order PRESENT IS "+orderPresent);
		Assert.assertTrue(orderPresent);
	}
	
	

	@Test(dependsOnMethods="placeOrder", groups= {"smoke"})
	public void negativeScenario() {
		
		ProductCatalogue pc = lp.loginApplication("shibil@gmail.com", "Abcd@123");
		OrdersPage op = pc.clickOrdersLink();
		boolean orderPresent= op.getOrderList(itemName);
		System.out.println("Order PRESENT IS "+orderPresent);
		Assert.assertTrue(orderPresent);
	}

}
