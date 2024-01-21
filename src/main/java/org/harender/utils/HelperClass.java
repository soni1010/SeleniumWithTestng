package org.harender.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class HelperClass {

    private static HelperClass helperClass;
    private static WebDriver driver;
    //private static WebDriverWait wait;
    public final static int TIMEOUT = 15;

    String windowsSize="--start-maximized";
    String infoBars="--disable-infobars";
    String notifications="--disable-notifications";
    String extensionsOnOrOff="--disable-extensions";
    String certificationErrors="--ignore-certificate-errors";

    private HelperClass() {

        driver= ReusableMethods.initializeChromeDriver(windowsSize,infoBars,notifications,extensionsOnOrOff,certificationErrors);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }
    public static void openPage(String url) {
        driver.get(url);
    }
    public static WebDriver getDriver() {
        return driver;
    }
    public static void setUpDriver() {
        if (helperClass==null) helperClass = new HelperClass();
    }
    public static void tearDown() {
        if(driver!=null) {
            driver.close();
            driver.quit();
        }
        helperClass = null;
    }

}
