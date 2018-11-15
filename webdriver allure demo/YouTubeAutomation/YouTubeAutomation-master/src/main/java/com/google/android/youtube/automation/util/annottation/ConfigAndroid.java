package com.google.android.youtube.automation.util.annottation;

import io.appium.java_client.remote.MobilePlatform;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Component
@Profile(MobilePlatform.ANDROID)
public @interface ConfigAndroid {
}
