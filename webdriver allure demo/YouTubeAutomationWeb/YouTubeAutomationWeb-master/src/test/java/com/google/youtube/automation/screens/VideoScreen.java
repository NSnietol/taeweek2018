package com.google.youtube.automation.screens;

import com.google.youtube.automation.util.annottation.Screen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.format;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Screen
public class VideoScreen extends com.google.youtube.automation.pageobject.Screen {

    @FindBy(id = "owner-name")
    private WebElement channelName;

    @FindBy(xpath = "//yt-formatted-string[contains(text(), 'SAVE')]")
    private WebElement saveButton;

    @FindBy(xpath = "//ytd-toggle-button-renderer[1]/a/yt-formatted-string[@id='text']")
    private WebElement likeButton;

    @FindBy(xpath = "//ytd-modal-with-title-and-button-renderer")
    private WebElement modal;

    /**
     * @param driver
     */
    @Autowired
    public VideoScreen(WebDriver driver) {
        super(driver);
    }

    @Step("Tap on search")
    public VideoScreen tapOnSave(){
        saveButton.click();
        return this;
    }

    @Step("Tap on like")
    public VideoScreen tapOnLike(){
        likeButton.click();
        return this;
    }

    @Step("Validate video")
    public Boolean isVideoDisplayed(String channelName) {
        return getDriver().findElement(By.xpath(format("//a[text()='%s']", channelName))).isDisplayed();
    }
}
