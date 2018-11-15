package com.google.android.youtube.automation.config.driver;

import com.google.android.youtube.automation.util.annottation.ConfigAndroid;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.events.api.general.ElementEventListener;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.android.youtube.automation.appium.MobileCapabilityTypeDecorator.APP_ACTIVITY;
import static com.google.android.youtube.automation.appium.MobileCapabilityTypeDecorator.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@ConfigAndroid
@PropertySource("classpath:appium.properties")
public class AndroidDriverConfig implements AppiumDriverConfig, DesiredCapabilitiesConfig {

    @Autowired
    private Environment environment;

    public AppiumDriver<MobileElement> getAppiumDriver(ElementEventListener eventListener) throws MalformedURLException {
        return EventFiringWebDriverFactory
                .getEventFiringWebDriver(new AndroidDriver<MobileElement>(new URL(environment.getProperty("appium.server")), getDesiredCapabilities()), eventListener);
    }

    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, environment.getProperty("android.automationName"));
        capabilities.setCapability(PLATFORM_NAME, environment.getProperty("android.platform"));
            capabilities.setCapability(NO_RESET, environment.getProperty("appium.app.noReset"));
            capabilities.setCapability(FULL_RESET, environment.getProperty("appium.app.fullReset"));
            capabilities.setCapability(NEW_COMMAND_TIMEOUT, environment.getProperty("appium.new.command.timeout"));
            capabilities.setCapability(DEVICE_NAME, environment.getProperty("android.device.name"));
            capabilities.setCapability(APP_PACKAGE, environment.getProperty("android.app.package"));
            capabilities.setCapability(APP_ACTIVITY, environment.getProperty("android.app.activity"));

        return capabilities;
    }
}
