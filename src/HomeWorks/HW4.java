package HomeWorks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class HW4 extends BaseTest {

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"Вікно"},
                {"Ліхтар"},
                {"Колеса"}
        };
    }

    @Test(dataProvider = "searchQueries", groups = "positive")
    public void searchTest(String query) {

        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver
                .findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(query);
        input.sendKeys(Keys.ENTER);

        WebElement searchResults = webDriver.findElement(By.cssSelector("li.search-result-card__wrap a.item-title__title"));
        Assert.assertTrue(searchResults.isDisplayed(), "Search results are not displayed for query: " + query);

    }

    @Test(groups = "positive")
    public void hw() {

        String searchValue = "тумба";
        webDriver.get("https://prozorro.gov.ua/en");

        WebElement input = webDriver
                .findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(searchValue);
        input.sendKeys(Keys.ENTER);

        String resultName = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[@class='search-result-card__wrap'][1]//a[@class='item-title__title']")))
                .getText().trim();

        String resultStatus = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[@class='search-result-card__wrap'][1]//span[@class='_setter __v_isRef __v_isReadonly effect _cacheable']")))
                .getText().trim();

        String resultCompany = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("div.search-result-card__description")))
                .getText().trim();

        String textElement1 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("p.app-price__amount"))).getText();

        double resultPrice = Double.parseDouble(textElement1.replaceAll("[^0-9\\.]", ""));

        List<WebElement> itemHeaders = webDriver
                .findElements(By.xpath("//div[@class='search-result-card']//a[@class='item-title__title']"));

        itemHeaders.get(0).click();

        String actualName = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//div[@class='tender--head--title col-sm-9']")))
                .getText().trim();

        String actualStatus = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//span[@class='marked']")))
                .getText().trim();

        String actualCompany = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("td.col-sm-6")))
                .getText().trim();

        int index = resultCompany.lastIndexOf("•");
        if (index != -1) {
            resultCompany = resultCompany.substring(0, index).trim();
        }

        String textElement2 = new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("div.green.tender--description--cost--number strong"))).getText();

        double actualPrice = Double.parseDouble(textElement2.replaceAll("[^0-9\\.]", ""));

        Assert.assertEquals(resultName, actualName, "Name is incorrect");
        Assert.assertEquals(resultStatus, actualStatus, "Status is incorrect");
        Assert.assertEquals(resultCompany, actualCompany, "Company is incorrect");
        Assert.assertEquals(resultPrice, actualPrice, "Price is incorrect");

    }

    @Test(groups = "negative")
    public void testNegativeSearch() {

        String inputSearch = "fejwnf332";
        webDriver.get("https://prozorro.gov.ua/en");
        WebElement input = webDriver.findElement(By.xpath("//input[@class='search-text__input']"));
        input.clear();
        input.sendKeys(inputSearch);
        input.sendKeys(Keys.ENTER);

        WebElement searchResults = webDriver.findElement(By.xpath("//p[@class='search-result__empty'][1]"));
        Assert.assertTrue(searchResults.isDisplayed(), "Search results are not displayed");

    }
}




