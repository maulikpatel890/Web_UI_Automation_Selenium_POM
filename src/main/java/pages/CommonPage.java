package pages;

/*************************************** PURPOSE **********************************
 - This class will hold all the the commonly used data members & methods of different pages across the app.
 */

import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.TestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static util.DriverManager.getAppWebDriverWait;

public class CommonPage extends BaseClass {

    public CommonPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                BaseClass.defaultTimeout), this);
    }
    TestUtils testUtils = new TestUtils();



}
