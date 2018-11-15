package com.google.youtube.automation.config;

import com.google.youtube.automation.config.driver.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.google.youtube.automation.*")
public class ConfigApplication {

    @Autowired
    private DriverConfig driver;

    @Bean(destroyMethod = "quit")
    @Qualifier("driver")
    public WebDriver driver() {
        return driver.getDriver();
    }
}
