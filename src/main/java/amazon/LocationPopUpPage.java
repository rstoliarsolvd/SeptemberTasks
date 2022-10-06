package amazon;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationPopUpPage extends AbstractPage{
    private static final Logger LOGGER = Logger.getLogger(LocationPopUpPage.class);

@FindBy(css = ".a-button.a-spacing-top-base.a-button-base.glow-toaster-button.glow-toaster-button-dismiss")
    WebElement dismissPopUp;

    public LocationPopUpPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void closeLocPopUp(){
        LOGGER.info("Location alert closing");
        clickButton(dismissPopUp);
    }
}
