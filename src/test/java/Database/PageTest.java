package Database;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageTest {

    private Page databasePage;

    @BeforeAll
    static void setupSelenide() {
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 8000;
        Configuration.pageLoadTimeout = 10000;
    }

    @BeforeEach
    void openDatabase() {
        databasePage = new Page();
        databasePage.openPage();
    }

    @Test
    void validarInformacaoDoPrimeiroFilme() {

        sleep(1500);

        String titulo = databasePage.getMovieCells().get(0).getText();
        String ano    = databasePage.getMovieCells().get(1).getText();
        String autor  = databasePage.getMovieCells().get(2).getText();
        String link   = databasePage.getMovieCells().get(3).getText();

        String esperadoTitulo = "Law Abiding Citizen";
        String esperadoAno = "2009";
        String esperadoAutor = "F. Gardy Gray";
        String esperadoTextoLink = "Click to IMBD site";

        // validações
        assertEquals(esperadoTitulo, titulo);
        assertEquals(esperadoAno, ano);
        assertEquals(esperadoAutor, autor);
        assertEquals(esperadoTextoLink, link);
    }
}
