package com.amazon.services;

import com.amazon.pages.AbstractPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.UpTab;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoTo extends AbstractPage {

    public GoTo(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Back to HomePage
     *
     * @param driver
     * @return
     */
    public static HomePage goHome(RemoteWebDriver driver) {
        UpTab upTab = new UpTab(driver);
        upTab.clickHomeBtn();
        return new HomePage(driver);
    }
}
