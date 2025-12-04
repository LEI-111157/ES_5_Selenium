package iscteiul.ista.es_5;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuthTest {

    private WebDriver driver;
    private BasicAuthPage basicAuthPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        basicAuthPage = new BasicAuthPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void basicAuthComSucesso() {
        basicAuthPage.openWithValidCredentials();
        assertTrue(basicAuthPage.getMessage().contains("Congratulations"));
    }
}
