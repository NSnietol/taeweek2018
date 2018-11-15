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
@AndroidFindBy(id = ID + "video_info_view")
public class SearchListVideoInformationWidget extends Widget {

    @AndroidFindBy(id = ID + "thumbnail")
    private MobileElement imageVideo;

    @AndroidFindBy(xpath = ".//*[contains(@text,':')]")
    private MobileElement videoTime;

    @AndroidFindBy(id = ID + "title")
    private MobileElement title;

    @AndroidFindBy(id = ID + "contextual_menu_anchor")
    private MobileElement menu;

    @AndroidFindBy(id = ID + "author")
    private MobileElement author;

    @AndroidFindBy(id = ID + "details")
    private MobileElement details;

    protected SearchListVideoInformationWidget(WebElement element) {
        super(element);
    }

    public MobileElement getImageVideo() {
        return imageVideo;
    }

    public MobileElement getVideoTime() {
        return videoTime;
    }

    public MobileElement getTitle() {
        return title;
    }

    public MobileElement getMenu() {
        return menu;
    }

    public MobileElement getAuthor() {
        return author;
    }

    public MobileElement getDetails() {
        return details;
    }
}
