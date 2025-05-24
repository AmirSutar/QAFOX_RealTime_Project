package Register;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_04 {

	@Test

	public void TestCase04() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Register")).click();
				
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		String Epected_First_Name_warning ="First Name must be between 1 and 32 characters!";
		String Epected_Last_Name_warning ="Last Name must be between 1 and 32 characters!";
		String Epected_Mail_warning = "E-Mail Address does not appear to be valid!";
		String Epected_Telephone_warning ="Telephone must be between 3 and 32 characters!";
		String Epected_Password_warning ="Password must be between 4 and 20 characters!";
		String Epected_Privacy_Policy_warning ="Warning: You must agree to the Privacy Policy!";
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).getText(), Epected_First_Name_warning);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text() , 'Last Name must be between 1 and 32 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text() , 'E-Mail Address does not appear to be valid!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text() , 'Telephone must be between 3 and 32 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text() , 'Password must be between 4 and 20 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text() , 'Warning: You must agree to the Privacy Policy!')]")).isDisplayed());
		System.out.println("All Warinings Displayed");

		

	}
}
