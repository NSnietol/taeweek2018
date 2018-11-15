package com.google.android.youtube.automation.config;

import com.google.android.youtube.automation.config.driver.AppiumDriverConfig;
import com.google.android.youtube.automation.listener.AppListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.google.android.youtube.automation.*")
public class ConfigApplication {

    @Autowired
    private AppiumDriverConfig appiumDriverConfig;

    @Autowired
    private AppListener appListener;

    @Bean(destroyMethod = "quit")
    @Qualifier("driver")
    public AppiumDriver<MobileElement> appiumDriver() throws IOException {
        return appiumDriverConfig.getAppiumDriver(appListener);
    }
}
