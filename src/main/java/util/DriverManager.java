package util;


import base.BaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import static base.BaseClass.appiumDriver;

public class DriverManager extends BaseClass{

    private static final String WINDOW_WIDTH = System.getenv("window-width");
    private static final String WINDOW_HEIGHT = System.getenv("window-height");
    private static final String HEADLESS = "--headless";
    private static final String HEADLESS_CHROME = "headless-chrome";
    private static final String FIREFOX = "firefox";
    private static final String HEADLESS_FIREFOX = "headless-firefox";
    private static final String WINDOW_SIZE = "--window-size=" + WINDOW_WIDTH + "x" + WINDOW_HEIGHT;
    public static WebDriverWait wait;

    public static WebDriver getDriver(String browserName){
        if(browserName == null){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        switch (browserName.toLowerCase()){
            case HEADLESS_CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(HEADLESS);
                chromeOptions.addArguments(WINDOW_SIZE);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                WebDriverManager.chromedriver().setup();
                return new FirefoxDriver();
            case HEADLESS_FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(HEADLESS);
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions);
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }
    public static WebDriverWait getWebDriverWait(WebDriver driver){
        wait = new WebDriverWait(driver, 20);
        return wait;
    }

    public static AppiumDriver openApp() throws Exception {
        if (appiumDriver == null) {
            String serverUrl = "http://127.0.0.1:4723/wd/hub";
            DeviceSettings capabilities  = new DeviceSettings();
            if (mobileOS.equalsIgnoreCase("iOS")) {
                appiumDriver = new IOSDriver(new URL(serverUrl), capabilities.iOSCaps());
                acceptLocationAccess();
            }
            if (mobileOS.equalsIgnoreCase("Android")) {
                appiumDriver = new AndroidDriver(new URL(serverUrl), capabilities.androidCaps());
                acceptLocationAccess();
                AndroidDriver androidDriver = (AndroidDriver) appiumDriver;
            }
            else {
                Log.info("The mobileOS <" + mobileOS + "> seems invalid. Please check CLI mobileOS argument.");
            }
        }
        return appiumDriver;
    }

    public static void acceptLocationAccess()  {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        final String locationAccessAlert = "com.android.permissioncontroller:id/permission_allow_foreground_only_button";

        //Allow location access in Android
        if(mobileOS.equalsIgnoreCase("Android")){
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locationAccessAlert)));
                if(appiumDriver.findElement(By.id(locationAccessAlert)).isDisplayed());
                {
                    appiumDriver.findElement(By.id(locationAccessAlert)).click();
                }
            } catch (Exception e) {
            }

        }
    }

    public static WebDriverWait getAppWebDriverWait(AppiumDriver appiumDriver){
        wait = new WebDriverWait(appiumDriver, 60);
        return wait;
    }

}
