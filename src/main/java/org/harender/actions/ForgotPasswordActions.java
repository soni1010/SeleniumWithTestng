package org.harender.actions;

import org.harender.locators.ForgotPasswordLocators;
import org.harender.utils.HelperClass;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordActions {

    ForgotPasswordLocators forgotPasswordLocators = null;

    public ForgotPasswordActions() {

        this.forgotPasswordLocators = new ForgotPasswordLocators();

        PageFactory.initElements(HelperClass.getDriver(),forgotPasswordLocators);
    }

    // Get the Heading of Forgot Password page
    public String getForgotPasswordPageText() {
        return forgotPasswordLocators.ForgotPasswordHeading.getText();
    }

}
