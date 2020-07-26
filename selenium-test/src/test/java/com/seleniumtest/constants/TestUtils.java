package com.seleniumtest.constants;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

import java.math.BigDecimal;

public class TestUtils {

    public static void clearDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void testWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testWait(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isGrey(Color color) {
        return (color.getColor().getRed() == color.getColor().getGreen()) &&
                (color.getColor().getRed() == color.getColor().getBlue());
    }

    public static boolean isRed(Color color) {
        return (color.getColor().getRed() > 0) &&
                (color.getColor().getGreen() == 0) &&
                (color.getColor().getBlue() == 0);
    }

    public static BigDecimal getSize(String size){
        return new BigDecimal(size
                .replace("px", ""));
    }

}
