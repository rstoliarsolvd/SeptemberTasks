package amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ResultsPage extends AbstractPage{
    private static final Logger LOGGER = Logger.getLogger(ResultsPage.class);

    @FindBy(xpath = "//*[contains(@class, 's-main-slot')]//*[contains(@class, 's-title-instructions-style')]")
    List<WebElement> goodsResult;

    @FindBy(id = "nav-logo-sprites")
    WebElement homeBtn;

    public ResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyItemPresent() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(goodsResult.get(1)));
        String s1 = "iphone";
        String s2 = "11";
        String s3 = "case";
        LOGGER.info("Verifying in title of all goods Present " + s1 + s2 + s3);
        String[] str = {s1, s2, s3};
        SoftAssert softAssert = new SoftAssert();
        List<String> titles = goodsResult.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        for (String s : titles) {
            for (int i = 0; i < str.length; i++) {
                softAssert.assertTrue(s.toLowerCase(Locale.ROOT).contains(str[i].toLowerCase(Locale.ROOT)),
                        "Not All goods have " +s1+ ", " + s2 + ", " + s3 + " in their name");
            }
        }
        softAssert.assertAll();
    }

    public void clickHomeBtn(){
        clickButton(homeBtn);
        LOGGER.info("Click Home - btn");
    }
}
