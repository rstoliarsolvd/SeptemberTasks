package com.amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpTab extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(HomePage.class);

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement signInBtn;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    private WebElement searchField;

//    @FindBy(id = "nav-search-submit-button")
//    private WebElement searchIcon;

//    @FindBy(id = "nav-logo-sprites")
//    private WebElement homeBtn;

    public UpTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInFormPage clickSignInBtn() {
        clickButton(signInBtn, "signInBtn");
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