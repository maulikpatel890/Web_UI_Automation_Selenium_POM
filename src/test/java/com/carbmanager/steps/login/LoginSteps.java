package com.carbmanager.steps.login;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LandingPage;
import pages.LoginPage;
import util.TestUtils;

public class LoginSteps extends BaseClass {
    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();

    TestUtils testUtils = new TestUtils();

    @Given("User visits the landing page of carb manager web app.")
    public void openTestAppUrl() {
        Assert.assertTrue(landingPage.txt_landingPage_welcomeTxt.isDisplayed(),"Missing Welcome Text in Landing Page.");
    }

    @When("User clicks on sign in link")
    public void clickSignInLink_landingPage() {
        landingPage.link_landingPage_SignIn.click();
    }
    @Then("User should be redirected to sign in page")
    public void verify_redirection_to_sign_in_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains(LandingPage.signIn_urlPath),"User is not navigated to sign in page.");
    }
    @Then("Verify sign in page contents")
    public void verify_sign_in_page_content() {
        Assert.assertTrue(loginPage.link_signInPage_backArrow.isDisplayed(), "Back arrow is missing.");
        Assert.assertTrue(loginPage.img_signInPage_logo_carbManager.isDisplayed(), "Carb Manager Logo is missing.");
        Assert.assertEquals(loginPage.txt_signInPage_title.getText(), "Welcome Back");
        Assert.assertEquals(loginPage.txt_signInPage_subTitle.getText(), "Please sign in below to get started.");
        Assert.assertTrue(loginPage.txtbx_signInPage_Email.isDisplayed(), "Email Textbox is missing");
        Assert.assertTrue(loginPage.txtbx_signInPage_Password.isDisplayed(), "Password Textbox is missing");
        Assert.assertTrue(loginPage.btn_signInPage_Continue.isDisplayed(), "Continue button is missing");
        Assert.assertTrue(loginPage.link_signInPage_forgotPassword.isDisplayed(), "Forgot Password Link is missing");
        Assert.assertTrue(loginPage.btn_signInPage_ContinueWithApple.isDisplayed(), "Continue with Apple button is missing");
        Assert.assertTrue(loginPage.btn_signInPage_ContinueWithGoogle.isDisplayed(), "Continue with Google button is missing");
        Assert.assertEquals(loginPage.txt_signInPage_dontHaveAnAccount.getText(), "Don't have an account? Sign up here");
        Assert.assertTrue(loginPage.link_signInPage_SignupHere.isDisplayed(), "Sign up here link is missing");
        Assert.assertEquals(loginPage.txt_signInPage_hcaptcha_disclaimerText.getText(), "This site is protected by hCaptcha and their\n" +
                "privacy policy and terms apply.");
    }
}
