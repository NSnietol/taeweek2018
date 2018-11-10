package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import managers.FileReaderManager;

public class HomePageBlogspot  extends BasePage{
    public HomePageBlogspot(WebDriver driver){
        super(driver);
    }

    @FindBy(css="sign-in ga-header-sign-in")
    public WebElement signIn;


    public void goToLoginPage() {
        String url = FileReaderManager.getInstance().getConfig().urlBlogSpot();
        driver.get(url);
    }

    public void login(String user, String pass) {
    	//TODO:
    }

}
