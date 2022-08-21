package base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CommonPage;
import util.DriverManager;
import util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    private static final String fileSeparator = File.separator;
    public static WebDriver driver;
    public static AppiumDriver appiumDriver;
    public static Properties config = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    private static final String rootDirectory = System.getProperty("user.dir");
    public static String mobileOS = System.getProperty("mobileOS");
    //public static String platform = System.getProperty("platform");
    public static String platform = "Web";
    public static final String packageName="com.ipl.qa.debug";
    public static String iOSAppURL = "https://appstoreconnect.apple.com/apps/123123123/testflight/ios/0f643c1d-4316-4385-8574-ebbc7e669a4d";
    public static WebDriverWait wait;
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;
    public final static int defaultTimeout = 60;
    public static String testUrl;
    public void setUp() throws Exception {
        fis = new FileInputStream(rootDirectory +fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator
        + "properties" + fileSeparator + "Config.properties");
        config.load(fis);
        BasicConfigurator.configure();
        testUrl = config.getProperty("testUrl");
        log.info("Config file loaded !!!");
        initializeDriver();
    }

    public void initializeDriver() throws Exception {
        if(platform.equalsIgnoreCase("Web")){
            String browserName = config.getProperty("browserName");
            int implicitTimeOut = Integer.parseInt(config.getProperty("implicitWait"));
            driver = DriverManager.getDriver(browserName);
            wait = DriverManager.getWebDriverWait(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(implicitTimeOut, TimeUnit.SECONDS);
            driver.get(testUrl);
        }

        if(platform.equalsIgnoreCase("Mobile")){
            int port = 4723;

            if(!checkIfServerIsRunnning(port)) {
                setupAppiumServer();
            } else {
                Log.info("Appium Server already running on Port - " + port);
            }
            appiumDriver = DriverManager.openApp();
            wait = DriverManager.getAppWebDriverWait(appiumDriver);
        }
    }
    public void restartApp(){
        appiumDriver.terminateApp(packageName);
        appiumDriver.activateApp(packageName);
        Log.info("App is restarted....");
    }

    public boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public void setupAppiumServer(){
        //Set Capabilities
        cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        builder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Log.info("Appium Server started....");
    }

    private String takeScreenshot(WebDriver driver, String screenshotName){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = rootDirectory+fileSeparator+"screenshot"+fileSeparator+screenshotName
                +"-"+timeStamp+".png";
        File finalDestination = new File(destination);
        try{
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
    /*
     * This will return random integer number between 100 - 999999
     */
    public static int getRandomNumber(){
        Random r = new Random();
        int low = 100;
        int high = 999999;
        return r.nextInt(high-low) + low;
    }
}
