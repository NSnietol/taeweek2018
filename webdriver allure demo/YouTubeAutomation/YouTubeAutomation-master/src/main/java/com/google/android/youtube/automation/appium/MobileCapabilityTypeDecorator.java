package com.google.android.youtube.automation.appium;

import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
public interface MobileCapabilityTypeDecorator extends MobileCapabilityType {
    String XCODE_ORG_ID = "xcodeOrgId";
    String BUNDLE_ID = "bundleId";
    String XCODE_SIGNIN_ID = "xcodeSigningId";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
}
