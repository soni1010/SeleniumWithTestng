package org.harender.actions;

import org.harender.locators.LoginPageLocators;
import org.harender.utils.HelperClass;
import org.harender.utils.ReusableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPageActions {

    LoginPageLocators loginPageLocators = null;
    WebDriver driver;
    public LoginPageActions() {
        this.loginPageLocators = new LoginPageLocators();
        PageFactory.initElements(HelperClass.getDriver(),loginPageLocators);
        driver=HelperClass.getDriver();
    }

    public void screenshotTaker(String name){
        ReusableMethods.takeScreenshot(driver,name);
    }

    boolean a=true;
    // Set user name in textbox
    public void setUserName(String strUserName) {
        loginPageLocators.userName.sendKeys(strUserName);
    }

    // Set password in password textbox
    public void setPassword(String strPassword) {
        loginPageLocators.password.sendKeys(strPassword);
    }

    // Click on login button
    public void clickLogin() {
        loginPageLocators.login.click();
    }

    // Get the title of Login Page
    public String getLoginTitle() {
        return loginPageLocators.titleText.getText();
    }

    // Get the error message when username is blank
    public String getMissingUsernameText() {
        return loginPageLocators.missingUsernameErrorMessage.getText();
    }

    // Get the error message when password is blank
    public String getMissingPasswordText() {
        return loginPageLocators.missingPasswordErrorMessage.getText();
    }

    // Get the Error Message
    public String getErrorMessage() {
        return loginPageLocators.errorMessage.getText();
    }

    // LinkedIn Icon is displayed
    public Boolean getLinkedInIcon() {
        return loginPageLocators.linkedInIcon.isDisplayed();
    }

    // FaceBook Icon is displayed
    public Boolean getFaceBookIcon() {
        return loginPageLocators.faceBookIcon.isDisplayed();
    }

    // Click on Forget Your Password link
    public void clickOnForgetYourPasswordLink() {
        loginPageLocators.ForgotYourPasswordLink.click();
    }

    public void login(String strUserName, String strPassword) {
        // Fill username
        this.setUserName(strUserName);
        // Fill password
        this.setPassword(strPassword);
        // Click Login button
        this.clickLogin();
    }
}
