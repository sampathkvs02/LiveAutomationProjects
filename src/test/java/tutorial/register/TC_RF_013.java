package tutorial.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_013 {
	
	@Test
	public void verifyPlaceHolderTextFieldsInRegisterAccountPage() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		String expectedFirstNamePlaceholder = "First Name";
		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), expectedFirstNamePlaceholder);
		
		String expectedLastNamePlaceholder = "Last Name";
		Assert.assertEquals(driver.findElements(By.id("input-lastname")).get(0).getAttribute("placeholder"), expectedLastNamePlaceholder);
		
		String expectedEmailPlaceholder ="E-Mail";
		Assert.assertEquals(driver.findElements(By.id("input-email")).get(0).getAttribute("placeholder"), expectedEmailPlaceholder);
		
		String expectedTelephonePlaceholder = "Telephone";
		Assert.assertEquals(driver.findElements(By.id("input-telephone")).get(0).getAttribute("placeholder"), expectedTelephonePlaceholder);
		
		String expectedPasswordPlaceholder = "Password";
		Assert.assertEquals(driver.findElements(By.id("input-password")).get(0).getAttribute("placeholder"), expectedPasswordPlaceholder);
		
		String expectedConfirmPasswordPlaceholder = "Password Confirm";
		Assert.assertEquals(driver.findElements(By.id("input-confirm")).get(0).getAttribute("placeholder"), expectedConfirmPasswordPlaceholder);
		
		driver.quit();
	
		
	}

}
