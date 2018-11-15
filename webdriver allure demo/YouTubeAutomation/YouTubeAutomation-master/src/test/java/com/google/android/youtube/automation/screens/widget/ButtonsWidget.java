package com.google.android.youtube.automation.screens.widget;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.WebElement;

import static com.google.android.youtube.automation.pageobject.Screen.ID;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@AndroidFindBy(xpath = "//android.widget.GridLayout/android.view.ViewGroup/android.view.ViewGroup")
public class ButtonsWidget extends Widget {

    @AndroidFindBy(id = ID + "button_icon")
    private MobileElement imageVideo;

    @AndroidFindBy(id = ID + "button_text")
    private MobileElement videoTime;

    protected ButtonsWidget(WebElement element) {
        super(element);
    }

    public MobileElement getImageVideo() {
        return imageVideo;
    }

    public MobileElement getVideoTime() {
        return videoTime;
    }
}
