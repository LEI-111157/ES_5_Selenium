package iscteiul.ista.es_5.dynamicloading;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object para a página Dynamic Loading (Example 1)
 * URL: https://the-internet.herokuapp.com/dynamic_loading/1
 */
public class DynamicLoadingPage {

    // Botão "Start"
    @FindBy(css = "#start button")
    public WebElement startButton;

    // Barra de loading
    @FindBy(id = "loading")
    public WebElement loadingBar;

    // Div com a mensagem final "Hello World!"
    @FindBy(id = "finish")
    public WebElement finishDiv;

    public DynamicLoadingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}