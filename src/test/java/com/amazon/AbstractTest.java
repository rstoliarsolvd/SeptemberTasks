package com.amazon;

import com.amazon.constants.Const;
import com.amazon.services.CheckMethods;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AbstractTest {

    protected static ThreadLocal <RemoteWebDriver> driverT = new ThreadLocal<>();  //For Selenium Standalone server //for parallel ran (multiThread)

//    WebDriver driver;  //For Selenium server

    private static final Logger LOGGER = Logger.getLogger(AmazonTest.class);

    @BeforeMethod
    protected static void setupDriver() {
        PropertyConfigurator.configure(Const.LOG4J_CONF_PATH);

//For Selenium Standalone server
        RemoteWebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        try {
            driver = new RemoteWebDriver(new URL(Const.LOCAL_HOST), options);
        } catch (MalformedURLException e) {
            LOGGER.info("Invalid grid URL");
            throw new RuntimeException(e);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            throw new RuntimeException(e);
        }

//For Selenium Standalone server and for Selenium  server
//        System.setProperty("webdriver.chrome.driver", "/Users/rstoliar/Downloads/chromedriver 2");
//        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Const.HOME_URL);
        LOGGER.info("Start tests");
        driverT.set(driver);
    }

    @AfterMethod
    public void setUpClose() {
        LOGGER.info("Tests finished");
        if(driverT.get() != null) {
            driverT.get().quit();
            driverT.set(null);
        }
    }

//    public static RemoteWebDriver getDriver(){
//        ChromeOptions options = new ChromeOptions();
//        options.setAcceptInsecureCerts(true);
//        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//        try {
//             driver = new RemoteWebDriver(new URL(Const.LOCAL_HOST), options);
//        } catch (MalformedURLException e) {
//            LOGGER.info("Invalid grid URL");
//        } catch (Exception e) {
//            LOGGER.info(e.getMessage());
//        }
//        return driver;
//    }

    public void refreshPageIfWrongDesign(RemoteWebDriver driver) {
        CheckMethods checkMethods = new CheckMethods(driver);
        if (checkMethods.isWrongDesign()) {
            driver.navigate().refresh();
        }
    }
}
