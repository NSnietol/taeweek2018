package com.google.youtube.automation.test;

import com.google.youtube.automation.screens.ResultsScreen;
import com.google.youtube.automation.screens.VideoScreen;
import com.google.youtube.automation.test.BaseTest;
import com.google.youtube.automation.screens.HomeScreen;
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
@ActiveProfiles("Firefox")
public class SmokeTestPlayVideo extends BaseTest {

    @Autowired
    private HomeScreen homeScreen;

    @Autowired
    private ResultsScreen resultsScreen;

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
            softly.assertThat(homeScreen.isLogoDisplayed()).as("Logo displayed").isTrue();
            softly.assertThat(homeScreen.isSearchBoxDisplayed()).as("Search box displayed").isTrue();
        });
    }

    @Test(priority = 2)
    @Story("Search - Validate Search Results")
    @Severity(SeverityLevel.BLOCKER)
    public void testSearchVideo() {
        resultsScreen = homeScreen.searchByText(videoName).tapOnSearch();

        Assertions.assertThat(resultsScreen.isVideosDisplayed()).as("Search results displayed").isTrue();
    }


    @Test(priority = 3)
    @Story("Search - Video Selected")
    @Severity(SeverityLevel.BLOCKER)
    public void testSelectVideo() {
        videoScreen = resultsScreen.selectVideo(videoName);

        Assertions.assertThat(videoScreen.isVideoDisplayed(channelName)).as("Video playing displayed").isTrue();
    }

    @Test(priority = 4)
    @Story("Search - Video Save")
    @Severity(SeverityLevel.BLOCKER)
    public void testSaveVideo() {
        videoScreen.tapOnLike();

        //Assertions.assertThat(videoScreen.isVideoDisplayed(channelName)).as("Video playing displayed").isTrue();
    }
}
