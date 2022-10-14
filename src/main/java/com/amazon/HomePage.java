package com.amazon;

import com.amazon.constants.Const;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHomePageOpen() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        LOGGER.info("Verifying Home-page is opened");
        return driver.getCurrentUrl().equals(Const.HOME_URL) || driver.getCurrentUrl().equals(Const.HOME_LOGO_URL);
    }

}
