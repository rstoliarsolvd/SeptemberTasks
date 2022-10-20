package com.amazon.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuTab extends AbstractPage {

    @FindBy(xpath = "//*[@id='nav-xshop']//a[1]")
    private ExtendedWebElement todaysDealsBtn;

    @FindBy(xpath = "//*[@class='hm-icon nav-sprite']")
    private ExtendedWebElement filterMenuBtn;

    public MenuTab(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public FilterMenuPage clickFilterMenuBtn() {
        assertElementPresent(filterMenuBtn);
        filterMenuBtn.click();
        return new FilterMenuPage((RemoteWebDriver) driver);
//        clickButton(filterMenuBtn, "filterMenuBtn");
//        return new FilterMenuPage(driver);
    }

//    public TodaysDealPage clickTodaysDealsBtn() {
//        clickButton(todaysDealsBtn, "todaysDealsBtn");
//        return new TodaysDealPage(driver);
//    }
    public TodaysDealPage clickTodaysDealsBtn() {
        assertElementPresent(todaysDealsBtn);
        todaysDealsBtn.click();
        return new TodaysDealPage((RemoteWebDriver) driver);
    }
}
