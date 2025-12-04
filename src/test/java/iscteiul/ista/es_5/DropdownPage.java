package iscteiul.ista.es_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    private final WebDriver driver;

    private final By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/dropdown");
    }

    private Select getSelect() {
        return new Select(driver.findElement(dropdown));
    }

    public void selectByValue(String value) {
        getSelect().selectByValue(value); // "1", "2", ...
    }

    public String getSelectedText() {
        return getSelect().getFirstSelectedOption().getText().trim();
    }
}