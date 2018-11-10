package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
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