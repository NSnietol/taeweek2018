package com.google.android.youtube.automation.util.annottation;

import io.appium.java_client.remote.MobilePlatform;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Lazy
@ActiveProfiles({MobilePlatform.IOS, MobilePlatform.ANDROID})
public @interface Screen {
}
