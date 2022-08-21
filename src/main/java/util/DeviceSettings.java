package util;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static base.BaseClass.packageName;
import static base.BaseClass.iOSAppURL;

public class DeviceSettings {

    public DesiredCapabilities androidCaps() throws Exception{
        DesiredCapabilities deviceCaps = new DesiredCapabilities();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        deviceCaps.setCapability("instrumentApp",true);
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","auto");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("appPackage", packageName);
        capabilities.setCapability("appActivity", "com.ipl.debug.MainActivity");
        capabilities.setCapability("appWaitActivity", "com.ipl.debug.MainActivity");
        capabilities.setCapability("nativeWebScreenshot", true);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("noSign", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("autoGrantPermissions","true");
//        capabilities.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, System.getProperty("user.dir")+"/src/test/resources/Files/chromedriver");
        deviceCaps.setCapability("app",System.getProperty("user.dir")+"/src/test/resources/files/ipl_QA.apk");
        capabilities.merge(deviceCaps);

        return capabilities;
    }

    public DesiredCapabilities iOSCaps(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion","10.1");
        capabilities.setCapability("deviceName","iPhone 6s");
        capabilities.setCapability("udid","auto");
        capabilities.setCapability("instrumentApp",true);
        capabilities.setCapability("startIWDP", true);
        capabilities.setCapability("nativeWebScreenshot", true);
        capabilities.setCapability("xcodeConfigFile", System.getProperty("user.dir")+"/src/test/resources/files/appium.xcconfig");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("showXcodeLog", false);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("autoGrantPermissions","true");
        //capabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/src/test/java/com/ipl/resource/ipl_QA.ipa");
        capabilities.setCapability(MobileCapabilityType.APP,iOSAppURL);
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, packageName);
        return capabilities;
    }
}
