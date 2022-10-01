package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TodaysDealPage {
    WebDriver driver;

    @FindBy(xpath = "//h1")
    WebElement title;

    @FindBy(xpath = "//*[contains(@class, 'Grid-module__gridDisplayGrid_2X7cDTY7pjoTwwvSRQbt9Y')]//*[contains(@class, 'BadgeAutomatedLabelApex-module__light_2LF3B5hTU7wYCGMizFqBvg')]")
    List<WebElement> discountGoods;

    public TodaysDealPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyTDPageIsOpen() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(title.getText().toLowerCase(Locale.ROOT).contains("deals"));
    }

    public void verifyAllGoodsHaveDiscount() {
        SoftAssert softAssert = new SoftAssert();
        List<String> discGoods = discountGoods.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> discounts = new ArrayList<>(Arrays.asList("up", "-", "%", "off", "under"));
        for (int i = 0; i < 8; i++) {
            boolean haveDiscount = false;
            for (int j = 0; j < discounts.size(); j++) {
                String a = discGoods.get(i).toLowerCase(Locale.ROOT);
                if (a.contains(discounts.get(j))) {
                    haveDiscount = true;
                    break;
                }
                softAssert.assertTrue(haveDiscount);
            }
        }
        softAssert.assertAll();
    }
}
