package lesson4.exercise7;

import lesson2.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.seleniumtest.constants.DriverFactory.clearDriver;
import static com.seleniumtest.constants.DriverFactory.getChrome;
import static org.assertj.core.api.Assertions.assertThat;

public class BadgeTest extends TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @AfterAll
    static void afterAll() {
        clearDriver(driver);
    }

    @BeforeAll
    static void beforeAll(){
        driver = getChrome();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    void every_item_has_a_badge(){
        driver.navigate().to("http://localhost/litecart");

        //count all items on main page
        List<WebElement> allItems = driver.findElements(By.xpath("//div[@class='content']//a[@class='link']"));

        //count items with only one badge
        List<WebElement> itemsWithBadge = driver.findElements(By.xpath("//div[@class='content']//a[count(.//div[contains(@class, 'sticker')])=1]"));

        //all items are items with only one badge
        assertThat(allItems).containsExactlyInAnyOrder(itemsWithBadge.toArray(new WebElement[0]));

    }

}
