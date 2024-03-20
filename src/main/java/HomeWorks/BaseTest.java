package HomeWorks;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver webDriver;

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,720");

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Attachment(type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        byte[] result = null;
        if (driver != null) {
            result = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }
        return result;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        webDriver.quit();
    }
}


