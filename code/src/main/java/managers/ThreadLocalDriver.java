package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDriver {

    //ThreadLocal for Thread-Safe executions to run tests in parallel without any problem.
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    //Set driver to tlDriver
    public synchronized static void driverInitializer () {
        tlDriver = ThreadLocal.withInitial(() -> createDriver());
    }

    //Get driver from tlDriver
    public synchronized static WebDriver getDriver () {
        return tlDriver.get();
    }


    private static WebDriver createDriver() {
        driverType = FileReaderManager.getInstance().getConfig().browser().getDriverType();
        environmentType = FileReaderManager.getInstance().getConfig().environment()
                .getEnvironmentType();

        switch (environmentType) {
            case LOCAL :
                return createLocalDriver();
            case REMOTE :
                return createRemoteDriver();
        }
        return null;
    }
    private static WebDriver createRemoteDriver() {
        WebDriver driver = null;
        DesiredCapabilities cap = null;
        switch (driverType) {
            case FIREFOX :
                cap = DesiredCapabilities.firefox();
                break;
            case CHROME :
                cap = DesiredCapabilities.chrome();
                break;
        }
        try {
            String url = FileReaderManager.getInstance().getConfig().urlHub();
            driver = new RemoteWebDriver(new URL(url), cap);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Url hub malformated");
        }

        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance()                       .getConfig().implicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver createLocalDriver() {
        WebDriver driver = null;
        switch (driverType) {
            case FIREFOX :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(OptionsManager.getFirefoxOptions());
                break;
            case CHROME :
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(OptionsManager.getChromeOptions());
                break;
        }

        if(FileReaderManager.getInstance().getConfig().windowMaximize()) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(
                                FileReaderManager.getInstance().getConfig().implicitlyWait(),
                                TimeUnit.SECONDS);
        return driver;
    }


}