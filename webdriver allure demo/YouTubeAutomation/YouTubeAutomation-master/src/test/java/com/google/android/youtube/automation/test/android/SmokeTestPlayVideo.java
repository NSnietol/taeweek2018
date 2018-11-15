package com.google.android.youtube.automation.test.android;

import com.google.android.youtube.automation.screens.ios.HomeScreen;
import com.google.android.youtube.automation.screens.ios.SearchScreen;
import com.google.android.youtube.automation.screens.ios.VideoScreen;
import com.google.android.youtube.automation.test.BaseTest;
import io.appium.java_client.remote.MobilePlatform;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Epic("PLAY VIDEO")
@Feature("SMOKE TEST PLAY VIDEO IOS")
@ActiveProfiles(MobilePlatform.ANDROID)
public class SmokeTestPlayVideo extends BaseTest {

    @Autowired
    private HomeScreen homeScreen;

    @Autowired
    private SearchScreen searchScreen;

    @Autowired
    private VideoScreen videoScreen;

    private String videoName;

    private String channelName;

    @BeforeClass
    @Parameters({"videoName", "channelName"})
    public void setUpData(@Optional("videoName") String videoName, @Optional("channelName") String channelName) {
        this.videoName = videoName;
        this.channelName = channelName;
    }

    @Test(priority = 1)
    @Story("Home - Validate ToolBar")
    @Severity(SeverityLevel.NORMAL)
    public void testValidateHome() {

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(homeScreen.isValidToolBar()).as("ToolBar displayed").isTrue();
            softly.assertThat(homeScreen.isValidResults()).as("Results displayed").isTrue();
        });
    }

    @Test(priority = 2)
    @Story("Search - Validate Search Results")
    @Severity(SeverityLevel.BLOCKER)
    public void testSearchVideo() {
        searchScreen = homeScreen.tapOnSearch().typeVideoName(videoName);

        Assertions.assertThat(searchScreen.isValidSearchResult()).as("Search results displayed").isTrue();
    }

    @Test(priority = 3)
    @Story("Search - Validate Search Select Video")
    @Severity(SeverityLevel.NORMAL)
    public void testValidateSearchResultVideo() {
        searchScreen.selectSearchResult(videoName);

        Assertions.assertThat(searchScreen.isValidSearchResultVideo(channelName)).as("Search results video displayed").isTrue();
    }

    @Test(priority = 4)
    @Story("Video - Video Selected")
    @Severity(SeverityLevel.MINOR)
    public void testValidateVideoSelected() {
        videoScreen = searchScreen.selectSearchResultVideo(channelName);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(videoScreen.isTitleDisplayed()).as("Title displayed").isTrue();
            softly.assertThat(videoScreen.isMoreButtonDisplayed()).as("Button more displayed").isTrue();
            softly.assertThat(videoScreen.isSubTitleDisplayed()).as("Subtitle more displayed").isTrue();
            softly.assertThat(videoScreen.isButtonsLikesDisplayed()).as("Search results video displayed").isTrue();
            softly.assertThat(videoScreen.isChannelAvatarDisplayed()).as("Channel avatar displayed").isTrue();
            softly.assertThat(videoScreen.isChannelTitleDisplayed()).as("Channel title displayed").isTrue();
            softly.assertThat(videoScreen.isChannelSubscribersDisplayed()).as("Channel subscribers displayed").isTrue();
            softly.assertThat(videoScreen.isSubscribeButtonDisplayed()).as("Subscribers button displayed").isTrue();
        });
    }

    @Test(priority = 5)
    @Story("Video - Video - Subscribe To This Channel Video Selected")
    @Severity(SeverityLevel.MINOR)
    public void testSubscribeToThisChannelVideoSelected() {
        videoScreen.tapOnSubscribe();

        Assertions.assertThat(videoScreen.isNotificationButtonDisplayed()).as("Title displayed").isTrue();
    }
}
