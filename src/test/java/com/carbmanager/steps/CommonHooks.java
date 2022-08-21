package com.carbmanager.steps;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;

public class CommonHooks extends BaseClass {
    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            if(platform.equalsIgnoreCase("Mobile")){
                final byte[] screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png",scenario.getId());
                restartApp();
            }
            else{
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png",scenario.getId());
            }

        }
    }
}
