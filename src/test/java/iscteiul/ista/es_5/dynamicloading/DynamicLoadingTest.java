package iscteiul.ista.es_5.dynamicloading;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Testes de aceitação para Dynamic Loading
 * Parte 1-B: Automação de testes de elementos de interação - Conteúdo Dinâmico
 */
public class DynamicLoadingTest {

    private WebDriver driver;
    private DynamicLoadingPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        page = new DynamicLoadingPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Teste: Dynamic Loading - Element Hidden
     * Valida que após clicar em "Start", o elemento escondido aparece com a mensagem "Hello World!"
     */
    @Test
    public void testDynamicLoadingElementHidden() throws InterruptedException {
        System.out.println("\n=== Teste: Dynamic Loading - Element Hidden ===");

        // Pausa para observar a página inicial
        Thread.sleep(1000);

        // 1. Clicar no botão Start
        System.out.println("→ Clicando no botão Start...");
        page.startButton.click();

        // 2. Usar estratégia de espera: aguardar que o loading bar desapareça
        System.out.println("→ Aguardando o loading terminar...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(page.loadingBar));

        // 3. Aguardar que o elemento finish fique visível
        wait.until(ExpectedConditions.visibilityOf(page.finishDiv));

        // Pausa para observar o resultado
        Thread.sleep(1000);

        // 4. Validar que a mensagem "Hello World!" aparece
        System.out.println("→ Validando mensagem...");
        String message = page.finishDiv.getText();
        assertEquals("Hello World!", message,
                "A mensagem deveria ser 'Hello World!'");

        System.out.println("✓ Mensagem encontrada: " + message);
        System.out.println("✓ Teste passou!");
    }

    /**
     * Teste: Dynamic Loading - Element Rendered After
     * Valida que após clicar em "Start", o elemento é criado dinamicamente no DOM
     */
    @Test
    public void testDynamicLoadingElementRendered() throws InterruptedException {
        System.out.println("\n=== Teste: Dynamic Loading - Element Rendered ===");

        // Navegar para o Example 2
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        Thread.sleep(1000);

        // 1. Clicar no botão Start
        System.out.println("→ Clicando no botão Start...");
        page.startButton.click();

        // 2. Usar estratégia de espera: aguardar que o loading bar desapareça
        System.out.println("→ Aguardando o loading terminar...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(page.loadingBar));

        // 3. Aguardar que o elemento finish seja CRIADO e fique visível
        // Nota: No Example 2, o elemento não existe inicialmente no DOM
        wait.until(ExpectedConditions.visibilityOf(page.finishDiv));

        // Pausa para observar o resultado
        Thread.sleep(1000);

        // 4. Validar que a mensagem "Hello World!" aparece
        System.out.println("→ Validando mensagem...");
        String message = page.finishDiv.getText();
        assertEquals("Hello World!", message,
                "A mensagem deveria ser 'Hello World!'");

        System.out.println("✓ Mensagem encontrada: " + message);
        System.out.println("✓ Teste passou!");
    }
}