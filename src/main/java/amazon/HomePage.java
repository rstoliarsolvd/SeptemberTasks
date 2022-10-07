package amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);


    @FindBy(id = "nav-link-accountList-nav-line-1")
    WebElement signInBtn;

    @FindBy(xpath = "//*[@id='twotabsearchtextbox']")
    WebElement searchField;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchIcon;

    @FindBy(xpath = "//*[@id='lZfD2qd0cC-at_egUpYZJA']/span")
    WebElement homePageTab;

    @FindBy(xpath = "//*[@id='nav-xshop']//a[1]")
    WebElement todaysDealsBtn;

    @FindBy(xpath = "//*[contains(text(),'Don')]")
    WebElement popUpLocClose;

    @FindBy(xpath = "//*[@class='hm-icon nav-sprite']")
    WebElement filterMenuBtn;

    @FindBy(css = ".a-section.glow-toaster.glow-toaster-theme-default.glow-toaster-slot-default.nav-coreFlyout.nav-flyout")
    WebElement locationPopUp;

    @FindBy(css = ".a-button.a-spacing-top-base.a-button-base.glow-toaster-button.glow-toaster-button-dismiss")
    WebElement dismissPopUp;

    private String searchItem = "iphone 11 case";
    private String homeLogoURL = "https://www.amazon.com/ref=nav_logo";
    private String homeURL = "https://www.amazon.com/";


//    public HomePage(WebDriver driver) {
    public HomePage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn() {
        LOGGER.info("click SignIn Btn");
        clickButton(signInBtn);
    }

    public void clickSearchField() {
        clickButton(searchField);
    }

    public void inputTextInSearchField() {
        searchField.sendKeys(searchItem, Keys.ENTER);
        LOGGER.info("key "+ searchItem + " is inputed in search field and entered");
    }

    public void verifyHomePageIsOpen() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        LOGGER.info("Verifying Home-page is opened");
        boolean a = driver.getCurrentUrl().equals(homeURL) || driver.getCurrentUrl().equals(homeLogoURL);
        Assert.assertTrue(a,"Home page is not open");
    }

    public void clickTodaysDealsBtn() {
        LOGGER.info("Today's Deals btn click");
        clickButton(todaysDealsBtn);
    }

    public void closeLocationPopUpIfPresence() {
        LOGGER.info("Verifying ifLocation alert is Present. If Yes - then close it ");

        String locationPopUpCSSLocator = ".a-section.glow-toaster.glow-toaster-theme-default.glow-toaster-slot-default.nav-coreFlyout.nav-flyout";
        boolean isPresent = driver.findElements(By.cssSelector(locationPopUpCSSLocator)).size() > 0;
        if (isPresent) {
            LocationPopUpPage locationPopUpPage = new LocationPopUpPage(driver);
            locationPopUpPage.closeLocPopUp();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            LOGGER.info("Location Alert closed");
        }
    }

    public void clickFilterMenuBtn() {
        clickButton(filterMenuBtn);
    }
}
