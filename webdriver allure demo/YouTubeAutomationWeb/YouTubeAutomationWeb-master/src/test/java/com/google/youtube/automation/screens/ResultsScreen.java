package com.google.youtube.automation.screens;

import com.google.youtube.automation.util.annottation.Screen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Screen
public class ResultsScreen extends com.google.youtube.automation.pageobject.Screen {

    @FindBy(xpath = "//ytd-video-renderer")
    private List<WebElement> videos;

    /**
     * @param driver
     */
    @Autowired
    public ResultsScreen(WebDriver driver) {
        super(driver);
    }

    @Step("Validate videos")
    public VideoScreen selectVideo(String videoName) {
        videos.stream()
                .filter(element -> nestedElement(element, By.linkText(videoName), 30).getText().equals(videoName))
                .findFirst()
                .ifPresent(WebElement::click);
        return new VideoScreen(getDriver());

    }

    @Step("Validate videos")
    public Boolean isVideosDisplayed() {
        return videos.stream()
                .findFirst()
                .map(element -> nestedElementExists(element, By.id("title-wrapper"), 10)
                        && nestedElementExists(element, By.xpath("//yt-formatted-string/a"), 10))
                .orElse(Boolean.FALSE);
    }
}
