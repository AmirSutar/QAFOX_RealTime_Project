package Register;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_01 {

    ExtentReports extent;
    ExtentTest test;
    ChromeDriver driver;

    @BeforeClass
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TestCase01() {
        test = extent.createTest("Register Test Case 01", "Verifies user registration flow");

        try {
            driver.get("http://tutorialsninja.com/demo");
            test.info("Navigated to TutorialsNinja site");

            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();
            test.info("Clicked on Register");

            driver.findElement(By.id("input-firstname")).sendKeys("Amir");
            driver.findElement(By.id("input-lastname")).sendKeys("Sutar");
            String NewEmail = "NewUser" + Instant.now().getEpochSecond() + "@gmail.com";
            driver.findElement(By.id("input-email")).sendKeys(NewEmail);
            test.info("Entered registration details");

            Actions scroll = new Actions(driver);
            WebElement ButtonContinue = driver.findElement(By.cssSelector(".btn.btn-primary"));
            scroll.moveToElement(ButtonContinue).perform();

            driver.findElement(By.id("input-telephone")).sendKeys("9834977236");
            driver.findElement(By.id("input-password")).sendKeys("Amir@123");
            driver.findElement(By.id("input-confirm")).sendKeys("Amir@123");
            driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
            driver.findElement(By.name("agree")).click();
            driver.findElement(By.cssSelector("input[value='Continue']")).click();

            test.pass("Form submitted");

            Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
            test.pass("Logout link displayed - Account created");

            String expectedHeading = "Your Account Has Been Created!";
            WebElement actualHeading = driver.findElement(By.xpath("//div[@id=\"content\"]//h1"));
            Assert.assertEquals(expectedHeading, actualHeading.getText());
            test.pass("Account creation message verified");

            // Verify paragraphs
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"content\"]//p[1]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"content\"]//p[2]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"content\"]//p[3]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"content\"]//p[4]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.linkText("contact us")).isDisplayed());

            test.pass("Account information paragraphs displayed");

            driver.findElement(By.linkText("Continue")).click();
            Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
            test.pass("Navigated to 'Account' page");

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage());
            throw e; // Rethrow so test still fails in TestNG
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
