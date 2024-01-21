package org.harender.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.harender.utils.HelperClass;
import org.harender.utils.ReusableMethods;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Hooks {


    @Before
    public static void setUp() {
        HelperClass.setUpDriver();
    }

    @After
    public static void tearDown(Scenario scenario) {

        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) HelperClass.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        HelperClass.tearDown();
    }

}
