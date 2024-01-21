package org.harender.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.harender.utils.ReusableMethods;

@CucumberOptions(tags = "", features = "src/test/resources/features/loginTest.feature", glue = "org.harender.definitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}
