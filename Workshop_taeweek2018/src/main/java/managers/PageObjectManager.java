package managers;

import org.openqa.selenium.WebDriver;
import pages.HomePageFacebook;

public class PageObjectManager {

    private WebDriver driver;
    private HomePageFacebook homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageFacebook getHomePage() {
        return (homePage == null) ? homePage = new HomePageFacebook(driver) : homePage;
    }
}