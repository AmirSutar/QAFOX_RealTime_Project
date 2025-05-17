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

public class TC_01 {

	@Test

	public void TestCase01() {

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
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Amir@123");
		driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		String ExpectedHeading = "Your Account Has Been Created!";
		WebElement ActualHeading = driver.findElement(By.xpath("//div[@id=\"content\"]//h1"));
		Assert.assertEquals(ExpectedHeading, ActualHeading.getText());
		
		WebElement ProperDetails1 = driver.findElement(By.xpath("//div[@id=\"content\"]//p[1]"));
		WebElement ProperDetails2 = driver.findElement(By.xpath("//div[@id=\"content\"]//p[2]"));
		WebElement ProperDetails3 = driver.findElement(By.xpath("//div[@id=\"content\"]//p[3]"));
		WebElement ProperDetails4 = driver.findElement(By.xpath("//div[@id=\"content\"]//p[4]"));
		WebElement ProperDetails5 = driver.findElement(By.linkText("contact us"));
		
		Assert.assertTrue(ProperDetails1.isDisplayed());
		System.out.println("Information Dispayed 01");
		Assert.assertTrue(ProperDetails2.isDisplayed());
		System.out.println("Information Dispayed 02");
		Assert.assertTrue(ProperDetails3.isDisplayed());
		System.out.println("Information Dispayed 03");
		Assert.assertTrue(ProperDetails4.isDisplayed());
		System.out.println("Information Dispayed 04");
		Assert.assertTrue(ProperDetails5.isDisplayed());
		System.out.println("Information Dispayed 05");
		
		driver.findElement(By.linkText("Continue")).click();
		
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		System.out.println("User has been taken to 'Account' page");

		
		

		
		
		
		
		driver.close();
				

	}

}
