package iscteiul.ista.es_5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Testes de aceitação da página principal da JetBrains
 * Parte 1-A: Projeto piloto com código de demonstração
 */
public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        // Fechar banner de cookies se aparecer
        closeCookiesIfPresent();

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Fecha o banner de cookies se este estiver presente na página.
     */
    private void closeCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Localizar o container do banner de cookies
            WebElement banner = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.ch2-container")
                    )
            );

            // Clicar no botão de aceitar (primeiro botão dentro do banner)
            WebElement acceptButton = banner.findElement(By.tagName("button"));
            acceptButton.click();
            System.out.println("✓ Banner de cookies fechado com sucesso");

            // Aguardar que o banner desapareça
            wait.until(ExpectedConditions.invisibilityOf(banner));

        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("ℹ Banner de cookies não encontrado (pode já estar aceite)");
        }
    }

    /**
     * Teste 1: Funcionalidade de pesquisa
     * Valida que é possível pesquisar por "Selenium" através do campo de pesquisa do header
     */
    @Test
    public void search() throws InterruptedException {
        System.out.println("\n=== Teste: Pesquisa ===");

        // Pausa para observar a página inicial
        Thread.sleep(2000);

        System.out.println("→ Clicando no botão de pesquisa...");
        mainPage.searchButton.click();
        Thread.sleep(1000);

        System.out.println("→ Localizando campo de pesquisa...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchField = wait.until(d -> {
            WebElement el = d.switchTo().activeElement();
            return "input".equalsIgnoreCase(el.getTagName()) ? el : null;
        });

        System.out.println("→ Escrevendo 'Selenium' no campo...");
        searchField.clear();
        searchField.sendKeys("Selenium");
        Thread.sleep(500);

        System.out.println("→ Validando valor do campo...");
        assertEquals("Selenium", searchField.getAttribute("value"));
        System.out.println("✓ Teste de pesquisa passou!");
    }

    /**
     * Teste 2: Menu de ferramentas
     * Valida que o menu Tools é clicável e responde à interação
     */
    @Test
    public void toolsMenu() throws InterruptedException {
        System.out.println("\n=== Teste: Menu Tools ===");

        // Pausa para observar a página inicial
        Thread.sleep(2000);

        // 1. Clicar no menu Tools
        System.out.println("→ Clicando no menu Tools...");
        mainPage.toolsMenu.click();
        Thread.sleep(1000);

        // 2. Validar que o menu permanece visível após interação
        System.out.println("→ Validando visibilidade do menu...");
        assertTrue(mainPage.toolsMenu.isDisplayed(),
                "O menu Tools deveria estar visível");
        System.out.println("✓ Teste do menu Tools passou!");
    }

    /**
     * Teste 3: Navegação para a página de produtos
     * Valida o fluxo completo de navegação desde a homepage até à página /products
     */
    @Test
    public void navigationToAllTools() throws InterruptedException {
        System.out.println("\n=== Teste: Navegação para produtos ===");

        // Pausa para observar a homepage
        Thread.sleep(2000);

        // 1. Clicar no botão "Developer Tools"
        System.out.println("→ Clicando em Developer Tools...");
        mainPage.seeDeveloperToolsButton.click();
        Thread.sleep(1000);

        // 2. Clicar no link que leva à página de produtos
        System.out.println("→ Procurando link para página de produtos...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[data-test='suggestion-link']")
                )
        );

        System.out.println("→ Navegando para /products...");
        productsLink.click();

        // 3. Aguardar que a URL contenha "/products"
        wait.until(ExpectedConditions.urlContains("/products"));
        Thread.sleep(1000);

        // 4. Validar a navegação
        System.out.println("→ Validando URL final...");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/products"),
                "A URL deveria conter '/products'");
        System.out.println("✓ URL atual: " + currentUrl);
        System.out.println("✓ Teste de navegação passou!");
    }
}
