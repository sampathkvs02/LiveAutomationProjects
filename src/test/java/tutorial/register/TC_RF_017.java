package tutorial.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Utils.CommonUtils;


	public class TC_RF_017 {
		
		
		WebDriver driver;

		@AfterMethod
		public void teardown() {
			driver.quit();
		}
	
		@Test
		public void verifyRegisterAccountAndCheckingPasswordComplexity() {
		
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
			driver.get("https://tutorialsninja.com/demo/");
			
			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Register")).click();
			driver.findElement(By.id("input-firstname")).sendKeys("John");
			driver.findElement(By.id("input-lastname")).sendKeys("Doe");
			driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
			driver.findElement(By.id("input-telephone")).sendKeys("923112456");
			driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.id("input-password")).sendKeys("12345");
			driver.findElement(By.id("input-confirm")).sendKeys("12345");
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
			String expectedWarningMessage = "Password must be between 4 and 20 characters!";
			
			Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedWarningMessage);
			Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()=success']")).isDisplayed(), false);
			
		
	}

}
