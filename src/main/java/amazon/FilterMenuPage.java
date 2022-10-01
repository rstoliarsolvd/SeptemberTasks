package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class FilterMenuPage {
    WebDriver driver;

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

    public FilterMenuPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickSmartHomeBtn(){
        smartHomeBtn.click();
    }

    public void clickPetBtn(){
        petBtn.click();
    }

    public void verifyFilterMenuCloseBtnPresent(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        boolean isFilterBlockMenuPresent = filterBlock.isDisplayed();
        Assert.assertTrue(isFilterBlockMenuPresent);
    }

    public void verifySmartTitlePresent(){
        boolean isSmartHomeTitleDisplayed = smartHomeTitle.isDisplayed();
        Assert.assertTrue(isSmartHomeTitleDisplayed);
    }

    public void clickCloseBtn(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        closeFilterMenuBtn.click();
    }
}
