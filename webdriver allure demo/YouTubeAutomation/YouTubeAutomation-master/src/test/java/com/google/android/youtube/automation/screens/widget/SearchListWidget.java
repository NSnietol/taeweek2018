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
@AndroidFindBy(xpath = "//android.widget.ListView/android.widget.LinearLayout")
public class SearchListWidget extends Widget {

    @AndroidFindBy(id = ID + "search_type_icon")
    private MobileElement searchTypeIcon;

    @AndroidFindBy(id = ID + "text")
    private MobileElement textLastSearch;

    @AndroidFindBy(id = ID + "edit_suggestion")
    private MobileElement editSuggestionButton;

    protected SearchListWidget(WebElement element) {
        super(element);
    }

    public MobileElement getSearchTypeIcon() {
        return searchTypeIcon;
    }

    public MobileElement getTextLastSearch() {
        return textLastSearch;
    }

    public MobileElement getEditSuggestionButton() {
        return editSuggestionButton;
    }
}
