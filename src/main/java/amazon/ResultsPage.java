package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ResultsPage {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 's-main-slot')]//*[contains(@class, 's-title-instructions-style')]")
    List<WebElement> goodsResult;

    @FindBy(id = "nav-logo-sprites")
    WebElement homeBtn;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyItemPresent() {
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        String s1 = "iphone";
        String s2 = "11";
        String s3 = "case";
        String[] str = {s1, s2, s3};
        SoftAssert softAssert = new SoftAssert();
        List<String> titles = goodsResult.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        for (String s : titles) {
            for (int i = 0; i < str.length; i++) {
                softAssert.assertTrue(s.toLowerCase(Locale.ROOT).contains(str[i].toLowerCase(Locale.ROOT)));
            }
        }
        softAssert.assertAll();
    }
    public void clickHomeBtn(){
        homeBtn.click();
    }
}
