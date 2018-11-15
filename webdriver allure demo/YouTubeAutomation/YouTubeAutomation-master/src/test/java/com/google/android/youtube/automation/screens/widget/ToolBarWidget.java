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
@AndroidFindBy(id = ID + "toolbar")
public class ToolBarWidget extends Widget {

    @AndroidFindBy(id = ID + "youtube_logo")
    private MobileElement logo;

    @AndroidFindBy(accessibility = "Video")
    private MobileElement videoButton;

    @AndroidFindBy(accessibility = "Buscar")
    private MobileElement searchButton;

    @AndroidFindBy(id = ID + "mobile_topbar_avatar")
    private MobileElement avatarButton;

    protected ToolBarWidget(WebElement element) {
        super(element);
    }

    public MobileElement getLogo() {
        return logo;
    }

    public MobileElement getVideoButton() {
        return videoButton;
    }

    public MobileElement getSearchButton() {
        return searchButton;
    }

    public MobileElement getAvatarButton() {
        return avatarButton;
    }
}
