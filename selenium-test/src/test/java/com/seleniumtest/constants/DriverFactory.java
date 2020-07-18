package com.seleniumtest.constants;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    public static WebDriver getFirefox() {
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().fullscreen();
        return driver;
    }

    public static void clearDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void testWait(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testWait(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
