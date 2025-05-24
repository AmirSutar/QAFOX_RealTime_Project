package Register;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class TC_07 {

	@Test

	public void TestCase07() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		String Tab = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Register")).click();
		String Expected_Register_Account = "Register Account";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id = 'content']//h1")).getText(),
				Expected_Register_Account);

		System.out.println("Suucesfully redirected to regstering page--> 01");

		driver.switchTo().newWindow(WindowType.TAB);
		String Tab1 = driver.getWindowHandle();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id = 'content']//h1")).getText(),
				Expected_Register_Account);

		System.out.println("Suucesfully redirected to regstering page--> 02");

		driver.switchTo().newWindow(WindowType.TAB);
		String Tab2 = driver.getWindowHandle();
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id = 'content']//h1")).getText(),
				Expected_Register_Account);

		System.out.println("Suucesfully redirected to regstering page--> 03");

		driver.close();
		driver.switchTo().window(Tab1);
		driver.close();
		driver.switchTo().window(Tab);
		driver.close();

	}

	/*
	 * @Test public void TestCase08() {
	 * 
	 * WebDriverManager.chromedriver().setup(); ChromeDriver driver = new
	 * ChromeDriver();
	 * 
	 * driver.get("http://tutorialsninja.com/demo");
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
	 * driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 * driver.findElement(By.linkText("Login")).click();
	 * driver.findElement(By.linkText("Continue")).click();
	 * 
	 * }
	 * 
	 * @Test public void TestCase09() {
	 * 
	 * WebDriverManager.chromedriver().setup(); ChromeDriver driver = new
	 * ChromeDriver();
	 * 
	 * driver.get("http://tutorialsninja.com/demo");
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
	 * driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 * driver.findElement(By.linkText("Login")).click();
	 * driver.findElement(By.linkText("Register")).click();
	 * 
	 * }
	 */

}
