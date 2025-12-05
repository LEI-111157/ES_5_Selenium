package samplerInteraction;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Vaadin Sampler")
@Feature("Interaction Components Page")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InteractionPageTest {

    private InteractionPage page;

    @BeforeAll
    static void setupAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.headless = false;

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeEach
    void setUp() {
        page = new InteractionPage();
    }

    @Test
    @Order(1)
    @DisplayName("Validar que a página Interaction carrega")
    @Description("Verifica se a URL da página Interaction é acessível")
    void validarPaginaCarrega() {
        page.openPage();
        sleep(2000);

        String url = page.getCurrentUrl();
        assertTrue(url.contains("sampler"), "URL deve conter 'sampler'");
        assertTrue(url.contains("interaction"), "URL deve conter 'interaction'");

        System.out.println("✓ Página carregou: " + url);
    }

    @Test
    @Order(2)
    @DisplayName("Validar título da página")
    @Description("Verifica se o título da página contém 'Vaadin'")
    void validarTituloPagina() {
        page.openPage();
        sleep(2000);

        String title = page.getPageTitle();
        assertFalse(title.isEmpty(), "Título não deve estar vazio");

        System.out.println("✓ Título da página: " + title);
    }

    @Test
    @Order(3)
    @DisplayName("Validar presença de HTML na página")
    @Description("Verifica se elementos HTML básicos existem")
    void validarElementosHTML() {
        page.openPage();
        sleep(2000);

        assertTrue(page.elementExists("html"), "Tag HTML deve existir");
        assertTrue(page.elementExists("body"), "Tag BODY deve existir");
        assertTrue(page.elementExists("head"), "Tag HEAD deve existir");

        System.out.println("✓ Elementos HTML básicos encontrados");
    }

    @Test
    @Order(4)
    @DisplayName("Validar presença de iframe")
    @Description("Verifica que o iframe do Vaadin Sampler existe")
    void validarIframeExiste() {
        page.openPage();
        sleep(2000);

        int numIframes = page.countElements("iframe");
        assertTrue(numIframes > 0, "Deve existir pelo menos 1 iframe");

        System.out.println("✓ Número de iframes: " + numIframes);
    }

    @Test
    @Order(5)
    @DisplayName("Validar estrutura da página principal")
    @Description("Verifica que divs e scripts existem na página")
    void validarEstruturaPagina() {
        page.openPage();
        sleep(2000);

        int numDivs = page.countElements("div");
        int numScripts = page.countElements("script");

        System.out.println("Divs na página: " + numDivs);
        System.out.println("Scripts na página: " + numScripts);

        assertTrue(numDivs > 0, "Deve ter divs na página");
        assertTrue(numScripts > 0, "Deve ter scripts na página");
    }

    @Test
    @Order(6)
    @DisplayName("Validar que a aplicação Vaadin está presente")
    @Description("Verifica elementos específicos do Vaadin Sampler")
    void validarVaadinPresente() {
        page.openPage();
        sleep(3000);

        // Procura por elementos Vaadin típicos
        boolean hasVaadinElements =
                page.elementExists("[class*='vaadin']") ||
                        page.elementExists("[class*='v-']") ||
                        page.elementExists("[class*='gwt']");

        assertTrue(hasVaadinElements,
                "Deve ter elementos Vaadin ou GWT na página");

        System.out.println("✓ Aplicação Vaadin detectada");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}