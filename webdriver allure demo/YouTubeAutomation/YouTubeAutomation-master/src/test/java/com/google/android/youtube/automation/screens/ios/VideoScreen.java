package com.google.android.youtube.automation.screens.ios;

import com.google.android.youtube.automation.screens.widget.ButtonsWidget;
import com.google.android.youtube.automation.util.annottation.Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Screen
public class VideoScreen extends com.google.android.youtube.automation.pageobject.Screen {

    @AndroidFindBy(id = ID + "title")
    private MobileElement title;

    @AndroidFindBy(accessibility = "Botón de More")
    private MobileElement moreButton;

    @AndroidFindBy(id = ID + "subtitle")
    private MobileElement subTitle;

    @AndroidFindBy(id = ID + "channel_owner_avatar")
    private MobileElement channelAvatar;

    @AndroidFindBy(id = ID + "channel_title")
    private MobileElement channelTitle;

    @AndroidFindBy(id = ID + "channel_subscribers")
    private MobileElement channelSubscribers;

    @AndroidFindBy(id = ID + "subscribe_button")
    private MobileElement subscribeButton;

    @AndroidFindBy(id = ID + "notification_toggle_view")
    @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
    private MobileElement notificationButton;

    private List<ButtonsWidget> buttonsWidgets;

    /**
     * @param appiumDriver
     */
    @Autowired
    public VideoScreen(AppiumDriver<? extends MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    @Step("Tap on search")
    public VideoScreen tapOnSubscribe(){
        subscribeButton.click();
        return this;
    }

    /**
     * @return <code>Boolean.TRUE</code> if the buttons like and dislike is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate buttons like and dislike displayes")
    public Boolean isButtonsLikesDisplayed() {
        return buttonsWidgets.stream()
                .findFirst()
                .map(buttonsWidget -> buttonsWidget.getWrappedElement().isDisplayed())
                .orElse(Boolean.FALSE);
    }

    /**
     * @return <code>Boolean.TRUE</code> if the title is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate tittle displayed")
    public Boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the more button is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate more button displayed")
    public Boolean isMoreButtonDisplayed() {
        return moreButton.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the subTitle is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate more button displayed")
    public Boolean isSubTitleDisplayed() {
        return subTitle.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the channel avatar is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate channel avatar displayed")
    public Boolean isChannelAvatarDisplayed() {
        return channelAvatar.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the channel title is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate channel title displayed")
    public Boolean isChannelTitleDisplayed() {
        return channelTitle.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the channel title is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate channel suscribers displayed")
    public Boolean isChannelSubscribersDisplayed() {
        return channelSubscribers.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the channel title is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate channel suscribers displayed")
    public Boolean isSubscribeButtonDisplayed() {
        return subscribeButton.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the notification button title is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate notification button displayed")
    public Boolean isNotificationButtonDisplayed() {
        return notificationButton.isDisplayed();
    }
}
