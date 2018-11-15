package com.google.android.youtube.automation.test;

import com.google.android.youtube.automation.config.ConfigApplication;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@ContextConfiguration(classes = {ConfigApplication.class},
        loader = AnnotationConfigContextLoader.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AppiumDriver<? extends MobileElement> appiumDriver;

    @BeforeSuite(alwaysRun = true)
    public void setUpbeforeSuite() {
        getAppiumDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAfterSuite() {
        appiumDriver.resetApp();
    }

    protected AppiumDriver<? extends MobileElement> getAppiumDriver() {
        return appiumDriver;
    }
}