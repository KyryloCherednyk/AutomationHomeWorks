package HomeWorks;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HW1 {
    public static void main(String[] args) {

        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://rozetka.com.ua/");

        WebElement image = webDriver.findElement(By.xpath("//img[@alt='Rozetka Logo']"));

        Assert.isTrue(image.isDisplayed(), "Image not found");

        webDriver.quit();
    }
}
