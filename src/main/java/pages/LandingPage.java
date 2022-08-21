package pages;

import base.BaseClass;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.temporal.ChronoUnit;

import static base.BaseClass.driver;

public class LandingPage {
    public LandingPage(){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                BaseClass.defaultTimeout), this);
    }

    public static final String signIn_urlPath = "/account/signin";
    public static final String welcomeText_LandingPage_xpath = "//h2[normalize-space()='Welcome to Carb Manager']";
    public static final String signInLink_landingPage_xpath = "//a[@href='"+signIn_urlPath+"']";

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= welcomeText_LandingPage_xpath)
    public WebElement txt_landingPage_welcomeTxt;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= signInLink_landingPage_xpath)
    public WebElement link_landingPage_SignIn;
}
