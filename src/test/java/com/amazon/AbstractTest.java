package com.amazon;

import com.amazon.constants.Const;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AbstractTest {

    public RemoteWebDriver driver = null;  //For Selenium Standalone server

//    WebDriver driver;  //For Selenium server

    private static final Logger LOGGER = Logger.getLogger(AmazonTest.class);
//    private static final String SITEURL = "https://www.amazon.com/";

    HomePage homePage;
    SignInFormPage signInFormPage;
    ResultsPage resultsPage;
    TodaysDealPage todaysDealPage;
    FilterMenuPage filterMenuPage;
    FilterResultPage filterResultPage;

    @BeforeTest

    public void setup() {
        PropertyConfigurator.configure(Const.LOG4J_CONF_PATH);

//For Selenium Standalone server
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        try {
            driver = new RemoteWebDriver(new URL(Const.LOCAL_HOST), options);
        } catch (MalformedURLException e) {
            LOGGER.info("Invalid grid URL");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }

//For Selenium Standalone server and for Selenium  server
//        System.setProperty("webdriver.chrome.driver", "/Users/rstoliar/Downloads/chromedriver 2");
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Const.HOME_URL);
        LOGGER.info("Start tests");
    }

    @AfterTest
    public void setUpClose() {
        LOGGER.info("Tests finished");
        driver.quit();
    }
}
