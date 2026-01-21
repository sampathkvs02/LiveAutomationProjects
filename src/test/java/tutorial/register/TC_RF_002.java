package tutorial.register;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class TC_RF_002 {

    WebDriver driver;
    WebDriverWait wait;

    // ===== GMAIL CONFIG =====
    private static final String GMAIL_USER = "venkatasampathkodati@gmail.com";
    private static final String APP_PASSWORD = "ztcurcilamrmujnq";

    // ===== LOGIN DATA =====
    private static final String USERNAME = "venkatasampathkodati@gmail.com";
    private static final String PASSWORD = "@Amma123";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void verifyLoginUsingEmailOTP() throws Exception {

        driver.get("https://signon.servicenow.com/x_snc_sso_auth.do?pageId=login");

        // ✅ SWITCH TO LOGIN IFRAME (VERY IMPORTANT)
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ✅ USERNAME
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("venkatasampathkodati@gmail.com");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // ✅ NEXT BUTTON
        wait.until(ExpectedConditions.elementToBeClickable(By.id("identify-submit"))).click();

        // ✅ PASSWORD (FIXED)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")))
                .sendKeys("@Amma123");

        // ✅ LOGIN BUTTON
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("challenge-authenticator-submit"))).click();
        
        Thread.sleep(50000);
        
        // ✅ WAIT & READ OTP
        String otp = waitForOtpWithRetry(5, 10000);
        System.out.println("OTP RECEIVED: " + otp);

        Assert.assertNotNull(otp, "OTP NOT RECEIVED");

        // ⚠️ Switch back if OTP is outside iframe
        driver.switchTo().defaultContent();

        // (OTP field id may vary – update if needed)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otp")))
                .sendKeys(otp);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("verify")))
                .click();

        System.out.println("✅ LOGIN SUCCESSFUL USING OTP");
    }

    // ===== OTP RETRY =====
    private String waitForOtpWithRetry(int attempts, int waitMillis) throws Exception {
        for (int i = 1; i <= attempts; i++) {
            String otp = readOtpFromEmail();
            if (otp != null) {
                return otp;
            }
            Thread.sleep(waitMillis);
        }
        return null;
    }

    // ===== READ OTP FROM GMAIL =====
    private String readOtpFromEmail() throws Exception {

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");
        props.put("mail.imap.ssl.enable", "true");

        Session session = Session.getInstance(props);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", GMAIL_USER, APP_PASSWORD);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        Message[] messages = inbox.getMessages();

        for (int i = messages.length - 1; i >= Math.max(0, messages.length - 5); i--) {
            Message msg = messages[i];
            String body = getTextFromMessage(msg);

            Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(body);
            if (matcher.find()) {
                inbox.close(false);
                store.close();
                return matcher.group();
            }
        }

        inbox.close(false);
        store.close();
        return null;
    }

    // ===== EMAIL BODY =====
    private String getTextFromMessage(Message message) throws Exception {

        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }

        if (message.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) message.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    return bp.getContent().toString();
                }
            }
        }
        return "";
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
