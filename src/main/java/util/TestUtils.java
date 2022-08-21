package util;

import base.BaseClass;
import base.MessageEnum;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static base.BaseClass.*;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class TestUtils {
    /*
     * This will type text in the textbox
     * @element will accept WebElement
     * @value accept String value which needs to be typed
     */
    public void sendKeys(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }
    /*
     * This will type text in the textbox - specially designed for mobile app
     * @element will accept MobileElement
     * @value accept String value which needs to be typed
     */
    public void appSendKeys(MobileElement element, String value) {
        element.click();
        element.clear();

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appiumDriver.getKeyboard().sendKeys(value);
    }
    /*
     * This method will check whether element is present or not on a page
     * @locator it will accept String value and this will be value of xpath, id or other locator type
     * @locationType will accept String value this will be either xpath, id or css selector
     */
    public boolean isElementPresent(String locatorType, String locator){
        if(BaseClass.platform.equalsIgnoreCase("Mobile")){
            List<MobileElement> allElements = null;
            if (locatorType.equals("xpath")) {
                allElements = appiumDriver.findElements(By.xpath(locator));
            } else if (locatorType.equalsIgnoreCase("cssSelector")) {
                allElements = appiumDriver.findElements(By.cssSelector(locator));
            } else if (locatorType.equalsIgnoreCase("id")) {
                allElements = appiumDriver.findElements(By.id(locator));
            }
            else if (locatorType.equalsIgnoreCase("tagName")) {
                allElements = appiumDriver.findElements(By.tagName(locator));
            }
            else if (locatorType.equalsIgnoreCase("name")) {
                allElements = appiumDriver.findElements(By.name(locator));
            }
            else if (locatorType.equalsIgnoreCase("className")) {
                allElements = appiumDriver.findElements(By.className(locator));
            }
            else if (locatorType.equalsIgnoreCase("linkText")) {
                allElements = appiumDriver.findElements(By.linkText(locator));
            }
            else if (locatorType.equalsIgnoreCase("partialLinkText")) {
                allElements = appiumDriver.findElements(By.partialLinkText(locator));
            }
            if (allElements.size() == 0)
                return false;
            else
                return true;
        }
        else {
            List<WebElement> allElements = null;
            if (locatorType.equals("xpath")) {
                allElements = driver.findElements(By.xpath(locator));
            } else if (locatorType.equalsIgnoreCase("cssSelector")) {
                allElements = driver.findElements(By.cssSelector(locator));
            } else if (locatorType.equalsIgnoreCase("id")) {
                allElements = driver.findElements(By.id(locator));
            }
            else if (locatorType.equalsIgnoreCase("tagName")) {
                allElements = driver.findElements(By.tagName(locator));
            }
            else if (locatorType.equalsIgnoreCase("name")) {
                allElements = driver.findElements(By.name(locator));
            }
            else if (locatorType.equalsIgnoreCase("className")) {
                allElements = driver.findElements(By.className(locator));
            }
            else if (locatorType.equalsIgnoreCase("linkText")) {
                allElements = driver.findElements(By.linkText(locator));
            }
            else if (locatorType.equalsIgnoreCase("partialLinkText")) {
                allElements = driver.findElements(By.partialLinkText(locator));
            }

            if (allElements.size() == 0)
                return false;
            else
                return true;
       }
    }
    /*
     * This method will check whether web element is present or not on a page
     * @element it will accept Web Element(s)
     */
    public boolean isWebElementPresent(List<WebElement> element){
        if (element.size() == 0)
            return false;
        else
            return true;
    }
    /*
     * This method will check whether element is present or not on a page
     * @element it will accept Mobile Element(s)
     */
    public boolean isAppElementPresent(List<MobileElement> element){
        if (element.size() == 0)
            return false;
        else
            return true;
    }
    /*
     * This will click on element provided in the method parameter
     * @param element will accept MobileElement
     */
    public void appClick(MobileElement element){
        try{
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch(Exception e){
            appTapByElementCoordinate(element);
        }

    }
    public void appScrollUp() {
        new TouchAction(appiumDriver).press(PointOption.point(50,250))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(150,150)).release().perform();
    }
    public void appScrollUpByElements(MobileElement mobElement1,MobileElement mobElement2) {
        new TouchAction(appiumDriver).press(ElementOption.element(mobElement2))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) 0.5)))
                .moveTo(ElementOption.element(mobElement1)).release().perform();
    }
    public void appScrollElementIntoView(MobileElement mobElement) {
        int leftX = mobElement.getLocation().getX();
        int upperY = mobElement.getLocation().getY();
        int lowerY = upperY + mobElement.getSize().getHeight();
        new TouchAction( appiumDriver).press(PointOption.point(leftX,lowerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(leftX,upperY)).release().perform();
    }
    public void appScrollElementByCoordinate(MobileElement mobElement,int newHeight) {
        int eleX = mobElement.getLocation().getX();
        int eleY = mobElement.getLocation().getY();
        int newY = eleY + newHeight;
        new TouchAction( appiumDriver).press(PointOption.point(eleX,eleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(eleX,newY)).release().perform();
    }
    public void appScrollElementIntoView(MobileElement mobElement,int newHeight) {
        int eleX = mobElement.getLocation().getX();
        int eleY = mobElement.getLocation().getY();
        new TouchAction( appiumDriver).press(PointOption.point(eleX,eleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(eleX,newHeight)).release().perform();
    }
    public void appTap(MobileElement mobElement){
        try{
            TouchAction action = new TouchAction(appiumDriver);
            action.tap(tapOptions().withElement(element(mobElement))).waitAction(waitOptions(ofSeconds(1))).perform();
        }catch(Exception e){
            System.out.println("Tap on mobile element failed: " + e);
        }
    }
    public void appTapByElementCoordinate(MobileElement mobElement){
        try{
            TouchAction action = new TouchAction(appiumDriver);
            if(mobElement.getSize().height < 100){
                appScrollElementByCoordinate(mobElement,-200);
            }
            Point centerOfElement = mobElement.getCenter();
            action.tap(point(centerOfElement.x, centerOfElement.y)).waitAction(waitOptions(ofSeconds(1))).perform();
        }catch(Exception e){
            System.out.println("Tap on mobile element by Coordinate failed: " + e);
        }
    }
    public void appTapByCoordinate(int x, int y){
        try{
            TouchAction action = new TouchAction(appiumDriver);
            action.tap(point(x, y)).perform();
        }catch(Exception e){
            System.out.println("Tap on mobile element by Coordinate failed: " + e);
        }
    }
    public void appTapByRightOfElementCoordinates(MobileElement element){
        try{
            TouchAction action = new TouchAction(appiumDriver);
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            action.tap(PointOption.point(rightX-50, middleY)).perform();
        }catch(Exception e){
            System.out.println("Tap on mobile element at right end by Coordinate failed: " + e);
        }
    }
    public void appTapByRightCornerOfElement(MobileElement element){
        try{
            TouchAction action = new TouchAction(appiumDriver);
            int rightY = element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;
            action.tap(PointOption.point(rightY-2, middleY)).perform();
        }catch(Exception e){
            System.out.println("Tap on mobile element at right end by Coordinate failed: " + e);
        }
    }
    public String getFormattedDate(Date date, String datePattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return simpleDateFormat.format(date);
    }

    public String getDayOfCurrentMonth(int offset){
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar cal = Calendar.getInstance(timeZone);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)+offset;
        if(dayOfMonth>28){
            dayOfMonth = 1;
        }
        return  String.valueOf(dayOfMonth);
    }
    public void scrollDownInScreen(int offset){
        int screenHeight = appiumDriver.manage().window().getSize().height;
        int screenWidth = appiumDriver.manage().window().getSize().width;

        new TouchAction(appiumDriver).press(PointOption.point(screenWidth/2,screenHeight-50))
                .waitAction(waitOptions(ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(screenWidth/2,screenHeight-offset)).release().perform();
    }
    public void scrollUpInScreen(int offset){
        int screenHeight = appiumDriver.manage().window().getSize().height;
        int screenWidth = appiumDriver.manage().window().getSize().width;

        new TouchAction(appiumDriver).press(PointOption.point(screenWidth/2,screenHeight-offset))
                .waitAction(waitOptions(ofSeconds((long) 0.5)))
                .moveTo(PointOption.point(screenWidth/2,screenHeight-50)).release().perform();
    }
    public void appScrollElementOutOfScreen(String xpath,int scrollOffset, Boolean isScrollDown) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(appiumDriver, (long) 0.5);

        for(int i=0; i<20; i++){
            try{
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
                break;
            }catch(Exception e){
                if(isScrollDown){
                    scrollDownInScreen(scrollOffset);
                }
                else if(!isScrollDown){
                    scrollUpInScreen(scrollOffset);
                }
            }
        }
    }
    public void appSwipeRight(){
        int screenWidth = appiumDriver.manage().window().getSize().width;
        (new TouchAction(appiumDriver)).press(PointOption.point(screenWidth/2,457))
                .moveTo(PointOption.point(screenWidth-100,457)).release().perform();

    }
    public void allowMediaAccess()  {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 2);
        final String allowMediaAccess = "com.android.permissioncontroller:id/permission_allow_button";

        //Allow location access in Android
        if(mobileOS.equalsIgnoreCase("Android")){
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(allowMediaAccess)));
                if(appiumDriver.findElement(By.id(allowMediaAccess)).isDisplayed());
                {
                    appiumDriver.findElement(By.id(allowMediaAccess)).click();
                }
            } catch (Exception e) {
            }

        }
    }
    public String getExpectedMessage(String expectedValue){
        MessageEnum messageEnum = MessageEnum.valueOf(expectedValue);
        String message = messageEnum.getMessage();
        return message;
    }
    public List<String> getRoundedCurrentDateTime(){
        String roundedCurrentDateTime = "";
        String roundedCurrentDateTimePlus30Mins = "";
        SimpleDateFormat mobileDateFormatter = new SimpleDateFormat("MMM dd, YYYY h:mm aa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int round = calendar.get(Calendar.MINUTE) % 30;
        calendar.add(Calendar.MINUTE, round < 8 ? -round : (30-round));
        calendar.set( Calendar.SECOND, 0 );
        roundedCurrentDateTime = mobileDateFormatter.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, 30);
        roundedCurrentDateTimePlus30Mins = mobileDateFormatter.format(calendar.getTime());
        return Arrays.asList(roundedCurrentDateTime,roundedCurrentDateTimePlus30Mins);
    }
    public List<Integer> getCurrentHHMM(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int MM = calendar.get(Calendar.MINUTE);
        int HH = calendar.get(Calendar.HOUR_OF_DAY);
        return Arrays.asList(HH,MM);
    }
}
