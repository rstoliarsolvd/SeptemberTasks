package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FilterResultPage {
    WebDriver driver;

    @FindBy(xpath = "//*[text()='Smart Pet | Smart Home']")
    WebElement titleSmartPet;

    @FindBy(xpath = "//*[contains(@class,'a-carousel-row-inner')]//*[contains(@class,'a-truncate-cut')]")
    List<WebElement> goodsOfFilter;

    public FilterResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyTitleOnFilterResultPage() {
        boolean isTitleSmartPetDisplayed = titleSmartPet.isDisplayed();
        Assert.assertTrue(isTitleSmartPetDisplayed);
    }

    public void verifyGoodsAreOk() {
        SoftAssert softAssert = new SoftAssert();
        String pet = "pet";
        List<String> goodsText = goodsOfFilter.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        boolean a = false;
        for (String s : goodsText) {
            if (s.toLowerCase(Locale.ROOT).contains(pet)) {
                a = true;
            }
            softAssert.assertTrue(a);
        }
        softAssert.assertAll();
    }
}
