package com.google.android.youtube.automation.listener;

import io.appium.java_client.events.api.general.ElementEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.testng.*;

import java.util.Optional;

/**
 * @author Carmelo Buelvas
 * @since 1.0
 */
@Component
public class AppListener extends TestListenerAdapter
        implements ISuiteListener, ITestNGListener, ITestListener, IConfigurationListener, ElementEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppListener.class);
    private Optional<ITestContext> optionalTestContext = Optional.ofNullable(Reporter.getCurrentTestResult().getTestContext());

    @Override
    public void onTestStart(ITestResult tr) {
        optionalTestContext.ifPresent(ITestContext::getStartDate);
        LOGGER.info("BaseTest {} STARTED", tr.getName());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        optionalTestContext.ifPresent(testContext -> testContext.getPassedTests().addResult(tr, tr.getMethod()));
        LOGGER.info("BaseTest {} PASSED", tr.getName());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        optionalTestContext.ifPresent(testContext -> testContext.getFailedTests().addResult(tr, tr.getMethod()));
        LOGGER.error("BaseTest {} FAILED", tr.getName(), String.valueOf(tr.getThrowable()));
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        optionalTestContext.ifPresent(testContext -> testContext.getSkippedTests().addResult(tr, tr.getMethod()));
        LOGGER.warn("BaseTest {} SKIPPED", tr.getName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        LOGGER.info("Suite {} STARTED", iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LOGGER.info("Suite {} PASSED", iSuite.getName());
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        optionalTestContext.ifPresent(testContext -> testContext.getFailedConfigurations().addResult(itr, itr.getMethod()));
        LOGGER.error("Configuration {} FAILED", itr.getName());
    }

    @Override
    public void onConfigurationSuccess(ITestResult itr) {
        optionalTestContext.ifPresent(testContext -> testContext.getPassedConfigurations().addResult(itr, itr.getMethod()));
        LOGGER.info("Configuration {} PASSED", itr.getName());
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {
        optionalTestContext.ifPresent(testContext -> testContext.getSkippedConfigurations().addResult(itr, itr.getMethod()));
        LOGGER.warn("Configuration {} SKIPPED", itr.getName());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String[] data = getElementData(element);
        LOGGER.info("Click on [{} | {}]", data[0], data[1]);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        // Do nothing.
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        // Do nothing.
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // Do nothing.
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        // Do nothing.
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        // Do nothing.
    }

    private String[] getElementData(WebElement element) {
        String[] data = new String[2];
        data[0] = element.getTagName();
        data[1] = element.getText() != null ? element.getText() : "";
        return data;
    }
}