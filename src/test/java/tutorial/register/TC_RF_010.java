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
	
	Thread.sleep(3000);
	
	File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
	FileHandler.copy(srcScreenshot1,new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
	
	BufferedImage acutualBImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
	BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));
	
	
	ImageDiffer imgDiffer = new ImageDiffer();
	ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);
	
	Assert.assertFalse(imgDifference.hasDiff());
	
	driver.quit();
	
	
	
	}
	

}
