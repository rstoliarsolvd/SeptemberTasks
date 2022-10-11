package com.amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(AbstractPage.class);

    //    WebDriver driver;
    public RemoteWebDriver driver = null;

    @FindBy(id = "nav-logo-sprites")
    private WebElement homeBtn;

    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButton(WebElement webElement, String btn) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(webElement)).click();
        LOGGER.info("Element Abstract " + btn + " is clicked");
    }

    public HomePage goHome(RemoteWebDriver driver) {
        UpTab upTab = new UpTab(driver);
        upTab.clickButton(homeBtn, "homeBtn");
        return new HomePage(driver);
    }




}
