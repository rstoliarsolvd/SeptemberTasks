package com.amazon;

import com.amazon.services.CheckMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FilterResultPage  extends AbstractPage{

    public static final Logger LOGGER = Logger.getLogger(FilterResultPage.class);

    @FindBy(xpath = "//*[text()='Smart Pet | Smart Home']")
    private WebElement titleSmartPet;

    @FindBy(xpath = "//*[contains(@class,'a-carousel-row-inner')]//*[contains(@class,'a-truncate-cut')]")
    private List<WebElement> goodsOfFilter;

    public FilterResultPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isTitleOnFilterResultPageWithPet() {
        LOGGER.info("Verifying if title with 'Smart Home' and 'Pet' Present. ");
        return titleSmartPet.isDisplayed();
    }

    public List<String> getGoodsTitlesList(){
        return goodsOfFilter.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public  boolean areAllGoodsTitleContainsSearchItem(String pet){
        LOGGER.info("Verifying " + pet + " Present in all goods ");
        List<String> goodsText = getGoodsTitlesList();
        return CheckMethods.isStringPresentInList(goodsText,pet);
    }
}
