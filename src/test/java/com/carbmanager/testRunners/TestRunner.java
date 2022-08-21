package com.carbmanager.testRunners;

import base.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static base.BaseClass.testUrl;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/carbmanager/steps"},
        plugin = {"json:target/cucumber-report/cucumber_cardmanager.json", "rerun:target/failed_scenarios_carbmanager.txt"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setupFramework() throws Exception {
        BaseClass baseClass = new BaseClass();
        baseClass.setUp();
    }

    @AfterSuite()
    public void tearDown(){
        if(BaseClass.driver!=null){
            BaseClass.driver.quit();
        }
    }
}