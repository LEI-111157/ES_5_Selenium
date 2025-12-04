package iscteiul.ista.es_5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        // fecha o banner de cookies se aparecer
        closeCookiesIfPresent();

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void closeCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement banner = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.ch2-container")
                    )
            );

            WebElement acceptButton = banner.findElement(By.tagName("button"));
            acceptButton.click();

            wait.until(ExpectedConditions.invisibilityOf(banner));

        } catch (TimeoutException | NoSuchElementException e) {
            // se não aparecer banner, ignoramos
        }
    }

    @Test
    public void search() throws InterruptedException {
        // pausas só para cumprir a ficha (observar o comportamento)
        Thread.sleep(2000); // ver a página inicial (sem banner)

        // 1) clicar no botão de pesquisa no header
        mainPage.searchButton.click();

        Thread.sleep(1000); // ver o campo/overlay de pesquisa aberto

        // 2) obter o campo de pesquisa através do elemento focado
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchField = wait.until(d -> {
            WebElement el = d.switchTo().activeElement();
            return "input".equalsIgnoreCase(el.getTagName()) ? el : null;
        });

        // 3) escrever "Selenium"
        searchField.clear();
        searchField.sendKeys("Selenium");

        Thread.sleep(500); // pequena pausa só para veres

        // 4) validar que o campo ficou com o valor correto
        assertEquals("Selenium", searchField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        // 1) pequena pausa só para veres a página inicial
        Thread.sleep(2000);

        // 2) clicar no menu Tools
        mainPage.toolsMenu.click();

        // 3) pausa para poderes ver o que acontece depois do clique
        Thread.sleep(1000);

        // 4) validação mínima: o botão Tools continua visível/clicável
        assertTrue(mainPage.toolsMenu.isDisplayed());
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        // pequena pausa para veres a homepage
        Thread.sleep(2000);

        // 1) clicar no botão "Developer Tools" (ou semelhante) na página inicial
        mainPage.seeDeveloperToolsButton.click();

        Thread.sleep(1000); // ver a secção intermédia

        // 2) clicar no link visível que leva à página de produtos/tools
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[data-test='suggestion-link']")
                )
        );
        productsLink.click();

        // 3) esperar que a página de produtos carregue
        wait.until(ExpectedConditions.urlContains("/products"));

        Thread.sleep(1000); // observar a página final

        // 4) validação simples sobre a navegação
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/products"));
    }

}
