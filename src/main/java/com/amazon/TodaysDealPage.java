package com.amazon;

import com.amazon.services.CheckMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TodaysDealPage extends AbstractPage{

    private static final Logger LOGGER = Logger.getLogger(TodaysDealPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//*[@class='Grid-module__gridDisplayGrid_2X7cDTY7pjoTwwvSRQbt9Y']//div[contains(@class,'DealGridItem-module__withoutActionButton_2OI8DAanWNRCagYDL2iIqN')]")
    private List<WebElement> discountGoods;

    public TodaysDealPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean ifTDPageIsOpen() {
        String titleTrue = "deals";
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(title));
        return title.getText().toLowerCase(Locale.ROOT).contains(titleTrue);
    }

    public List<String> goodsTitleDiscountsList(){
        return   discountGoods.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean areGoodsHaveDiscount(){
        LOGGER.info("Verifying that all goods have at least one feature of discount");
        List<String> discGoods = goodsTitleDiscountsList();
        List<String> discounts = new ArrayList<>(Arrays.asList("up", "%", "off", "under", "-"));

        return CheckMethods.isElementsPresentInList(discGoods,8,discounts);
    }

}