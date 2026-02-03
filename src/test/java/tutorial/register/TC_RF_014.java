package tutorial.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_014 {
	
	WebDriver driver;
	
	@AfterMethod
	public void tearDown() {
			
			driver.quit(); 
		}


    @Test
    public void verifyMandatoryFieldsSymbolInRegisterAccountPage() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String expectedFNContent = "*";
        String expectedFNColor = "rgb(255, 0, 0)";

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // ---------- First Name ----------
        WebElement firstNameLabel = driver.findElement(By.cssSelector("label[for='input-firstname']"));
        String fnContent = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                firstNameLabel);
        fnContent = fnContent.replace("\"", "").trim();
        String fnColor = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                firstNameLabel);

        System.out.println("First Name Content: " + fnContent);
        System.out.println("First Name Color: " + fnColor);

        Assert.assertEquals(fnContent, expectedFNContent);
        Assert.assertEquals(fnColor, expectedFNColor);

        // ---------- Last Name ----------
        WebElement lastNameLabel = driver.findElement(By.cssSelector("label[for='input-lastname']"));
        String lnContent = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                lastNameLabel);
        lnContent = lnContent.replace("\"", "").trim();
        String lnColor = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                lastNameLabel);

        System.out.println("Last Name Content: " + lnContent);
        System.out.println("Last Name Color: " + lnColor);

        Assert.assertEquals(lnContent, expectedFNContent);
        Assert.assertEquals(lnColor, expectedFNColor);

        // ---------- Email ----------
        WebElement emailLabel = driver.findElement(By.cssSelector("label[for='input-email']"));
        String emailContent = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                emailLabel);
        emailContent = emailContent.replace("\"", "").trim();
        String emailColor = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                emailLabel);

        System.out.println("Email Content: " + emailContent);
        System.out.println("Email Color: " + emailColor);

        Assert.assertEquals(emailContent, expectedFNContent);
        Assert.assertEquals(emailColor, expectedFNColor);

        // ---------- Telephone ----------
        WebElement telephoneLabel = driver.findElement(By.cssSelector("label[for='input-telephone']"));
        String telephoneContent = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                telephoneLabel);
        telephoneContent = telephoneContent.replace("\"", "").trim();
        String telephoneColor = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                telephoneLabel);

        System.out.println("Telephone Content: " + telephoneContent);
        System.out.println("Telephone Color: " + telephoneColor);

        Assert.assertEquals(telephoneContent, expectedFNContent);
        Assert.assertEquals(telephoneColor, expectedFNColor);

        // ---------- Password ----------
        WebElement passwordLabel = driver.findElement(By.cssSelector("label[for='input-password']"));
        String passwordContent = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
                passwordLabel);
        passwordContent = passwordContent.replace("\"", "").trim();
        String passwordColor = (String) jse.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",
                passwordLabel);

        System.out.println("Password Content: " + passwordContent);
        System.out.println("Password Color: " + passwordColor);

        Assert.assertEquals(passwordContent, expectedFNContent);
        Assert.assertEquals(passwordColor, expectedFNColor);

        // ---------- Confirm Password ----------
        WebElement confirmPasswordLabel = driver.findElement(By.cssSelector("label[for='input-confirm']"));
        String confirmPasswordContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",confirmPasswordLabel);
        confirmPasswordContent = confirmPasswordContent.replace("\"", "").trim();
        String confirmPasswordColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",confirmPasswordLabel);
        System.out.println("Confirm Password Content: " + confirmPasswordContent);
        System.out.println("Confirm Password Color: " + confirmPasswordColor);
        Assert.assertEquals(confirmPasswordContent, expectedFNContent);
        Assert.assertEquals(confirmPasswordColor, expectedFNColor);
        
        //----------------privacy policy----------------
        WebElement privacyPolicyLabel = driver.findElement(By.cssSelector(".pull-right"));
        String privacypolicyContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",privacyPolicyLabel);
        privacypolicyContent = privacypolicyContent.replace("\"", "").trim();
        String privacypolicyColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');",privacyPolicyLabel);
        System.out.println("Privacy Policy Content: " + privacypolicyContent);
        System.out.println("Privacy Policy Color: " + privacypolicyColor);


        driver.quit();
    }
}
