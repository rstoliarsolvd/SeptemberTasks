package com.amazon;

import com.amazon.constants.Const;
import com.amazon.services.CheckMethods;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AbstractTest {

    /**
     * For Selenium Standalone server //for parallel ran (multiThread)
     */
    protected static ThreadLocal<RemoteWebDriver> driverT = new ThreadLocal<>();

    /**
     * For Selenium server
     */
//    public WebDriver driver;

    private static final Logger LOGGER = Logger.getLogger(AmazonTest.class);

    @Parameters({"browser"})
    @BeforeMethod
    protected static void setupDriver(String browser) throws MalformedURLException {

        PropertyConfigurator.configure(Const.LOG4J_CONF_PATH);

/**
 * For Selenium Standalone server
 */
        RemoteWebDriver driver = null;
        DesiredCapabilities cap = new DesiredCapabilities();

        if (browser.equals("chrome")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(cap);
        } else if (browser.equals("firefox")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("firefox");
            FirefoxOptions options = new FirefoxOptions();
            options.merge(cap);
        } else if (browser.equals("safari")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("safari");
            SafariOptions options = new SafariOptions();
            options.merge(cap);
        } else {
            System.out.println("Nothing");
        }
        driver = new RemoteWebDriver(new URL(Const.LOCAL_HOST), cap);

/**
 * For Selenium Standalone server and for Selenium  server
 */
//        System.setProperty("webdriver.chrome.driver", "/Users/rstoliar/IdeaProjects/chromedriver");
//        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Const.HOME_URL);
        LOGGER.info("Start tests");
        driverT.set(driver);
    }

    @AfterMethod
    public void setUpClose() {
        LOGGER.info("Tests finished");
        if (driverT.get() != null) {
            driverT.get().quit();
            driverT.set(null);
        }
    }

    public void refreshPageIfWrongDesign(RemoteWebDriver driver) {
        CheckMethods checkMethods = new CheckMethods(driver);
        if (checkMethods.isWrongDesign()) {
            driver.navigate().refresh();
        }
    }
}
