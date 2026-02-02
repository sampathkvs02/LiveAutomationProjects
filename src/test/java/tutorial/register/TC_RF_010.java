package tutorial.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
public class TC_RF_010 {
	
	@Test
	public void verifyEmailFieldUsingInvalidEmailFormats() throws InterruptedException, IOException {
		
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	driver.get("http://tutorialsninja.com/demo");
	
	driver.findElement(By.xpath("//span[text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("input-firstname")).sendKeys("John");
	driver.findElement(By.id("input-lastname")).sendKeys("Doe");
	driver.findElement(By.id("input-email")).sendKeys("invalidEmailFormat");
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	driver.findElement(By.id("input-password")).sendKeys("Password");
	driver.findElement(By.id("input-confirm")).sendKeys("Password");
	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	Thread.sleep(2000);
	// Take screenshot of the registration form with error message 1
	File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcScreenshot1,new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
	Assert.assertFalse(compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png",System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));
	// Clear the email field
	driver.findElement(By.id("input-email")).clear();
	// Enter another invalid email format 2
	driver.findElement(By.id("input-email")).sendKeys("amotoori@");
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	Thread.sleep(2000);
	// Take screenshot of the registration form with error message 2
	File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcScreenshot2,new File(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png"));
	Assert.assertFalse(compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png",System.getProperty("user.dir") + "\\Screenshots\\sc2Expected.png"));
	driver.findElement(By.id("input-email")).clear();
	// Enter another invalid email format 3
	driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	Thread.sleep(2000);
	
	File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcScreenshot3,new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png"));
	Assert.assertFalse(compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png",System.getProperty("user.dir") + "\\Screenshots\\sc3Expected.png"));
	driver.findElement(By.id("input-email")).clear();
	
	String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedWarningMessage);
	
	// Enter another invalid email format 4
	driver.findElement(By.id("input-email")).clear();
	driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	Thread.sleep(2000);
	
	File srcScreenshot4 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcScreenshot4,new File(System.getProperty("user.dir") + "\\Screenshots\\sc4Actual.png"));
	Assert.assertFalse(compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc4Actual.png",System.getProperty("user.dir") + "\\Screenshots\\sc4Expected.png"));
	
	driver.quit();
	}
	public boolean compareTwoScreenshots(String actualImgPath, String expectedImgPath) throws IOException {

	    BufferedImage actualBImg = ImageIO.read(new File(actualImgPath));
	    BufferedImage expectedBImg = ImageIO.read(new File(expectedImgPath));

	    ImageDiffer imgDiffer = new ImageDiffer();
	    ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);

	    return imgDifference.hasDiff();
	}


}
