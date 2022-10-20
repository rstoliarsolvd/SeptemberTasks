package com.amazon.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpTab extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private ExtendedWebElement signInBtn;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    private ExtendedWebElement searchField;

    @FindBy(xpath = "//*[@id='nav-search-submit-button']")
    private ExtendedWebElement searchBtn;

    @FindBy(xpath = "//div[@class='a-box-inner a-padding-extra-large']")
    private ExtendedWebElement signInBlock;

    @FindBy(id = "nav-logo-sprites")
    private ExtendedWebElement homeBtn;

    public UpTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInFormPage clickSignInBtn() {
       signInBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf((WebElement) signInBlock));
        return new SignInFormPage((RemoteWebDriver) driver);
    }

//    public SignInFormPage clickSignInBtn() {
//        clickButton(signInBtn, "signInBtn");
//        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(signInBlock));
//        return new SignInFormPage(driver);
//    }

    public void clickSearchField() {
       searchField.click();
    }

//    public void clickSearchField() {
//        clickButton(searchField,"searchField");
//    }

    public ResultsPage inputTextInSearchField(String searchItem) {
        searchField.type(searchItem);
        searchField.getElement().sendKeys(searchItem,Keys.ENTER);
//        searchField.sendKeys(Keys.valueOf(searchItem));
//        searchBtn.click();

        LOGGER.info("key "+ searchItem + " is inputed in search field and entered");
        return new ResultsPage((RemoteWebDriver) driver);
    }

//    public ResultsPage inputTextInSearchField(String searchItem) {
//        searchField.sendKeys(searchItem, Keys.ENTER);
//        LOGGER.info("key "+ searchItem + " is inputed in search field and entered");
//        return new ResultsPage(driver);
//    }

    public ResultsPage findItem(String searchItem){
        clickSearchField();
       return inputTextInSearchField(searchItem);
    }

//    public ResultsPage findItem(String searchItem){
//        clickSearchField();
//       return inputTextInSearchField(searchItem);
//    }

    public HomePage goHome() {//в отдельный сервис
        homeBtn.click();
        return new HomePage((RemoteWebDriver) driver);
    }
}


//в карине свой метод по драйверу - гетДрайвер
