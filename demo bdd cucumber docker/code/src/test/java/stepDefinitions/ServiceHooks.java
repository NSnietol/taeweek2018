package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import managers.FileReaderManager;
import managers.PageGenerator;
import managers.ThreadLocalDriver;

public class ServiceHooks {
    public static PageGenerator page;

    @Before
    public void globalSetup() {
        DOMConfigurator.configure(FileReaderManager.getInstance().getConfig().log4jPath());
        //Initialize ThreadLocal Driver
        ThreadLocalDriver.driverInitializer();
        page = new PageGenerator();
    }



    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) ThreadLocalDriver.getDriver()).getScreenshotAs(OutputType.FILE);

                String reportPath = System.getProperty("user.dir") +
                        FileReaderManager.getInstance().getConfig().reportPathGenerate();
                File destinationPath = new File(reportPath + "//screenshots//" +
                        screenshotName + ".png");
                FileUtils.copyFile(sourcePath, destinationPath);


                //Embed image into extent report
                Reporter.addScreenCaptureFromPath(destinationPath.toString()
                        .replace(reportPath, "."));
                //Embed image into cucumber report
                final byte[] screenshot = ((TakesScreenshot) ThreadLocalDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @After(order = 0)
    public void globalTearDown() {
        ThreadLocalDriver.getDriver().quit();
    }

}
