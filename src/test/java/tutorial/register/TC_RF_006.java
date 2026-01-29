package tutorial.register;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_006 {
	
	@Test
	public void verifyRegisterAccountBySayingNoToNewsletter() throws Exception {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver. manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("venkata Sampath");
		driver.findElement(By.id("input-lastname")).sendKeys("kodati");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1233567890");
		driver.findElement(By.id("input-password")).sendKeys("@Sampath");
		driver.findElement(By.id("input-confirm")).sendKeys("@Sampath");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Thread.sleep(2000);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Newsletter']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).isSelected());
		
		driver.quit();
		
	}
	
	public String generateNewEmail() {
		
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	        return LocalDateTime.now().format(formatter) + "@gmail.com";
		
	}

}
