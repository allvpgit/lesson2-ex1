package tests.lesson2.exercise1;

import org.junit.jupiter.api.AfterEach;
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

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private static WebDriver driverToQuite;

    @AfterEach
    void afterEach(){
        driverToQuite.quit();
        driverToQuite = null;
    }

    @ParameterizedTest
    @MethodSource
    void find_webdriver_in_google(Supplier<WebDriver> driverSupplier){

        WebDriver driver = driverSupplier.get();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverToQuite = driver;

        driver.navigate().to("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    private static Stream<Arguments> find_webdriver_in_google(){

        return Stream.of(
                Arguments.of((Supplier<WebDriver>)ChromeDriver::new),
                Arguments.of((Supplier<WebDriver>)FirefoxDriver::new),
                Arguments.of((Supplier<WebDriver>)OperaDriver::new)
        );
    }


}
