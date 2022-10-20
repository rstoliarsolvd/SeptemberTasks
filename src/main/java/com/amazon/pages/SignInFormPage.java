package com.amazon.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SignInFormPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(SignInFormPage.class);

    @FindBy(xpath = "//h1")
    private ExtendedWebElement header;

    @FindBy(xpath = "//a[@class='a-link-nav-icon']")
    private ExtendedWebElement homeBtn;

    @FindBy(xpath = "//*[@aria-label='Amazon']")
    private ExtendedWebElement homeBtn1;

    public SignInFormPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderSignIn() {
        LOGGER.info("Verifying the header of window have title - 'Sign in'");
        String titleName = "Sign in";
        String headerText = header.getText();//гет Текст можно в абстракт и снабдить логгером
        return headerText.equals(titleName);//результат в логер . и константу вынести. что реалоьно в хедере
    }

    public void clickHomeBtn() {
        LOGGER.info("click Home-Btn");
        if (homeBtn.isElementPresent()) {
            homeBtn.click();//использовать другй клик, как везде
        } else if (homeBtn1.isElementPresent()) {
            homeBtn1.click();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//сменить на динамический
    }
}
