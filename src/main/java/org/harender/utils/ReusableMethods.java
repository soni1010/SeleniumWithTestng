package org.harender.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class ReusableMethods {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private JavascriptExecutor jsExecutor;
    private Actions actions;
    private static boolean scrnShotFldrNotAvailable=true;

    // Specify the path where you want to create the folder
    private static String path = "C:\\Users\\User\\Documents\\GitHub\\SeleniumWithTestng\\ScreenShotsDirectory\\";
    private static String scrnShotFolderPath;

    public ReusableMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    public void waitForVisibilityOfAllElements(By anyLocator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(anyLocator));
    }
    public void waitForElementToBeClickableAndClick(By anyLocator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(anyLocator));
        element.click();
    }
    public void selectByValueFromDropdown(By dropdownLocator, String valueToSelect) {
        WebElement dropdownElement = driver.findElement(dropdownLocator);
        Select select = new Select(dropdownElement);
        select.selectByValue(valueToSelect);
    }

//------------------- Switch To Handling --------------------------------------------------------

    private static Alert switchToAlert(){
        try {
            waitForAlertPresence();
            return driver.switchTo().alert();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
            return null;
        }
    }
    public static void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }
    public static void switchToFrame(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }
    public static void switchToFrame(String frameNameOrId) {
        driver.switchTo().frame(frameNameOrId);
    }
    public static void switchToFrame(By frameLocator) {
        WebElement frameElement = driver.findElement(frameLocator);
        driver.switchTo().frame(frameElement);
    }
    // Switch to the default content (out of any frames)
    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
    public static void switchToWindow(String byWindowHandle) {
        driver.switchTo().window(byWindowHandle);
    }
    public static void switchToWindow(int byWindowIndex) {
        String[] windowHandles = driver.getWindowHandles().toArray(new String[0]);
        if(windowHandles.length>=byWindowIndex)
            driver.switchTo().window(windowHandles[byWindowIndex]);
        else
            System.out.println("Total Window Handles Available - "+windowHandles.length+
                               "Received Window Index to Switch - "+byWindowIndex);
    }
    public static void switchToNewWindowAndCloseCurrent() {
        String currentWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close();
                break;
            }
        }
    }
    // Switch to an iframe using its index, then perform an action, and switch back to default content
    public static void switchToIframePerformActionSwitchBack(int iframeIndex, By elementLocator) {
        switchToFrame(iframeIndex);
        driver.findElement(elementLocator).click();
        // Switch back to default content
        switchToDefaultContent();
    }
    // Switch to an alert, get its text, and accept it
    public static String switchToAlertGetTextAndAccept() {
        switchToAlert();
        String alertText=getTextFromAlert();
        acceptAlert();
        return alertText;
    }
    // Switch to an alert, get its text, and dismiss it
    public String switchToAlertGetTextAndDismiss() {
        switchToAlert();
        String alertText=getTextFromAlert();
        dismissAlert();
        return alertText;
    }



//------------------- Actions Handling Random Scenarios-----------------------------------------------------


