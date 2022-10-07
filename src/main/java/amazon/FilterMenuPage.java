package amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class FilterMenuPage extends AbstractPage{

    private static final Logger LOGGER = Logger.getLogger(FilterMenuPage.class);


    @FindBy(xpath = "//div[text()='Smart Home']")
    WebElement smartHomeBtn;

    @FindBy(xpath = "//a[@class ='hmenu-item'][text()='Pet']")
    WebElement petBtn;

    @FindBy(xpath = "//div[@class ='nav-sprite hmenu-close-icon']")
    WebElement closeFilterMenuBtn;

    @FindBy(xpath = "//*[@id=\"hmenu-content\"]")
    WebElement filterBlock;

    @FindBy(xpath = "//*[@id=\"hmenu-content\"]/ul[7]/li[2]/div")
    WebElement smartHomeTitle;


    public FilterMenuPage(RemoteWebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void clickSmartHomeBtn(){
        clickButton(smartHomeBtn);
    }

    public void clickPetBtn(){
        clickButton(petBtn);
    }

    public void verifyFilterMenuCloseBtnPresent(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LOGGER.info("Verifying Filter-Menu Close-Btn Presence");
        boolean isFilterBlockMenuPresent = filterBlock.isDisplayed() || smartHomeTitle.isDisplayed();
        Assert.assertTrue(isFilterBlockMenuPresent, " No filter menu is displayed on the screen");
        boolean isCloseFMBtnPresent = closeFilterMenuBtn.isDisplayed();
        Assert.assertTrue(isCloseFMBtnPresent,"No filter-menu close btn present on the screen");
    }

    public void verifySmartTitlePresent(){
        LOGGER.info("Verifying 'Smart Home' Presence on title of page");
        boolean isSmartHomeTitleDisplayed = smartHomeTitle.isDisplayed();
        Assert.assertTrue(isSmartHomeTitleDisplayed, "No 'Smart Home' title is displayed");
    }

    public void clickCloseBtn(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        clickButton(closeFilterMenuBtn);
    }
}
