package stepDefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.FileReaderManager;
import managers.ThreadLocalDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.PageGenerator;

public class ServiceHooks {

    public static PageGenerator page;

    @Before
    public void globalSetup() {
        DOMConfigurator.configure(FileReaderManager.getInstance().getConfig().log4jPath());
        //Initialize ThreadLocal Driver
        ThreadLocalDriver.driverInitializer();
        page = new PageGenerator(ThreadLocalDriver.getDriver());
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            //Embed image into cucumber report
            final byte[] screenshot = ((TakesScreenshot) ThreadLocalDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }
    @After(order = 0)
    public void globalTearDown() {
        ThreadLocalDriver.getDriver().quit();
    }

}
