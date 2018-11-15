package com.google.android.youtube.automation.config.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.events.api.general.ElementEventListener;

import java.net.MalformedURLException;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
public interface AppiumDriverConfig {

    AppiumDriver<MobileElement> getAppiumDriver(ElementEventListener eventListener) throws MalformedURLException;

}
