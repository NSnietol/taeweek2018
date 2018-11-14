package pageObject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import managers.PageGenerator;
import utility.Log;

/**
 * Base abstract class for classes that will use the pattern page object
 * @author Paul Andrés Arenas Cardona <paul.arenas@globant.com>
 * @version 1.0
 * @since 1.0
 */
public class BasePage {

    private WebDriver driver;
    public PageGenerator page;

    /**
     * Constructor that is responsible for initializing the PageObject
     * @param driver Object with the instance of the WebDriver
     */
    public BasePage(WebDriver driver){
        this.driver = driver;
        page = new PageGenerator ();
    }

    /**
     * Click Method by using JAVA Generics (You can use both By or Webelement)
     * @param elementAttr an take the value of WeElement or By
     */
    public <T> void click (T elementAttr) {
        WebElement element = null;
        try {
            if (elementAttr.getClass().getName().contains("By")) {
                element = driver.findElement((By) elementAttr);
            } else {
                element = (WebElement) elementAttr;
            }
            element.click();
        }catch(NoSuchElementException nee){
            Log.error("The item was not found " + nee.getMessage());
        }catch(WebDriverException we){
            Actions actions = new Actions(driver);
            if(element != null) {
                actions.moveToElement(element).click().perform();
            }else {
                Log.error("There was an error when clicking on the element " +
                		elementAttr.getClass().getName());
            }
        }
    }

    /**
     * Clear Text by using JAVA Generics (You can use both By or Webelement)
     *
     * @param elementAttr Can take the value of WeElement or By
     */
    public <T> void clearText (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).clear();
        } else {
            ((WebElement) elementAttr).clear();
        }
    }

    /**
     * Write Text by using JAVA Generics (You can use both By or Webelement)
     *
     * @param elementAttr Can take the value of WeElement or By
     * @param text The text that will be written
     */
    public <T> void writeText (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

}
