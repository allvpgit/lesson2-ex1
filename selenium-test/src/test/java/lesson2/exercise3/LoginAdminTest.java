package lesson2.exercise3;

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

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginAdminTest {

    private static WebDriver driverToQuite;

    @AfterEach
    void afterEach() {
        driverToQuite.quit();
        driverToQuite = null;
    }

    @ParameterizedTest
    @MethodSource
    void login_admin(Supplier<WebDriver> driverSupplier, String login, String password) {

        WebDriver driver = driverSupplier.get();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driverToQuite = driver;

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("login")).click();

        wait.until(
                visibilityOfElementLocated(By.xpath("//text()[contains(.,'You are now logged in as admin')]/.."))
        );
    }

    private static Stream<Arguments> login_admin() {

        return Stream.of(
                Arguments.of((Supplier<WebDriver>) ChromeDriver::new, "admin", "admin"),
                Arguments.of((Supplier<WebDriver>)FirefoxDriver::new, "admin", "admin"),
                Arguments.of((Supplier<WebDriver>)OperaDriver::new, "admin", "admin")
        );
    }

}
