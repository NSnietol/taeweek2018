package com.google.android.youtube.automation.screens.ios;

import com.google.android.youtube.automation.screens.widget.SearchListVideoInformationWidget;
import com.google.android.youtube.automation.screens.widget.SearchListWidget;
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
public class SearchScreen extends com.google.android.youtube.automation.pageobject.Screen {

    @AndroidFindBy(accessibility = "Navegar hacia arriba")
    private MobileElement navigationBackButton;

    @AndroidFindBy(id = ID + "search_edit_text")
    private MobileElement searchBox;

    @AndroidFindBy(accessibility = "Búsqueda por voz")
    private MobileElement voiceSearchButton;

    @AndroidFindBy(accessibility = "Borrar")
    private MobileElement clearButton;

    @AndroidFindBy(accessibility = "Filtrar")
    private MobileElement filterButton;

    @WithTimeout(time = 30, unit = TimeUnit.SECONDS)
    private List<SearchListWidget> searchListWidgets;

    @WithTimeout(time = 60, unit = TimeUnit.SECONDS)
    private List<SearchListVideoInformationWidget> searchListVideoInformationWidgets;

    /**
     * @param appiumDriver
     */
    @Autowired
    public SearchScreen(AppiumDriver<? extends MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    @Step("Type video name")
    public SearchScreen typeVideoName(String videoName) {
        type(searchBox, videoName);
        return this;
    }

    @Step("Select result")
    public SearchScreen selectSearchResult(String NameVideo) {
        searchListWidgets.stream()
                .filter(searchListWidget -> searchListWidget.getTextLastSearch().getText().equals(NameVideo.toLowerCase()))
                .findFirst()
                .ifPresent(searchListWidget -> searchListWidget.getWrappedElement().click());
        return this;
    }

    @Step("Select result")
    public VideoScreen selectSearchResultVideo(String nameChannel) {
        searchListVideoInformationWidgets.stream()
                .filter(widget -> widget.getAuthor().getText().equals(nameChannel))
                .findFirst()
                .ifPresent(widget -> widget.getWrappedElement().click());
        return new VideoScreen(getDriver());
    }

    /**
     * @return <code>Boolean.TRUE</code> if the search result is valid;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate results")
    public Boolean isValidSearchResult() {
        return searchListWidgets.stream()
                .findFirst()
                .map(searchListWidget -> searchListWidget.getSearchTypeIcon().isDisplayed()
                        && searchListWidget.getTextLastSearch().isDisplayed()
                        && searchListWidget.getEditSuggestionButton().isDisplayed())
                .orElse(Boolean.FALSE);
    }

    /**
     * @return <code>Boolean.TRUE</code> if the search result video is valid;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate results")
    public Boolean isValidSearchResultVideo(String nameChannel) {
        return searchListVideoInformationWidgets.stream()
                .findFirst()
                .map(widget -> widget.getAuthor().getText().equals(nameChannel))
                .orElse(Boolean.FALSE);
    }
}
