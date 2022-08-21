package pages;

import base.BaseClass;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class LoginPage extends BaseClass {

    public LoginPage(){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,
                BaseClass.defaultTimeout), this);
    }



    public static final String pointTableRow_tagName = "tr";
    public static final String pointTableColumn_tagName = "td";

    public List<WebElement> totalRowsList;
    public WebElement getTableBody;
    public List<WebElement> totalColumnsList;
    public WebElement teamName;



    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//a[contains(.,'Back')]")
    public WebElement link_signInPage_backArrow;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//a[@class='account-header__logo-link router-link-active']//*[name()='svg']")
    public WebElement img_signInPage_logo_carbManager;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//h1[contains(@class,'account-header__title')]")
    public WebElement txt_signInPage_title;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//p[contains(@class,'account-header__subtitle')]")
    public WebElement txt_signInPage_subTitle;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//input[@name='email']")
    public WebElement txtbx_signInPage_Email;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//input[@name='password']")
    public WebElement txtbx_signInPage_Password;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//span[text()=' Continue ']")
    public WebElement btn_signInPage_Continue;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//a[@href='/account/forgot-password']")
    public WebElement link_signInPage_forgotPassword;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//div[contains(text(),'Continue With Apple')]")
    public WebElement btn_signInPage_ContinueWithApple;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//div[contains(text(),'Continue With Google')]")
    public WebElement btn_signInPage_ContinueWithGoogle;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//p[@class='account-subtext']")
    public WebElement txt_signInPage_dontHaveAnAccount;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//a[@href='/onboarding/goal']")
    public WebElement link_signInPage_SignupHere;

    @WithTimeout(time = BaseClass.defaultTimeout, chronoUnit = ChronoUnit.SECONDS)
    @FindBy(xpath= "//div[@class='account-subtext__notice hcaptcha-notice']")
    public WebElement txt_signInPage_hcaptcha_disclaimerText;
}
