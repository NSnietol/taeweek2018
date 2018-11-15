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
@AndroidFindBy(id = ID + "promoted_video")
public class ResultsWidget extends Widget {

    @AndroidFindBy(id = ID + "thumbnail")
    private MobileElement imageVideo;

    @AndroidFindBy(xpath = ".//*[contains(@text,':')]")
    private MobileElement videoTime;

    @AndroidFindBy(accessibility = "Ir al canal")
    private MobileElement goChannelButton;

    @AndroidFindBy(id = ID + "title")
    private MobileElement title;

    @AndroidFindBy(id = ID + "description")
    private MobileElement description;

    @AndroidFindBy(accessibility = "Menú")
    private MobileElement menu;

    @AndroidFindBy(id = ID + "ad_attribution")
    private MobileElement ad;

    @AndroidFindBy(id = ID + "channel_name")
    private MobileElement channelName;

    @AndroidFindBy(id = ID + "call_to_action")
    private MobileElement seeNow;

    protected ResultsWidget(WebElement element) {
        super(element);
    }

    public MobileElement getImageVideo() {
        return imageVideo;
    }

    public MobileElement getVideoTime() {
        return videoTime;
    }

    public MobileElement getGoChannelButton() {
        return goChannelButton;
    }

    public MobileElement getTitle() {
        return title;
    }

    public MobileElement getDescription() {
        return description;
    }

    public MobileElement getMenu() {
        return menu;
    }

    public MobileElement getAd() {
        return ad;
    }

    public MobileElement getChannelName() {
        return channelName;
    }

    public MobileElement getSeeNow() {
        return seeNow;
    }
}
