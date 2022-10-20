package com.amazon.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FilterMenuPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(FilterMenuPage.class);

    @FindBy(xpath = "//div[text()='Smart Home']")
    private ExtendedWebElement smartHomeBtn;

    @FindBy(xpath = "//a[@class ='hmenu-item'][text()='Pet']")
    private ExtendedWebElement petBtn;

    @FindBy(xpath = "//div[@class ='nav-sprite hmenu-close-icon']")
    private ExtendedWebElement closeFilterMenuBtn;

//    @FindBy(xpath = "//*[@id='hmenu-content']")
    @FindBy(xpath = "//ul[@class='hmenu hmenu-visible']")
    private ExtendedWebElement filterBlock;

    @FindBy(xpath = "//*[@id='hmenu-content']//following::div[contains(text(),'smart home')]")
    private ExtendedWebElement smartHomeTitle;

    @FindBy(xpath = "//*[text()='Smart Pet | Smart Home']")
    private ExtendedWebElement titleSmartPet;

    public FilterMenuPage(RemoteWebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public FilterMenuPage clickSmartHomeBtn(){
        assertElementPresent(smartHomeBtn);
        smartHomeBtn.click();

        waitForJSToLoad();
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(smartHomeTitle));.visibilityOfElementLocated(smartHomeTitle));
        return new FilterMenuPage((RemoteWebDriver) driver);
    }

    public FilterResultPage clickPetBtn(){
        assertElementPresent(petBtn);
        petBtn.click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf((WebElement) titleSmartPet));
        return new FilterResultPage((RemoteWebDriver) driver);
    }

//    public FilterResultPage clickPetBtn(){
//        clickButton(petBtn,"petBtn");
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(titleSmartPet));
//        return new FilterResultPage(driver);
//    }

    public boolean isFMPageOpen(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf((WebElement) closeFilterMenuBtn));
        return closeFilterMenuBtn.isElementPresent() || filterBlock.isElementPresent();
    }

//    public boolean isFMPageOpen(){
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(closeFilterMenuBtn));
//        return closeFilterMenuBtn.isDisplayed() || filterBlock.isDisplayed();
//    }

    public boolean isSmartTitlePresent(){
        LOGGER.info("Verifying 'Smart Home' Presence on title of page");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf((WebElement) smartHomeTitle));
        return smartHomeTitle.isElementPresent();
    }
//
//    public boolean isSmartTitlePresent(){
//        LOGGER.info("Verifying 'Smart Home' Presence on title of page");
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(smartHomeTitle));
//        return smartHomeTitle.isDisplayed();
//    }

    public HomePage clickCloseBtn(){
        waitForJSToLoad(3);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        closeFilterMenuBtn.clickIfPresent();
        new WebDriverWait(driver, Duration.ofSeconds(9)).until(ExpectedConditions.invisibilityOf((WebElement) closeFilterMenuBtn));
        return new HomePage((RemoteWebDriver) driver);
    }

//    public HomePage clickCloseBtn(){
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        clickButton(closeFilterMenuBtn,"closeFilterMenuBtn");
//        new WebDriverWait(driver, Duration.ofSeconds(9)).until(ExpectedConditions.invisibilityOf(closeFilterMenuBtn));
//        return new HomePage(driver);
//    }
}
