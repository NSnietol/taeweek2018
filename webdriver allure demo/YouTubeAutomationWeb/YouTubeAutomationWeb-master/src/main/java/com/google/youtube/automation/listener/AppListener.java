package com.google.youtube.automation.listener;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
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
        implements ISuiteListener, ITestNGListener, ITestListener, IConfigurationListener, WebDriverEventListener {

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
    public void beforeAlertAccept(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        String[] data = getElementData(webElement);
        LOGGER.info("Click on [{} | {}]", data[0], data[1]);
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        // TODO Auto-generated method stub
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        // TODO Auto-generated method stub
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        // TODO Auto-generated method stub
    }

    private String[] getElementData(WebElement element) {
        String[] data = new String[2];
        data[0] = element.getTagName();
        data[1] = element.getText() != null ? element.getText() : "";
        return data;
    }

}