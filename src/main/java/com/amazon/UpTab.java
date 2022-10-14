package com.amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='a-box-inner a-padding-extra-large']")
    private WebElement signInBlock;

    public UpTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInFormPage clickSignInBtn() {
        clickButton(signInBtn, "signInBtn");
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(signInBlock));
        return new SignInFormPage(driver);
    }

    public void clickSearchField() {
        clickButton(searchField,"searchField");
    }

    public ResultsPage inputTextInSearchField(String searchItem) {
        searchField.sendKeys(searchItem, Keys.ENTER);
        LOGGER.info("key "+ searchItem + " is inputed in search field and entered");
        return new ResultsPage(driver);
    }

    public ResultsPage findItem(String searchItem){
        clickSearchField();
       return inputTextInSearchField(searchItem);
    }
}
