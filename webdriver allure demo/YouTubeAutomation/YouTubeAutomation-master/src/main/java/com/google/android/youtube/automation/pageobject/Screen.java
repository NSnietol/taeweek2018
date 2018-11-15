package com.google.android.youtube.automation.pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
public abstract class Screen {

    public static final String ID = "com.google.android.youtube:id/";

    private final AppiumDriver<? extends MobileElement> appiumDriver;

    @Autowired
    private Environment environment;

    /**
     * @param appiumDriver
     */
    public Screen(AppiumDriver<? extends MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    /**
     * @return appiumDriver
     */
    protected AppiumDriver getDriver() {
        return appiumDriver;
    }

    /**
     * Use this method to simulate typing into an element, which may set its
     * value.
     *
     * @param element A <code>MobileElement</code> instance.
     * @param text    Character sequence to send to the element
     * @throws <code>TimeoutException</code> - If the timeout expires because the element is not visible
     *                                       or is disabled such that you cannot type on it.
     */
    protected void type(MobileElement element, String text) {
        element.clear();
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(text);
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
        return Boolean.parseBoolean(environment.getProperty("appium.enabled.screenshot")) ? getDriver().getScreenshotAs(OutputType.BYTES) : screen;
    }
}