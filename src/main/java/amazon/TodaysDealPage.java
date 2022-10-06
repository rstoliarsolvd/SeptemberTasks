package amazon;

import org.apache.log4j.Logger;
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

public class TodaysDealPage extends AbstractPage{
    private static final Logger LOGGER = Logger.getLogger(TodaysDealPage.class);


    @FindBy(xpath = "//h1")
    WebElement title;

    @FindBy(xpath = "//div[@class='Grid-module__gridDisplayGrid_2X7cDTY7pjoTwwvSRQbt9Y']//div//div//div//div//div//div[@style='color: rgb(204, 12, 57);']")
    List<WebElement> discountGoods;

    @FindBy(id = "nav-logo-sprites")
    WebElement homeBtn;

    public TodaysDealPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyTDPageIsOpen() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(title.getText().toLowerCase(Locale.ROOT).contains("deals"));
    }

    public void verifyAllGoodsHaveDiscount() {
        LOGGER.info("Verifying that all goods have at least one feature of discount");
        SoftAssert softAssert = new SoftAssert();
        List<String> discGoods = discountGoods.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> discounts = new ArrayList<>(Arrays.asList("up", "%", "off", "under", "-"));
        for (int i = 0; i < 8; i++) {
            boolean haveDiscount = false;
            for (int j = 0; j < discounts.size(); j++) {
                String a = discGoods.get(i).toLowerCase(Locale.ROOT);
                String signdisc = discounts.get(j);
                if (!a.contains(signdisc)) {
                    continue;
                } else {
                    haveDiscount = true;
            }
            softAssert.assertTrue(haveDiscount,"No discount on goods");
        }
    }
        softAssert.assertAll();
}

    public void clickHomeBtn(){
        clickButton(homeBtn);
        LOGGER.info("Home btn is clicked");
    }
}
