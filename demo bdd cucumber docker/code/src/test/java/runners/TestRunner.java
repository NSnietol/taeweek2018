package runners;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import managers.FileReaderManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


@CucumberOptions(
        features = "src/test/resources/features",
        glue= {"stepDefinitions"},
        tags = {"~@Ignore"},
        plugin = {
                "com.cucumber.listener.ExtentCucumberFormatter:target/extent-reports/"+
                        "report.html",
                "json:target/cucumber-reports/CucumberTestReport.json"
        }
)
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true )
    public void setUpClass() throws Exception{
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups="cucumber", description="Run cucumber Feature", dataProvider="features")
    public void feature(CucumberFeatureWrapper cucumberFeature){
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() { return testNGCucumberRunner.provideFeatures(); }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception{
        testNGCucumberRunner.finish();
    }

    @AfterClass
    public static void writeExtentReport() {
        extendReport();
        cucumberReport();
    }

	private static void extendReport() {
        String filePath = System.getProperty("user.dir");
        String reportConfigPath = filePath.concat(FileReaderManager.getInstance().getConfig()
                .reportConfigPath());
        Reporter.loadXMLConfig(new File(reportConfigPath));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", 	"Windows 10" + "64 Bit");
        Reporter.setSystemInfo("Selenium", "3.14.0");
        Reporter.setSystemInfo("Maven", "3.5.4");
        Reporter.setSystemInfo("Java Version", "1.8.0_172");
    }

	private static void cucumberReport() {
        /*File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/advanced-reports");

        String projectName = "DemoAutomation";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Environment",
                FileReaderManager.getInstance().getConfig()
                        .environment().getParam());
        configuration.addClassifications("Browser",
                FileReaderManager.getInstance().getConfig()
                .browser().getParam());
        configuration.addClassifications("Build Number", FileReaderManager.getInstance()
                .getConfig().buildNumber());

        ReportBuilder report = new ReportBuilder(jsonFiles, configuration);
        report.generateReports();*/
    }
}
