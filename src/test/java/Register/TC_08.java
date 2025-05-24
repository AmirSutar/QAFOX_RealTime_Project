package Register;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class TC_08 {

	@Test

	public void TestCase08() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://tutorialsninja.com/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Amir");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Sutar");
		String NewEmail = "NewUser" + Instant.now().getEpochSecond() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(NewEmail);
		Actions scroll = new Actions(driver);
		WebElement ButtonContinue = driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]"));
		scroll.moveToElement(ButtonContinue).perform();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9834977236");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Amir@123");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Amirs@123");
		driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String ExpectedDangerText = "Password confirmation does not match password!";
		WebElement ActualDangerText = driver.findElement(By.xpath("//div[@class = 'text-danger']"));
		
	Assert.assertEquals(ActualDangerText.getText(), ExpectedDangerText);
	
	System.out.println("Account did not created, instead a warning message shown as - 'Password confirmation does not match password!'");
		
		driver.close();

	}
}
