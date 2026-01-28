package tutorial.register;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_005 {
	
	@Test
	public void verifyRegisterAccountBySubscribingNewsletter() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver. manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Doe");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("@Sampath123");
		driver.findElement(By.id("input-confirm")).sendKeys("@Sampath123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Newsletter']")).isDisplayed());
		WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
		Assert.assertTrue(newsletterYes.isSelected());
		
		driver.quit();
			
}

	
	public String generateNewEmail() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter) + "@gmail.com";
		
	}

}
