package lesson5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.seleniumtest.constants.DriverFactory.clearDriver;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OpenItemTetst {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @AfterEach
    void afterEach() {
        clearDriver(driver);
    }


    @ParameterizedTest
    @MethodSource
    void open_item(Supplier<WebDriver> driverSupplier){

        driver = driverSupplier.get();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("http://localhost/litecart/en/");

        //main page item name
        String itemNameMainPage = driver.findElement(By.xpath("(//div[@id='box-campaigns']//a[@class='link'])[1]//*[@class='name']")).getText();
        //main page regular price
        String itemRegularPriceMainPage = driver.findElement(By.xpath("(//div[@id='box-campaigns']//a[@class='link'])[1]//*[@class='regular-price']")).getText();
        //main page discount price
        String itemDiscountPriceMainPage = driver.findElement(By.xpath("(//div[@id='box-campaigns']//a[@class='link'])[1]//*[@class='campaign-price']")).getText();

        //open item page
        driver.findElement(By.xpath("(//div[@id='box-campaigns']//a[@class='link'])[1]")).click();

        //item name
        String itemName = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        //item regular price
        String itemRegularPrice = driver.findElement(By.xpath("//*[@class='regular-price']")).getText();
        //item discount price
        String itemDiscountPrice = driver.findElement(By.xpath("//*[@class='campaign-price']")).getText();

        Assertions.assertAll(
                () -> assertThat(itemNameMainPage).isEqualTo(itemName)
                ,() -> assertThat(itemRegularPriceMainPage).isEqualTo(itemRegularPrice)
                ,() -> assertThat(itemDiscountPriceMainPage).isEqualTo(itemDiscountPrice)
        );
    }

    private static Stream<Arguments> open_item(){
        return Stream.of(
                Arguments.of((Supplier<WebDriver>) ChromeDriver::new),
                Arguments.of((Supplier<WebDriver>) FirefoxDriver::new),
                Arguments.of((Supplier<WebDriver>) OperaDriver::new)
        );
    }

}
