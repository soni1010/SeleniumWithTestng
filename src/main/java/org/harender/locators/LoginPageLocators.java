package org.harender.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {

    @FindBy(name = "username")
    public WebElement userName;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(id = "logInPanelHeading")
    public WebElement titleText;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/span")
    public WebElement missingUsernameErrorMessage;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/span")
    public WebElement missingPasswordErrorMessage;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    public WebElement login;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")
    public  WebElement errorMessage;

    @FindBy(xpath = "//*[@href='https://www.linkedin.com/company/orangehrm/mycompany/']")
    public  WebElement linkedInIcon;

    @FindBy(xpath = "//*[@href='https://www.facebook.com/OrangeHRM/']") //Invalid Xpath
    public  WebElement faceBookIcon;

    @FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p")
    public  WebElement ForgotYourPasswordLink;


    //---------- Xpath Dynamic Attribute values Example ----------------//
    //https://www.scientecheasy.com/2019/08/xpath-axes.html/

    //*[@id='app']//div[contains(@class, 'orangehrm-login-forgot')]/p
    //*[@id='app']//div[starts-with(@class, 'orangehrm-lo')]/p
    //*[@id='app']//div[ends-with(@class, 'forgot')]/p
    //input[@value = 'Log In' or @type = 'submit']
    //button[@type = 'submit' and @id = 'u_0_19']
    //div[text() = 'Recent logins']

    //------------  Xpath Axis --------------------------------------//
    /*
    Child Axis              : //child::tagName
    Parent Axis             : //parent::tagName
    Self Axis               : //self::tagName
    Ancestor Axis           : //ancestor::tagName
    Ancestor-or-self Axis   : //input[@id = 'u_0_3']//ancestor-or-self::input
    Descendant Axis         : //div[@class = 'signup_form new']//descendant::input
    Descendant-or-self Axis : //div[@class = 'signup_form new']//descendant-or-self::div
    Following Axis          : //input[@id = 'u_0_r']//following::input
    Following-sibling Axis  : //input[@id = 'u_0_5']//following-sibling::label
    Preceding Axis          : //input[@id = 'u_0_2']//preceding::input
    Preceding-sibling Axis  : //a[text() = 'Videos']//preceding-sibling::a
    Attribute Axis          : //input[attribute::name = 'q']
    Namespace Axis          :
    */


}
