package com.google.youtube.automation.pageobject;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfNestedElementLocatedBy;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
public abstract class Screen {

    private WebDriver driver;

    @Autowired
    private Environment environment;

    /**
     * @param driver
     */
    public Screen(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * @return appiumDriver
     */
    protected WebDriver getDriver() {
        return driver;
    }


    /**
     * Checking that an element, known to be present on the screen, is visible.
     *
     * @param element A  <code>MobileElement</code> instance.
     * @return True if the element is visible otherwise return false.
     * @throws <code>TimeoutException</code> - If the timeout expires because the element is not visible.
     */
    protected Boolean nestedElementExists(WebElement element, By subLocator, int timeInSeconds) {
        try {
            new WebDriverWait(driver, timeInSeconds)
                    .until(presenceOfNestedElementLocatedBy(element, subLocator)).isDisplayed();
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Checking that an element, known to be present on the screen, is visible.
     *
     * @param element A  <code>MobileElement</code> instance.
     * @return True if the element is visible otherwise return false.
     * @throws <code>TimeoutException</code> - If the timeout expires because the element is not visible.
     */
    protected WebElement nestedElement(WebElement element, By subLocator, int timeInSeconds) {
        return new WebDriverWait(driver, timeInSeconds)
                .until(presenceOfNestedElementLocatedBy(element, subLocator));
    }

    /**
     * Take an Screenshot and attach them to a Allure report.
     *
     * @param value This value is use as placeholder value.
     * @return The screenshot (OutputType.BYTES)
     * @see <a href=
     * "https://github.com/allure-framework/allure-core/wiki/Steps#placeholders">Placeholders</a>
     */
    @Attachment(value = "Screenshot \"{0}\"", type = "image/png")
    public byte[] screenshot(String value) {
        byte[] screen = "Screenshots disabled".getBytes();
        return Boolean.parseBoolean(environment.getProperty("driver.enabled.screenshot")) ? ((TakesScreenshot) getDriver()).
                getScreenshotAs(OutputType.BYTES) : screen;
    }
}