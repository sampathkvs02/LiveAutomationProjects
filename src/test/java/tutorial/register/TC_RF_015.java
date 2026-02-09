package tutorial.register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_RF_015 {
	
	// JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3307/opencart_db";
    private static final String USER = "root";
    private static final String PASSWORD = "sampath123";
	
	@Test
	public void verifyDataTestingOfRegisteringAccount() {
		
		 	WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(3));

	        driver.get("http://localhost/opencart/");

	        driver.findElement(By.xpath("//span[text()='My Account']")).click();
	        driver.findElement(By.linkText("Register")).click();

	        String firstNameInputData = "John";
	        driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);

	        String LastNameInputData = "Doe";
	        driver.findElement(By.id("input-lastname")).sendKeys(LastNameInputData);

	        String emailInputData = CommonUtils.generateBrandNewEmail();
	        driver.findElement(By.id("input-email")).sendKeys(emailInputData);

	        String passwordInputData = "123456";   // minimum 6 chars required
	        driver.findElement(By.id("input-password")).sendKeys(passwordInputData);

	        // ===== FIX FOR CLICK ERROR (minimal change only) =====
	        org.openqa.selenium.JavascriptExecutor js =
	                (org.openqa.selenium.JavascriptExecutor) driver;

	        js.executeScript("window.scrollBy(0,300)");

	        new org.openqa.selenium.support.ui.WebDriverWait(driver,
	                java.time.Duration.ofSeconds(10))
	                .until(org.openqa.selenium.support.ui.ExpectedConditions
	                        .elementToBeClickable(By.id("input-newsletter")));
	        // ====================================================

	        driver.findElement(By.xpath("//input[@id='input-newsletter']")).click();

	        driver.findElement(By.xpath("//input[@name='agree']")).click();

	        driver.findElement(By.xpath("//button[text()='Continue']")).click();
        
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        String firstNameStoredInDatabase = null;
        String lastNameStoredInDatabase = null;
        String emailStoredInDatabase = null;
        
        try {
            // Step 1: Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");

            // Step 2: Create a statement
            statement = connection.createStatement();
            
            // Step 3: Execute a query
            String sql = "SELECT firstname, lastname, email FROM oc_customer WHERE email='" + emailInputData + "'";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                firstNameStoredInDatabase = resultSet.getString("firstname");
                lastNameStoredInDatabase = resultSet.getString("lastname");
                emailStoredInDatabase = resultSet.getString("email");
            }

            
            // Step 4: Process the result set
            while (resultSet.next()) {
            	firstNameStoredInDatabase = resultSet.getString("firstname");
                lastNameStoredInDatabase = resultSet.getString("lastname");
                emailStoredInDatabase = resultSet.getString("email");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Clean up the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        
        
	    Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData);
	    Assert.assertEquals(lastNameStoredInDatabase, LastNameInputData);
	    Assert.assertEquals(emailStoredInDatabase, emailInputData);
	    
	    driver.quit();
		
	}

}