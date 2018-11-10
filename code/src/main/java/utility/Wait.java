package utility;

import managers.FileReaderManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Wait {

    public static void untilPageLoadComplete(WebDriver driver) {
        untilPageLoadComplete(driver,
                FileReaderManager.getInstance().getConfig().implicitlyWait());
    }

    public static void untilPageLoadComplete(WebDriver driver, int timeoutInSeconds){
        until(driver, (d) ->
        {
            Boolean isPageLoaded = ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) {
                Log.info("Document is loading");
            }
            return isPageLoaded;
        }, timeoutInSeconds);
    }

    public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition){
        until(driver, waitCondition,
                FileReaderManager.getInstance().getConfig().implicitlyWait());
    }

    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition,
                              int timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
        try{
            webDriverWait.until(waitCondition);
        }catch (Exception e){
            Log.error(e.getMessage());
        }
    }

    public static FluentWait<WebDriver> getWait(WebDriver driver) {
        return waitOn(driver, FileReaderManager.getInstance().getConfig().explicitlytWait());
    }

    private static FluentWait<WebDriver> waitOn(WebDriver driver, int timeOutSeconds) {
        return new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withTimeout(Duration.ofSeconds(timeOutSeconds))
                .pollingEvery(Duration.ofSeconds(1));
    }
}
