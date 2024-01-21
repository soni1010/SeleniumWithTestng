package org.harender.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class ReusableMethods {

    private static WebDriver driver;
    private static boolean scrnShotFldrNotAvailable=true;

    // Specify the path where you want to create the folder
    private static String path = "C:\\Users\\User\\Documents\\GitHub\\SeleniumWithTestng\\ScreenShotsDirectory\\";
    private static String scrnShotFolderPath;


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
