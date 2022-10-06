package amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SignInFormPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(SignInFormPage.class);

    @FindBy(xpath = "//h1")
    WebElement header;

    @FindBy(xpath = "//a[@class='a-link-nav-icon']")
    WebElement homeBtn;

    @FindBy(xpath = "//*[@aria-label='Amazon']")
    WebElement homeBtn1;

    public SignInFormPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyHeader() {
        LOGGER.info("Verifying the header of window have title - 'Sign in'");
        String titleName = "Sign in";
        String headerText = header.getText();
        assertEquals(titleName, headerText);
    }

    public void clickHomeBtn() {
        LOGGER.info("click Home-Btn");
        if (homeBtn.isDisplayed()) {
            clickButton(homeBtn);
        } else if (homeBtn1.isDisplayed()) {
            clickButton(homeBtn1);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
