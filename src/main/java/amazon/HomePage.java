package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;

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

    @FindBy(xpath = "//*[@id='nav-main']/div[1]/div/div/div[3]/span[1]/span")
//    @FindBy(xpath = "//*[contains(@class, \"a-button-input\")]/[contains(text(), \"Don't Change\")]")
    WebElement popUpLocClose;

    @FindBy(xpath = "//*[@class='hm-icon nav-sprite']")
    WebElement filterMenuBtn;

    private String searchItem = "iphone 11 case";
    private String homeLogoURL = "https://www.amazon.com/ref=nav_logo";
    private String homeURL = "https://www.amazon.com/";


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignInBtn(){
        signInBtn.click();
    }

    public void clickSearchField(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        searchField.click();
    }

    public void inputTextInSearchField(){
        searchField.sendKeys(searchItem);
    }

    public void clickSearchIcon(){
        searchIcon.click();
    }
    public void verifyHomePageIsOpen(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        Assert.assertTrue(homePageTab.isDisplayed());
        boolean a = driver.getCurrentUrl().equals(homeURL) || driver.getCurrentUrl().equals(homeLogoURL);
        Assert.assertTrue(a);
    }

    public void clickTodaysDealsBtn(){
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        todaysDealsBtn.click();
    }

    public void closeLocationPopUp(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        if(popUpLocClose.isDisplayed()){
        popUpLocClose.click();}
    }

    public  void  clickFilterMenuBtn(){
        filterMenuBtn.click();
    }
}
