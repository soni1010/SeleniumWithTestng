package org.harender.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsHandler {

    /*
    Simple Alert -
    This alert is used to notify a simple warning message with an ‘OK’ button

    Prompt Alert -
    This alert will ask the user to input the required information to complete the task.
    We can see that without entering the destination for Hotel, we are not allowed to hit the search button.

    Confirmation Alert -
    This alert is basically used for the confirmation of some tasks.
    For Example: Do you wish to continue a particular task? Yes or No?
    */

    private WebDriver driver;
    private static WebDriverWait wait;
    public AlertsHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait (driver,Duration.ofSeconds(10));
    }

    private static void waitForAlertPresence(){
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (NoAlertPresentException e){
            System.out.println("Alert Presence Time over - No Alert Found \n Exception Message - "+e.getMessage());
        }
    }
    //To cross-verify or handle alerts manually, one can paste the below command.
    public static void dismiss(WebDriver driver,By closeBtn){
        try {
            waitForAlertPresence();
            driver.switchTo().alert();
            driver.findElement(closeBtn).click();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static void dismiss(WebDriver driver){
        try {
            waitForAlertPresence();
            driver.switchTo().alert().dismiss();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static void accept(WebDriver driver){
        try {
            waitForAlertPresence();
            driver.switchTo().alert().accept();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static String getText(WebDriver driver){
        try {
            waitForAlertPresence();
            return driver.switchTo().alert().getText();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
            return "No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage();
        }
    }
    public static String getText(WebDriver driver,By alertMsg){
        try{
            waitForAlertPresence();
            driver.switchTo().alert();
            return driver.findElement(alertMsg).getText();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
            return "No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage();
        }
    }
    public static void sendKeys(WebDriver driver,String stringToSend){
        try{
            waitForAlertPresence();
            driver.switchTo().alert().sendKeys(stringToSend);
        }
        catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }


}
