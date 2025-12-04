package iscteiul.ista.es_5;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FormAuthTest {
    private WebDriver driver;
    private FormAuthPage formPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        formPage = new FormAuthPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginComSucesso() {
        formPage.open();
        formPage.login("tomsmith", "SuperSecretPassword!");

        assertTrue(formPage.getFlashText().contains("You logged into a secure area!"));
    }

    @Test
    void loginInvalido() {
        formPage.open();
        formPage.login("userErrado", "passErrada");

        assertTrue(formPage.getFlashText().contains("Your username is invalid!"));
    }
}
