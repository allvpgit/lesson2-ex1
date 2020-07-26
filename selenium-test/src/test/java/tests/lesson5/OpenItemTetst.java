package tests.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import tests.TestBase;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.seleniumtest.constants.TestUtils.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class OpenItemTetst extends TestBase {

    @DisplayName("Title, regular and discount prices are equal on main and item pages")
    @ParameterizedTest
    @MethodSource
    void title_and_prices_equals(Supplier<WebDriver> driverSupplier){

        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");

        //main page item name
        String itemNameMainPage = driver.findElement(By.cssSelector("#box-campaigns .product .name")).getText();
        String regularPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price")).getText();
        String discountPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price")).getText();

        //open item page
        driver.findElement(By.cssSelector("#box-campaigns .product a.link")).click();

        String itemName = driver.findElement(By.cssSelector("#box-product h1.title")).getText();
        String itemRegularPrice = driver.findElement(By.cssSelector("#box-product .regular-price")).getText();
        String itemDiscountPrice = driver.findElement(By.cssSelector("#box-product .campaign-price")).getText();

        Assertions.assertAll(
                () -> assertThat(itemNameMainPage).isEqualTo(itemName)
                ,() -> assertThat(regularPriceMainPage).isEqualTo(itemRegularPrice)
                ,() -> assertThat(discountPriceMainPage).isEqualTo(itemDiscountPrice)
        );
    }

    private static Stream<Arguments> title_and_prices_equals(){
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Regular price is grey and striked on main page")
    @ParameterizedTest
    @MethodSource
    void main_page_regular_price_style(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");

        WebElement regularPrice = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price"));
        Color regularPriceColor = Color.fromString(regularPrice.getCssValue("color"));
        String regularPriceDecoration = regularPrice.getCssValue("text-decoration");

        Assertions.assertAll(
                () -> assertThat(isGrey(regularPriceColor)).isTrue()
                , () -> assertThat(regularPriceDecoration).contains("line-through")
        );
    }

    private static Stream<Arguments> main_page_regular_price_style() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Regular price is grey and striked on item page")
    @ParameterizedTest
    @MethodSource
    void item_page_regular_price_style(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-campaigns .product a.link")).click();

        WebElement regularPrice = driver.findElement(By.cssSelector("#box-product .regular-price"));
        Color regularPriceColor = Color.fromString(regularPrice.getCssValue("color"));
        String regularPriceDecoration = regularPrice.getCssValue("text-decoration");

        Assertions.assertAll(
                () -> assertThat(isGrey(regularPriceColor)).isTrue()
                , () -> assertThat(regularPriceDecoration).contains("line-through")
        );
    }

    private static Stream<Arguments> item_page_regular_price_style() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Discount price is red and bold on main page")
    @ParameterizedTest
    @MethodSource
    void main_page_discount_price_style(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");

        WebElement discountPrice = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price"));
        Color discountPriceColor = Color.fromString(discountPrice.getCssValue("color"));
        Integer discountPriceWeight = Integer.parseInt(discountPrice.getCssValue("font-weight"));

        Assertions.assertAll(
                () -> assertThat(isRed(discountPriceColor)).isTrue()
                , () -> assertThat(discountPriceWeight).isGreaterThan(600)
        );
    }

    private static Stream<Arguments> main_page_discount_price_style() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Discount price is red and bold on item page")
    @ParameterizedTest
    @MethodSource
    void item_page_discount_price_style(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-campaigns .product a.link")).click();

        WebElement discountPrice = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        Color discountPriceColor = Color.fromString(discountPrice.getCssValue("color"));
        Integer discountPriceWeight = Integer.parseInt(discountPrice.getCssValue("font-weight"));

        Assertions.assertAll(
                () -> assertThat(isRed(discountPriceColor)).isTrue()
                , () -> assertThat(discountPriceWeight).isGreaterThan(600)
        );
    }

    private static Stream<Arguments> item_page_discount_price_style() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Discount price font size is greater than regular one on Main Page")
    @ParameterizedTest
    @MethodSource
    void discount_price_size_is_greater_than_regular_one_Main_Page(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");

        WebElement regularPrice = driver.findElement(By.cssSelector("#box-campaigns .product .regular-price"));
        BigDecimal regularSize = getSize(regularPrice.getCssValue("font-size"));

        WebElement discountPrice = driver.findElement(By.cssSelector("#box-campaigns .product .campaign-price"));
        BigDecimal discountSize = getSize(discountPrice.getCssValue("font-size"));

        Assertions.assertAll(
                () -> assertThat(discountSize).isGreaterThan(regularSize)
        );

    }

    private static Stream<Arguments> discount_price_size_is_greater_than_regular_one_Main_Page() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }

    @DisplayName("Discount price font size is greater than regular one on Item Page")
    @ParameterizedTest
    @MethodSource
    void discount_price_size_is_greater_than_regular_one_Item_Page(Supplier<WebDriver> driverSupplier) {
        WebDriver driver = driverSupplier.get();

        driver.navigate().to("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("#box-campaigns .product a.link")).click();

        WebElement regularPrice = driver.findElement(By.cssSelector("#box-product .regular-price"));
        BigDecimal regularSize = getSize(regularPrice.getCssValue("font-size"));

        WebElement discountPrice = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        BigDecimal discountSize = getSize(discountPrice.getCssValue("font-size"));

        Assertions.assertAll(
                () -> assertThat(discountSize).isGreaterThan(regularSize)
        );

    }

    private static Stream<Arguments> discount_price_size_is_greater_than_regular_one_Item_Page() {
        return Stream.of(
                Arguments.of(FF),
                Arguments.of(CHROME),
                Arguments.of(IE)
        );
    }


}
