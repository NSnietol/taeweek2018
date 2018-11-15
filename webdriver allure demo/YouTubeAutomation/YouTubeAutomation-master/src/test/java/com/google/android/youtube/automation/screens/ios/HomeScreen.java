package com.google.android.youtube.automation.screens.ios;

import com.google.android.youtube.automation.screens.widget.ResultsWidget;
import com.google.android.youtube.automation.screens.widget.ToolBarWidget;
import com.google.android.youtube.automation.util.annottation.Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Screen
public class HomeScreen extends com.google.android.youtube.automation.pageobject.Screen {

    private ToolBarWidget toolBarWidget;

    private List<ResultsWidget> listResultsWidget;

    /**
     * @param appiumDriver
     */
    @Autowired
    public HomeScreen(AppiumDriver<? extends MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    @Step("Tap on search")
    public SearchScreen tapOnSearch(){
        toolBarWidget.getSearchButton().click();
        return new SearchScreen(getDriver());
    }

    /**
     * @return <code>Boolean.TRUE</code> if the toolbar is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate toolbar")
    public Boolean isValidToolBar() {
        this.screenshot("Validate toolbar");
        return toolBarWidget.getLogo().isDisplayed()
                && toolBarWidget.getSearchButton().isDisplayed()
                && toolBarWidget.getVideoButton().isDisplayed()
                && toolBarWidget.getAvatarButton().isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the result is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate results")
    public Boolean isValidResults() {
        this.screenshot("Validate results");
        return listResultsWidget.stream()
                .findFirst()
                .map(resultsWidget -> resultsWidget.getImageVideo().isDisplayed()
                        && resultsWidget.getVideoTime().isDisplayed()
                        && resultsWidget.getGoChannelButton().isDisplayed()
                        && resultsWidget.getTitle().isDisplayed()
                        && resultsWidget.getDescription().isDisplayed()
                        && resultsWidget.getMenu().isDisplayed()
                        && resultsWidget.getAd().isDisplayed()
                        && resultsWidget.getChannelName().isDisplayed()
                        && resultsWidget.getSeeNow().isDisplayed())
                .orElse(Boolean.FALSE);
    }
}
