package org.harender.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.harender.actions.ForgotPasswordActions;
import org.harender.actions.HomePageActions;
import org.harender.actions.LoginPageActions;
import org.harender.utils.HelperClass;
import org.testng.Assert;

public class LoginPageDefinitions {

    LoginPageActions objLogin = new LoginPageActions();
    HomePageActions objHomePage = new HomePageActions();
    ForgotPasswordActions objForgotPasswordPage = new ForgotPasswordActions();

    @Given("User is on HRMLogin page {string}")
    public void loginTest(String url) {
        HelperClass.openPage(url);
    }

    @When("User enters username as {string} and password as {string}")
    public void goToHomePage(String userName, String passWord) {
        // login to application
        objLogin.login(userName, passWord);

        // go the next page

    }

    @When("User clicks on Forgot your Password Link")
    public void goToForgotYourPasswordPage() {
        objLogin.clickOnForgetYourPasswordLink();
    }

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {
        // Verify home page
        Assert.assertTrue(objHomePage.getHomePageText().contains("Dashboard"));
    }

    @Then("User should be able to see error message {string}")
    public void verifyErrorMessage(String expectedErrorMessage) {
        // Verify home page
        Assert.assertEquals(objLogin.getErrorMessage(),expectedErrorMessage);
    }

    @Then("User should be able to see a message {string} below Username")
    public void verifyMissingUsernameMessage(String message) {
        Assert.assertEquals(objLogin.getMissingUsernameText(),message);
    }

    @Then("User should be able to see LinkedIn Icon")
    public void verifyLinkedInIcon( ) {
        Assert.assertTrue(objLogin.getLinkedInIcon());
    }

    @Then("User should be able to see FaceBook Icon")
    public void verifyFaceBookIcon( ) {
        Assert.assertTrue(objLogin.getFaceBookIcon());
    }

    @Then("User should navigate to a new page")
    public void verfiyForgetYourPasswordPage() {
        Assert.assertEquals(objForgotPasswordPage.getForgotPasswordPageText(), "Reset Password");
    }

}
