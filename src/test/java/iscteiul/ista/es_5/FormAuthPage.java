package iscteiul.ista.es_5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormAuthPage {

    private WebDriver driver;

    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    @FindBy(id = "flash")
    public WebElement flashMessage;

    public FormAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void login(String user, String pass) {
        usernameInput.clear();
        usernameInput.sendKeys(user);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        loginButton.click();
    }

    public String getFlashText() {
        return flashMessage.getText();
    }
}
