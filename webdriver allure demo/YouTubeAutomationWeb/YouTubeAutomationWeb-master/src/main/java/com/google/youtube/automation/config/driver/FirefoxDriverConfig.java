package com.google.youtube.automation.config.driver;

import com.google.youtube.automation.util.annottation.ConfigFirefox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@ConfigFirefox
@PropertySource("classpath:config.properties")
public class FirefoxDriverConfig implements DriverConfig {

    @Autowired
    private Environment environment;

    public WebDriver getDriver() {
        System.setProperty(environment.getProperty("webdriver.firefox"),
                FileSystems.getDefault().getPath(environment.getProperty("webdriver.firefox.path")).toString());
        WebDriver driver = new FirefoxDriver();
        driver.get(environment.getProperty("pageURL"));
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
        return new EventFiringWebDriver(driver);
    }
}
