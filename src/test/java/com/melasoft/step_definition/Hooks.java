package com.melasoft.step_definition;

import com.melasoft.utilities.BrowserUtils;
import com.melasoft.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    //import the @Before coming from io.cucumber.java
    @Before()
    public void setupMethod(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    /*
    @After will be executed automatically after EVERY scenario in the project.
     */
    @After
    public void teardownMethod(Scenario scenario){

        if (scenario.isFailed()) {

            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

        }

        BrowserUtils.sleep(2);
      //  Driver.closeDriver();

    }

}