//--------------- Alert Handling Methods --------------------------------------------------------------------

    private static void waitForAlertPresence(){
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (NoAlertPresentException e){
            System.out.println("Alert Presence Time over - No Alert Found \n Exception Message - "+e.getMessage());
        }
    }
    public static void dismissAlert(By closeBtn){
        try {
            switchToAlert();
            driver.findElement(closeBtn).click();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static void dismissAlert(){
        try {
            switchToAlert().dismiss();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static void acceptAlert(){
        try {
            switchToAlert().accept();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }
    public static String getTextFromAlert(){
        try {
            return switchToAlert().getText();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
            return "No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage();
        }
    }
    public static String getTextFromAlert(By alertMsg){
        try{
            switchToAlert();
            return driver.findElement(alertMsg).getText();
        }catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
            return "No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage();
        }
    }
    public static void sendKeysToAlert(String stringToSend){
        try{
            switchToAlert().sendKeys(stringToSend);
        }
        catch (NoAlertPresentException e){
            System.out.println("No Alert Present \n Cause : "+e.getCause()+"\n Getting Message - "+e.getMessage());
        }
    }

//------------------- Navigation Handling Methods --------------------------------------------------------

    public static void navigateBack() {
        driver.navigate().back();
    }
    public static void navigateForward() {
        driver.navigate().forward();
    }
    public static void refreshUsingNavigate() {
        driver.navigate().refresh();
    }
    public static void navigateToURL(URL url) {
        driver.navigate().to(url);
    }
    public static void navigateToURL(String urlAsString) {
        driver.navigate().to(urlAsString);
    }











    // Initialize WebDriver with default settings
    public static WebDriver initializeDriver() {
        // Code to initialize WebDriver with default settings
        driver = new ChromeDriver();
        return driver;
    }
    // Initialize WebDriver with custom settings
    public static WebDriver initializeDriver(String browserName) {
        WebDriver driver = null;

        switch (browserName.toLowerCase()) {
            case "chrome":
                // Set the path to the ChromeDriver executable
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                // Set the path to the GeckoDriver executable (Firefox driver)
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "ie":
                // Set the path to the Internet Explorer Driver executable
                System.setProperty("webdriver.ie.driver", "path/to/IEDriverServer");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("Unsupported browser: " + browserName);
        }

        return driver;
    }

    public static WebDriver initializeChromeDriver(String windowsSize,String infoBars, String notifications,String extensionsOnOrOff,String certificationErrors){
        ChromeOptions options = new ChromeOptions();
        // Maximize the browser window
        options.addArguments(windowsSize);
        // Disable infobars and notifications
        options.addArguments(infoBars);
        options.addArguments(notifications);
        // Disable extensions
        options.addArguments(extensionsOnOrOff);
        // Ignore certificate errors
        options.addArguments(certificationErrors);

        driver = new ChromeDriver(options);
        return driver;
    }













    //Fluent Wait
    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds,long pollingFrequency) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingFrequency))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    // Explicit Wait
    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    // Click an element
    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }
    // Click an element with JavaScriptExecutor
    public void clickElement(By locator, boolean useJavaScript) {
        if (useJavaScript) {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", locator);
        } else {
            clickElement(locator); // Delegating to the previous method for default behavior
        }
    }
    // Take screenshot with passing driver instance and name of screenshot
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        // Convert WebDriver object to TakeScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        // Capture screenshot as a file
        File source = ts.getScreenshotAs(OutputType.FILE);
        // Define the destination path and file name
        String destination = scrnShotFolderPath+"\\" + screenshotName + ".jpeg";
        File target = new File(destination);

        try {
            // Copy the captured screenshot to the destination file
            FileUtils.copyFile(source, target);
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
    // Get the current method name
    public static String getCurrentMethodName() {
        // Use the Thread.currentThread().getStackTrace() to get the stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // The current method name is at index 2 in the stack trace
        // Index 0 is getStackTrace, index 1 is getCurrentMethodName, and index 2 is the calling method
        String methodName = stackTrace[2].getMethodName();

        return methodName;
    }
    // Create current execution related screenshot directory
    public static void  CreateCurrExecuScreenShotDir(){

        // Get current date and time
        Date currentDate = new Date();
        boolean dateFolderSuccess=false;
        boolean timeFolderSuccess;

        if(scrnShotFldrNotAvailable) {

            // Create a SimpleDateFormat with the desired pattern for Date
            SimpleDateFormat DateOnly = new SimpleDateFormat("dd_MMMM_yyyy");
            // Format the current Date using the SimpleDateFormat
            String formattedDate = DateOnly.format(currentDate);
            // Create a folder with the current Date as its name
            path = path+ formattedDate + "\\";

            try {
                dateFolderSuccess = new File(path).mkdir();
                scrnShotFldrNotAvailable = false;
            }catch(Exception e){
                System.out.println("Current Date folder already available");
            }

            System.out.println("Date folder Name :- " + formattedDate);
        }
        // Create a SimpleDateFormat with the desired pattern for time
        SimpleDateFormat sdf = new SimpleDateFormat("hh_mm_ss a");
        // Format the current time using the SimpleDateFormat
        String formattedTime = sdf.format(currentDate);
        // Specify the path where you want to create the folder
        scrnShotFolderPath = path+formattedTime;

        timeFolderSuccess= new File(scrnShotFolderPath).mkdir();
        System.out.println("Time folder Name :- "+formattedTime);






        if (dateFolderSuccess && timeFolderSuccess) {
            System.out.println("Folder '" + path  + "' and '" +scrnShotFolderPath+"' created successfully.");
        } else {
            System.err.println("Failed to create folder '" + path + "'.");
        }
    }

}
