package iscteiul.ista.es_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage {

    private final WebDriver driver;

    // localizadores dos dois checkboxes da página
    private final By firstCheckbox  = By.cssSelector("#checkboxes input:nth-of-type(1)");
    private final By secondCheckbox = By.cssSelector("#checkboxes input:nth-of-type(2)");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String baseUrl) {
        driver.get(baseUrl + "/checkboxes");
    }

    public void checkFirst() {
        WebElement cb = driver.findElement(firstCheckbox);
        if (!cb.isSelected()) {
            cb.click();
        }
    }

    public void uncheckFirst() {
        WebElement cb = driver.findElement(firstCheckbox);
        if (cb.isSelected()) {
            cb.click();
        }
    }

    public void checkSecond() {
        WebElement cb = driver.findElement(secondCheckbox);
        if (!cb.isSelected()) {
            cb.click();
        }
    }

    public void uncheckSecond() {
        WebElement cb = driver.findElement(secondCheckbox);
        if (cb.isSelected()) {
            cb.click();
        }
    }

    public boolean isFirstChecked() {
        return driver.findElement(firstCheckbox).isSelected();
    }

    public boolean isSecondChecked() {
        return driver.findElement(secondCheckbox).isSelected();
    }
}
