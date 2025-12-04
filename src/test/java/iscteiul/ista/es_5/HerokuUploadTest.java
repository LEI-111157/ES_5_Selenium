package iscteiul.ista.es_5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HerokuUploadTest {
    private WebDriver driver;
    private HerokuUploadPage uploadPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/upload");
        uploadPage = new HerokuUploadPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void fileUploadTest() {
        // Caminho para o ficheiro que criaste na pasta resources
        File file = new File("src/test/resources/ficheiro_teste.txt");

        // Verificação de segurança
        assertTrue(file.exists(), "O ficheiro_teste.txt não está em src/test/resources! Cria-o primeiro.");

        String absolutePath = file.getAbsolutePath();

        // Ação
        uploadPage.uploadFile(absolutePath);

        // Validação
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(uploadPage.header, "File Uploaded!"));

        assertEquals("File Uploaded!", uploadPage.header.getText());
        assertTrue(uploadPage.uploadedFilesList.getText().contains("ficheiro_teste.txt"));
    }
}