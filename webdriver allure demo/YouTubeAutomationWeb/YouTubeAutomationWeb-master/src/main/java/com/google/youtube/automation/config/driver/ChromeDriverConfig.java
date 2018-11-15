package com.google.youtube.automation.config.driver;

import com.google.youtube.automation.util.annottation.ConfigChrome;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
@ConfigChrome
@PropertySource("classpath:config.properties")
public class ChromeDriverConfig implements DriverConfig {

    @Autowired
    private Environment environment;

    public WebDriver getDriver() {
        System.setProperty(environment.getProperty("webdriver.chrome"),
                FileSystems.getDefault().getPath(environment.getProperty("webdriver.chrome.path")).toString());
        WebDriver driver = new ChromeDriver();
        driver.get(environment.getProperty("pageURL"));
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.switchTo().frame(0);
        return new EventFiringWebDriver(driver);
    }
}
