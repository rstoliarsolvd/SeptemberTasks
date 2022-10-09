package com.amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SignInFormPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(SignInFormPage.class);

    @FindBy(xpath = "//h1")
    private WebElement header;

    @FindBy(xpath = "//a[@class='a-link-nav-icon']")
    private WebElement homeBtn;

    @FindBy(xpath = "//*[@aria-label='Amazon']")
    private WebElement homeBtn1;

    public SignInFormPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderSignIn() {
        LOGGER.info("Verifying the header of window have title - 'Sign in'");
        String titleName = "Sign in";
        String headerText = header.getText();
        return headerText.equals(titleName);
    }

    public void clickHomeBtn() {
        LOGGER.info("click Home-Btn");
        if (homeBtn.isDisplayed()) {
            clickButton(homeBtn, "homeBtn");
        } else if (homeBtn1.isDisplayed()) {
            clickButton(homeBtn1, "homeBtn1");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
