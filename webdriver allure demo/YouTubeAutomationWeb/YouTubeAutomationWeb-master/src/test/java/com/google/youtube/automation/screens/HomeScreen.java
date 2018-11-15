package com.google.youtube.automation.screens;

import com.google.youtube.automation.util.annottation.Screen;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Screen
public class HomeScreen extends com.google.youtube.automation.pageobject.Screen {

    @FindBy(id = "logo")
    private WebElement logo;

    @FindBy(id = "search")
    private WebElement searchBox;

    @FindBy(id = "yt-masthead-signin")
    private WebElement loginButton;

    @FindBy(id = "Email")
    private WebElement boxEmail;

    @FindBy(name = "Passwd")
    private WebElement boxPass;

    @FindBy(id = "signIn")
    private WebElement signInButton;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "search-icon-legacy")
    private WebElement searchButton;

    /**
     * @param driver
     */
    @Autowired
    public HomeScreen(WebDriver driver) {
        super(driver);
    }

    @Step("Tap on search")
    public ResultsScreen tapOnSearch(){
        searchButton.click();
        return new ResultsScreen(getDriver());
    }

    @Step("Type on search")
    public HomeScreen searchByText(String text){
        searchBox.sendKeys(text);
        return this;
    }

    /**
     * @return <code>Boolean.TRUE</code> if the logo is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate logo")
    public Boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    /**
     * @return <code>Boolean.TRUE</code> if the search box is displayed;
     * <code>Boolean.FALSE</code> otherwise
     */
    @Step("Validate search box")
    public Boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }
/*
    *//**
     * @return <code>Boolean.TRUE</code> if the toolbar is displayed;
     * <code>Boolean.FALSE</code> otherwise
     *//*
    @Step("Validate toolbar")
    public Boolean isValidToolBar() {
        this.screenshot("Validate toolbar");
        return toolBarWidget.getLogo().isDisplayed()
                && toolBarWidget.getSearchButton().isDisplayed()
                && toolBarWidget.getVideoButton().isDisplayed()
                && toolBarWidget.getAvatarButton().isDisplayed();
    }

    *//**
     * @return <code>Boolean.TRUE</code> if the result is displayed;
     * <code>Boolean.FALSE</code> otherwise
     *//*
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
    }*/
}
