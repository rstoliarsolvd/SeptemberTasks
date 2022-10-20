package com.amazon.pages;

import com.amazon.services.CheckMethods;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends AbstractPage {

    private static final Logger LOGGER = Logger.getLogger(ResultsPage.class);

    @FindBy(xpath = "//*[contains(@class, 's-main-slot')]//*[contains(@class, 's-title-instructions-style')]")
    private List<ExtendedWebElement> goodsResult;

    public ResultsPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> goodsTitles() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf((WebElement) goodsResult.get(1)));

        List<String> titles = goodsResult.stream()
                .map(ExtendedWebElement::getText)
                .collect(Collectors.toList());
        return titles;
    }

    public boolean areTitlesContainsItem(String input) {
        String [] str = input.split(" ");
        LOGGER.info("Verifying in title of all goods Present -- " + input);
        List<String> titles = goodsTitles();
        return  CheckMethods.areAllElementsContainAllElements(titles,str);
    }
}
