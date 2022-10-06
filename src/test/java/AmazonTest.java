import amazon.*;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AmazonTest {
    WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger(AmazonTest.class);
    private static final String SITEURL = "https://www.amazon.com/";
    HomePage homePage;
    SignInFormPage signInFormPage;
    ResultsPage resultsPage;
    TodaysDealPage todaysDealPage;
    FilterMenuPage filterMenuPage;
    FilterResultPage filterResultPage;

    @BeforeTest

    public void setup() {
        String log4jConfPath = "src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        System.setProperty("webdriver.chrome.driver", "/Users/rstoliar/Downloads/chromedriver 2");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(SITEURL);
        LOGGER.info("Start tests");
    }

    @Test
    public void verifySignInFormAppearedTest() {
        homePage = new HomePage(driver);
        signInFormPage = new SignInFormPage(driver);
        homePage.clickSignInBtn();
        signInFormPage.verifyHeader();
        signInFormPage.clickHomeBtn();
    }

    @Test
    public void verifySearchFieldAndHomeBtn() {
        homePage = new HomePage(driver);
        homePage.clickSearchField();
        homePage.inputTextInSearchField();
        resultsPage = new ResultsPage(driver);
        resultsPage.verifyItemPresent();
        resultsPage.clickHomeBtn();
        homePage.verifyHomePageIsOpen();
    }

//    @Test(retryAnalyzer = Retry.class)
    @Test
    public void verifyTodayDealsOption() {
        homePage = new HomePage(driver);
        homePage.closeLocationPopUpIfPresence();
        homePage.clickTodaysDealsBtn();
        todaysDealPage = new TodaysDealPage(driver);
        todaysDealPage.verifyTDPageIsOpen();
        todaysDealPage.verifyAllGoodsHaveDiscount();
        todaysDealPage.clickHomeBtn();
    }

    @Test
    public void verifyFilterTest() {
        homePage = new HomePage(driver);
        homePage.clickFilterMenuBtn();
        filterMenuPage = new FilterMenuPage(driver);
        filterMenuPage.clickSmartHomeBtn();
        filterMenuPage.verifyFilterMenuCloseBtnPresent();
        filterMenuPage.verifySmartTitlePresent();
        filterMenuPage.clickPetBtn();
        filterResultPage = new FilterResultPage(driver);
        filterResultPage.verifyTitleOnFilterResultPage();
        filterResultPage.verifyGoodsAreOk();
        filterResultPage.clickHomeBtn();
    }

    @Test
    public void verifyFilterMenuCloseBtn() {
        homePage = new HomePage(driver);
        homePage.clickFilterMenuBtn();
        filterMenuPage = new FilterMenuPage(driver);
        filterMenuPage.verifyFilterMenuCloseBtnPresent();
        filterMenuPage.clickCloseBtn();
        homePage = new HomePage(driver);
        homePage.verifyHomePageIsOpen();
    }

    @AfterTest
    public void setUpClose() {
        LOGGER.info("Tests finished");
        driver.quit();
    }
}
