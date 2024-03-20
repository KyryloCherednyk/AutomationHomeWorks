package HomeWorks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HW3 extends BaseTest {

    @Test
    public void hw() {
        String searchValue = "тумба";

        webDriver.get("https://prozorro.gov.ua/en");

        WebElement input = webDriver
                .findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        String result1 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[@class='search-result-card__wrap'][1]//a[@class='item-title__title']")))
                .getText().trim();

        String result2 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[@class='search-result-card__wrap'][1]//span[@class='_setter __v_isRef __v_isReadonly effect _cacheable']")))
                .getText().trim();

        String result3 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("div.search-result-card__description")))
                .getText().trim();

        String textElement1 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("p.app-price__amount"))).getText();

        double result4 = Double.parseDouble(textElement1.replaceAll("[^0-9\\.]", ""));


        List<WebElement> itemHeaders = webDriver
                .findElements(By.xpath("//div[@class='search-result-card']//a[@class='item-title__title']"));

        itemHeaders.get(0).click();

        String actualResult1 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//div[@class='tender--head--title col-sm-9']")))
                .getText().trim();

        String actualResult2 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[@class='marked']")))
                .getText().trim();

        String actualResult3 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("td.col-sm-6")))
                .getText().trim();

        int index = result3.lastIndexOf("•");
        if (index != -1) {
            result3 = result3.substring(0, index).trim();
        }

        String textElement2 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("div.green.tender--description--cost--number strong"))).getText();

        double actualResult4 = Double.parseDouble(textElement2.replaceAll("[^0-9\\.]", ""));


        Assert.assertEquals(result1, actualResult1, "Search value is incorrect");
        Assert.assertEquals(result2, actualResult2, "Search value is incorrect");
        Assert.assertEquals(result3, actualResult3, "Search value is incorrect");
        Assert.assertEquals(result4, actualResult4, "Search value is incorrect");
    }

}

