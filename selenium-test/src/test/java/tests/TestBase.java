package tests;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.function.Supplier;

import static com.seleniumtest.constants.TestUtils.clearDriver;

public class TestBase {

    public static WebDriver chrome;
    public static WebDriver firefox;
    public static WebDriver internetExplorer;

    @AfterAll
    static void afterAll(){
        clearDriver(chrome);
        clearDriver(firefox);
        clearDriver(internetExplorer);
    }

    public static Supplier<WebDriver> CHROME = () -> {
        if (chrome == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            chrome = new ChromeDriver(options);
            return chrome;
        } else {
            return chrome;
        }
    };

    public static Supplier<WebDriver> FF = () -> {
        if (firefox == null) {
            firefox = new FirefoxDriver();
            firefox.manage().window().maximize();
            return firefox;
        } else {
            return firefox;
        }
    };

    public static Supplier<WebDriver> IE = () -> {
        if (internetExplorer == null) {
            internetExplorer = new InternetExplorerDriver();
            internetExplorer.manage().window().maximize();
            return internetExplorer;
        } else {
            return internetExplorer;
        }
    };



}
