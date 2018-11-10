package pages;

import managers.FileReaderManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageFacebook  extends BasePage{
    public HomePageFacebook(WebDriver driver){
        super(driver);
    }

    @FindBy(id="email")
    public WebElement emailInputText;

    @FindBy(id="pass")
    public WebElement passInputText;

    @FindBy(css = "input[data-testid='royal_login_button']")
    public WebElement submitButton;

    public String messageName = "xhpc_message";
    public String postButton = "button[data-testid='react-composer-post-button']";

    public void goToLoginPage() {
        String url = FileReaderManager.getInstance().getConfig().urlFacebook();
        driver.get(url);
    }

    public void login(String user, String pass) {
        writeText(emailInputText, user);
        writeText(passInputText, pass);
        click(submitButton);
    }

	public void post(String msg) {
		driver.findElement(By.name(messageName)).sendKeys(msg);
		driver.findElement(By.cssSelector(postButton)).click();
	}
}
