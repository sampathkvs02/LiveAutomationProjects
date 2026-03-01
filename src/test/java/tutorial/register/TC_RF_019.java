package tutorial.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_RF_019 {
	
	WebDriver driver;
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test
	public void verifyLeadingAndTrailingSpacesWhileRegistering() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		String enteredFirstName = "  John  ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = " venkata ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "   "+CommonUtils.generateBrandNewEmail()+"   ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredtelephone = "        9381932459      ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredtelephone);
		driver.findElement(By.id("input-password")).sendKeys("1234");
		driver.findElement(By.id("input-confirm")).sendKeys("1234");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary'][text()='Continue']")).click();
		driver.findElement(By.linkText("Edit your account information")).click();
		
		Assert.assertEquals(driver.findElements(By.id("input-firstname")).getFirst(),enteredFirstName.trim());
		Assert.assertEquals(driver.findElements(By.id("input-lastname")).getFirst(),enteredLastName.trim());
		Assert.assertEquals(driver.findElements(By.id("input-email")).getFirst(),enteredEmail.trim());
		Assert.assertEquals(driver.findElements(By.id("input-telephone")).getFirst(),enteredtelephone.trim());
		
	}

}
