package com.taeweek.wiremockdemo;



import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = {"src/test/resources/features"},
        glue = "com.taeweek.wiremockdemo.steps"
)
public class Runner {

}
