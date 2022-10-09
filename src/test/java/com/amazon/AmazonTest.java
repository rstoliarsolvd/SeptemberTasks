package com.amazon;

import com.amazon.services.LocationAlert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmazonTest extends AbstractTest {

    @Test
    public void verifySignInFormAppearedTest() {
        UpTab upTab = new UpTab(driver);
        SignInFormPage signInFormPage = upTab.clickSignInBtn();
        Assert.assertTrue(signInFormPage.isHeaderSignIn(), "Header on opened page is not 'Sign in'");
        signInFormPage.clickHomeBtn();
    }

    @DataProvider(name = "searchItem")
    public Object[][]searchItem (){
        return new Object[][] {{"iphone 11 case"}};
    }

    @Test(dataProvider = "searchItem")
    public void verifySearchFieldAndHomeBtn(String searchItem ) {
        UpTab upTab = new UpTab(driver);

        ResultsPage resultsPage = upTab.findItem(searchItem);
        Assert.assertTrue(resultsPage.areTitlesContainsItem(searchItem), "Not all goods titles contains searched items");
        HomePage homePage = upTab.goHome(driver);
        Assert.assertTrue(homePage.isHomePageOpen(), "Home page is not opened");
    }

    //    @Test(retryAnalyzer = Retry.class)
    @Test
    public void verifyTodayDealsOption() {
        LocationAlert lAlert = new LocationAlert(driver);
        lAlert.verifyAlert();
        MenuTab menuTab = new MenuTab(driver);
        TodaysDealPage todaysDealPage = menuTab.clickTodaysDealsBtn();
        Assert.assertTrue(todaysDealPage.ifTDPageIsOpen(), "No Today's Deals page is open");
        Assert.assertTrue(todaysDealPage.areGoodsHaveDiscount(), "Not All goods have discounts");
            menuTab.goHome(driver);
    }

    @Test
    public void verifyFilterTest() {
        String pet = "pet";
        MenuTab menuTab = new MenuTab(driver);
        FilterMenuPage filterMenuPage = menuTab.clickFilterMenuBtn();

        filterMenuPage = filterMenuPage.clickSmartHomeBtn();
        Assert.assertTrue(filterMenuPage.isFMPageOpen(),"Filter menu page is not open");
        Assert.assertTrue(filterMenuPage.isSmartTitlePresent(),"Filter menu page is not open");

        FilterResultPage filterResultPage = filterMenuPage.clickPetBtn();
        Assert.assertTrue(filterResultPage.isTitleOnFilterResultPageWithPet(), " No 'Pet' title is displayed");
        Assert.assertTrue(filterResultPage.areAllGoodsTitleContainsSearchItem(pet),"No 'Pet' in title on filter result page present");
        filterResultPage.goHome(driver);
    }

    @Test
    public void verifyFilterMenuCloseBtn() {
        MenuTab menuTab = new MenuTab(driver);
        FilterMenuPage filterMenuPage = menuTab.clickFilterMenuBtn();
       Assert.assertTrue(filterMenuPage.isFMPageOpen(),"Filter menu page is not open");
        HomePage homePage = filterMenuPage.clickCloseBtn();
        Assert.assertTrue(homePage.isHomePageOpen(),"Home page is not open");
    }

}
