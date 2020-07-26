package tests.lesson4.exercise7;

import com.seleniumtest.constants.TestUtils;
import tests.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.seleniumtest.constants.constants.CredentialsData.CREDENTIALS_DATA;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SidebarTest extends TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @AfterAll
    static void afterAll() {
        driver.quit();
        driver = null;
    }

    @MethodSource
    @ParameterizedTest
    void open_sidebar_subtabs(String headerToAssert, List<By> tabsToClick) {

        driver.navigate().to("http://localhost/litecart/admin/");

        for(By tab : tabsToClick){
            driver.findElement(tab).click();
            TestUtils.testWait(500L);
        }

        By headerLocator = By.xpath(String.format("//h1[contains(text(),'%s')]", headerToAssert));
        wait.until(
                visibilityOfElementLocated(headerLocator)
        );

    }

    private static Stream<Arguments> open_sidebar_subtabs() {
        TestUtils.clearDriver(driver);
        driver = CHROME.get();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(CREDENTIALS_DATA.ADMIN_LOGIN());
        driver.findElement(By.name("password")).sendKeys(CREDENTIALS_DATA.ADMIN_PASSWORD());
        driver.findElement(By.name("login")).click();
        wait.until(
                visibilityOfElementLocated(By.xpath("//text()[contains(.,'You are now logged in as admin')]/.."))
        );

        return Stream.of(
                Arguments.of("Template",            List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Appearence')]]"))),
                Arguments.of("Template",            List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Appearence')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Template')]]"))),
                Arguments.of("Logotype",            List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Appearence')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Logotype')]]"))),

                Arguments.of("Catalog",             List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Catalog')]]"))),
                Arguments.of("Product Groups",      List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Product Groups')]]"))),
                Arguments.of("Option Groups",       List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Option Groups')]]"))),
                Arguments.of("Manufacturers",       List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Manufacturers')]]"))),
                Arguments.of("Suppliers",           List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Suppliers')]]"))),
                Arguments.of("Delivery Statuses",   List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Delivery Statuses')]]"))),
                Arguments.of("Sold Out Statuses",   List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Sold Out Statuses')]]"))),
                Arguments.of("Quantity Units",      List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'Quantity Units')]]"))),
                Arguments.of("CSV Import/Export",   List.of(By.xpath("//*[@id='app-']/a[*[contains(text(),'Catalog')]]"),By.xpath("//*[@class='docs']//a[*[contains(text(),'CSV Import/Export')]]")))
                );
    }


}
