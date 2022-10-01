package amazon;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInFormPage {
    WebDriver driver;

    @FindBy(xpath = "//h1")
    WebElement header;

    public SignInFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyHeader(){
        String titleName = "Sign in";
        String headerText = header.getText();
        assertEquals(titleName, headerText);
    }
}
