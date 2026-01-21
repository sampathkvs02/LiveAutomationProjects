package tutorial.register;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {

    @Test
    public void verifyRegisteringWithMandatoryFields() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://tutorialsninja.com/demo/");
        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("sampath");
        driver.findElement(By.id("input-lastname")).sendKeys("kodati");
        driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("9381932459");
        driver.findElement(By.id("input-password")).sendKeys("@123322");
        driver.findElement(By.id("input-confirm")).sendKeys("@123322");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // Logout link validation
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        // Heading validation
        String expectedHeading = "Your Account Has Been Created!";
        String actualHeading =
                driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText();
        Assert.assertEquals(actualHeading, expectedHeading);

        // Content validation
        String actualProperDetailsOne = "Your Account Has Been Created!";
        String actualProperDetailsTwo =
                "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetailsThree =
                "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetailsFour = "contact us";

        String expectedProperDetails = driver.findElement(By.id("content")).getText();

        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsOne));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsTwo));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsThree));
        Assert.assertTrue(expectedProperDetails.contains(actualProperDetailsFour));

        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        Assert.assertTrue(
                driver.findElement(By.linkText("Edit your account information")).isDisplayed()
        );

        driver.quit();
    }

    public String generateNewEmail() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter) + "@gmail.com";
    }
}
