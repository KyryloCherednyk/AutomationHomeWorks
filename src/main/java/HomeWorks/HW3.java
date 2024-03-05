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

public class HW3 extends BaseTest{
    @Test
    public void hw()
    {
        String searchValue ="тумба";
        String result1 = "Меблі(тумба мобільна,тумба,тумба приставна)";
        String result2 = "Комунальне некомерційне підприємство Білоцерківської міської ради \"Білоцерківська міська лікарня №2\"";
        String result3 ="Завершена";
        String result4 ="98 940,00\n" +
                "UAH";
        webDriver.get("https://prozorro.gov.ua/en");

        WebElement input = webDriver
                .findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        List<WebElement> itemHeaders = webDriver
                .findElements(By.xpath("//div[@class='search-result-card']//a[@class='item-title__title']"));

        itemHeaders.get(0).click();

        String actualResult = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//div[@class='tender--head--title col-sm-9']")))
                .getText().trim();

        String actualResult1 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//td[contains(., 'Найменування:')]/following-sibling::td")))
                .getText().trim();

        String actualResult2 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[@class='marked']")))
                .getText().trim();

        String actualResult3 = new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//div[@class='green tender--description--cost--number']/strong")))
                .getText().trim();


        Assert.assertEquals(actualResult,result1,"Search value is incorrect");
        Assert.assertEquals(actualResult1,result2,"Search value is incorrect");
        Assert.assertEquals(actualResult2,result3,"Search value is incorrect");
        Assert.assertEquals(actualResult3,result4,"Search value is incorrect");

    }

}

