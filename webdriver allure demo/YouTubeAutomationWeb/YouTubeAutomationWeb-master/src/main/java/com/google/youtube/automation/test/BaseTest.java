package com.google.youtube.automation.test;

import com.google.youtube.automation.config.ConfigApplication;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:config.properties")
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private Environment environment;

    @Autowired
    private WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUpbeforeSuite() {
        this.getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownAfterSuite() {
        //driver.close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}