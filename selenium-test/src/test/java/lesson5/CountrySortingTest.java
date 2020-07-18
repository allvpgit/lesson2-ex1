package lesson5;

import lesson2.TestBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.seleniumtest.constants.DriverFactory.clearDriver;
import static com.seleniumtest.constants.DriverFactory.getChrome;
import static com.seleniumtest.constants.constants.CredentialsData.CREDENTIALS_DATA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CountrySortingTest extends TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @AfterAll
    static void afterAll() {
        clearDriver(driver);
    }

    @BeforeAll
    static void beforeAll() {
        driver = getChrome();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(CREDENTIALS_DATA.ADMIN_LOGIN());
        driver.findElement(By.name("password")).sendKeys(CREDENTIALS_DATA.ADMIN_PASSWORD());
        driver.findElement(By.name("login")).click();
        wait.until(
                visibilityOfElementLocated(By.xpath("//text()[contains(.,'You are now logged in as admin')]/.."))
        );

    }

    @Test
    void countries_are_listed_in_alphabetic_order() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Countries')]")));

        List<String> countries = driver.findElements(By.xpath("//table/tbody/tr/td[5]"))
                .stream().map(WebElement::getText).collect(Collectors.toList());

        assertThat(countries).isSortedAccordingTo(String::compareTo);

    }

    @ParameterizedTest
    @MethodSource
    void zones_are_listed_in_alphabetic_order(By cellLocator) {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Countries')]")));

        driver.findElement(cellLocator).click();
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Edit Country')]")));

        List<String> zones = driver.findElements(By.xpath("//table//td[3][input/@type='hidden']"))
                .stream().map(WebElement::getText).collect(Collectors.toList());

        assertThat(zones).isSortedAccordingTo(String::compareTo);
    }

    private static Stream<Arguments> zones_are_listed_in_alphabetic_order() {
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Countries')]")));

        List<String> countries = driver.findElements(By.xpath("//table/tbody/tr[td[6]>0]/td[5]/a"))
                .stream().map(w -> w.getText()).collect(Collectors.toList());

        return countries.stream().map(country -> Arguments.of(By.xpath(String.format("//table//a[contains(text(),'%s')]", country))));
    }

    @ParameterizedTest
    @MethodSource
    void geozones_are_listed_in_alphabet_order(By cellLocator){
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Geo Zones')]")));

        driver.findElement(cellLocator).click();
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Edit Geo Zone')]")));

        List<String> zones = driver.findElements(By.xpath("//table//td[3]/select/option[@selected]"))
                .stream().map(WebElement::getText).collect(Collectors.toList());

        assertThat(zones).isSortedAccordingTo(String::compareTo);
    }

    private static Stream<Arguments> geozones_are_listed_in_alphabet_order() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Geo Zones')]")));

        List<String> countries = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"))
                .stream().map(w -> w.getText()).collect(Collectors.toList());

        return countries.stream().map(country -> Arguments.of(By.xpath(String.format("//table//a[contains(text(),'%s')]", country))));
    }
}
