package com.google.android.youtube.automation.config.driver;

import com.google.android.youtube.automation.util.annottation.ConfigIOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import io.appium.java_client.events.api.general.ElementEventListener;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.android.youtube.automation.appium.MobileCapabilityTypeDecorator.BUNDLE_ID;
import static com.google.android.youtube.automation.appium.MobileCapabilityTypeDecorator.XCODE_ORG_ID;
import static io.appium.java_client.remote.MobileCapabilityType.*;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@ConfigIOS
@PropertySource("classpath:appium.properties")
public class IOSDriverConfig implements AppiumDriverConfig, DesiredCapabilitiesConfig {

    @Autowired
    private Environment environment;

    public AppiumDriver<MobileElement> getAppiumDriver(ElementEventListener eventListener) throws MalformedURLException {
        return EventFiringWebDriverFactory.getEventFiringWebDriver(new IOSDriver<MobileElement>(new URL(environment.getProperty("appium.server")), getDesiredCapabilities()), eventListener);
    }

    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, environment.getProperty("ios.automationName"));
        capabilities.setCapability(PLATFORM_NAME, environment.getProperty("ios.platform"));

        if (Boolean.valueOf(environment.getProperty("local.run.enviroment"))) {
            capabilities.setCapability(DEVICE_NAME, environment.getProperty("ios.device.name"));
            capabilities.setCapability(UDID, environment.getProperty("ios.device.udid"));
            capabilities.setCapability(APP, environment.getProperty("ios.app"));
            capabilities.setCapability(PLATFORM_VERSION, environment.getProperty("ios.platform.version"));
            capabilities.setCapability(BUNDLE_ID, environment.getProperty("ios.app.bundleid"));
            capabilities.setCapability(XCODE_ORG_ID, environment.getProperty("ios.app.xcodeOrgId"));
            capabilities.setCapability(NO_RESET, environment.getProperty("appium.app.noReset"));
            capabilities.setCapability(FULL_RESET, environment.getProperty("appium.app.fullReset"));
            capabilities.setCapability(CLEAR_SYSTEM_FILES, environment.getProperty("appium.clearSystemFiles"));
            capabilities.setCapability(NEW_COMMAND_TIMEOUT, environment.getProperty("appium.new.command.timeout"));
        }
        return capabilities;
    }
}
