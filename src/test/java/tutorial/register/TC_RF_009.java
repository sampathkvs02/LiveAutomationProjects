package tutorial.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_009 {
	
	@Test
	public void verifyRegisteringAccountUsingExistingEmail() throws Exception {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("John");
		driver.findElement(By.id("input-lastname")).sendKeys("Doe");
		driver.findElement(By.id("input-email")).sendKeys("venkatasampathkodati@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Password");
		driver.findElement(By.id("input-confirm")).sendKeys("Password");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedErrorMessage = "Warning: E-Mail Address is already registered!";
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedErrorMessage));
		
		Thread.sleep(3000);
		
		driver.quit();
		
	
	}
	
}
