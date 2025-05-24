package Register;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_03Copy {

    ChromeDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        extent = ExtentReportManager.getInstance();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TestCase02() {
        test = extent.createTest("Register Test Case 02", "Validates successful registration process");

        try {
            driver.get("http://tutorialsninja.com/demo");
            test.info("Navigated to TutorialsNinja site");

            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();
            test.info("Navigated to Registration page");

            driver.findElement(By.id("input-firstname")).sendKeys("Amir");
            driver.findElement(By.id("input-lastname")).sendKeys("Sutar");
            String NewEmail = "NewUser" + Instant.now().getEpochSecond() + "@gmail.com";
            driver.findElement(By.id("input-email")).sendKeys(NewEmail);
            driver.findElement(By.id("input-telephone")).sendKeys("9834977236");
            driver.findElement(By.id("input-password")).sendKeys("Amir@123");
            driver.findElement(By.id("input-confirm")).sendKeys("Amir@123");
            test.info("Filled in registration form");

            driver.findElement(By.xpath("//label[normalize-space()='Yes']")).click();
            driver.findElement(By.name("agree")).click();
            new Actions(driver).moveToElement(driver.findElement(By.className("btn-primary"))).perform();
            driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
            test.pass("Submitted the registration form");

            Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
            test.pass("Logout link is visible after registration");

            String expectedHeading = "Your Account Has Been Created!";
            WebElement actualHeading = driver.findElement(By.xpath("//div[@id='content']//h1"));
            Assert.assertEquals(expectedHeading, actualHeading.getText());
            test.pass("Verified confirmation message: 'Your Account Has Been Created!'");

            // Verify page information
            for (int i = 1; i <= 4; i++) {
                WebElement detail = driver.findElement(By.xpath("//div[@id='content']//p[" + i + "]"));
                Assert.assertTrue(detail.isDisplayed());
                test.pass("Paragraph " + i + " displayed correctly");
            }

            WebElement contactUs = driver.findElement(By.linkText("contact us"));
            Assert.assertTrue(contactUs.isDisplayed());
            test.pass("'Contact us' link is displayed");

            driver.findElement(By.linkText("Continue")).click();
            Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
            test.pass("User landed on 'My Account' page after registration");

        } catch (Exception e) {
            test.fail("Test Case Failed: " + e.getMessage());
            throw e;
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
