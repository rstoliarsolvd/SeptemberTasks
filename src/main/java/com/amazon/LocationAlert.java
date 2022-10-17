package com.amazon;

import com.amazon.constants.Const;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationAlert extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(LocationAlert.class);

    @FindBy(css = ".a-button.a-spacing-top-base.a-button-base.glow-toaster-button.glow-toaster-button-dismiss")
    private WebElement dismissPopUp;

    @FindBy(css = ".a-section.glow-toaster.glow-toaster-theme-default.glow-toaster-slot-default.nav-coreFlyout.nav-flyout")
    private WebElement locationPopUp;

    public LocationAlert(RemoteWebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void verifyAlert(){
        if(!isLocAlertOpen()) {
            LOGGER.info("Location Alert closed");
        }else {
            closeLocPopUp();
        }
    }

    public boolean isLocAlertOpen(){
        LOGGER.info("Verifying ifLocation alert is Present. If Yes - then close it ");
        return driver.findElements(By.cssSelector(Const.LOCATION_POP_UP_CSS_LOCATOR)).size() > 0;
    }

    public void closeLocPopUp(){
        clickButton(dismissPopUp,"dismissPopUp");
        if(!isLocAlertOpen()){
            LOGGER.info("Location Alert closed");
        }
    }
}
