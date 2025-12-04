package iscteiul.ista.es_5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasicAuthPage {

    private WebDriver driver;

    @FindBy(css = "p")
    public WebElement successText;

    public BasicAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openWithValidCredentials() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
    }

    public String getMessage() {
        return successText.getText();
    }
}
