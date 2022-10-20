package com.amazon.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage1 {

    private static final Logger LOGGER = Logger.getLogger(AbstractPage1.class);

//        public WebDriver driver;
    public RemoteWebDriver driver = null;

//    @FindBy(id = "nav-logo-sprites")
//    private ExtendedWebElement homeBtn;

    public AbstractPage1(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButton(WebElement webElement, String btn) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(webElement)).click();
        LOGGER.info("Element Abstract " + btn + " is clicked");
    }

    public HomePage goHome(RemoteWebDriver driver) {//в отдельный сервис
        UpTab upTab = new UpTab(driver);
        upTab.goHome(driver);
        return new HomePage(driver);
    }
}
