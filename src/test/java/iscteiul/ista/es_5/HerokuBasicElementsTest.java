package iscteiul.ista.es_5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HerokuBasicElementsTest extends BaseHerokuTest {

    @Test
    public void checkboxesTest() throws InterruptedException {
        CheckboxesPage page = new CheckboxesPage(driver);
        page.open(BASE_URL);

        // opcional: pequena pausa para observação (se quiseres mostrar no relatório)
        Thread.sleep(1000);

        // por convenção nesta página, a segunda já vem checked
        page.uncheckSecond();
        page.checkFirst();

        assertTrue(page.isFirstChecked(),  "Primeira checkbox devia estar selecionada");
        assertFalse(page.isSecondChecked(), "Segunda checkbox devia estar desmarcada");
    }

    @Test
    public void dropdownTest() throws InterruptedException {
        DropdownPage page = new DropdownPage(driver);
        page.open(BASE_URL);

        Thread.sleep(1000); // só para ver o dropdown

        // selecionar Option 2 (value = "2")
        page.selectByValue("2");

        Thread.sleep(500);

        assertEquals("Option 2", page.getSelectedText());
    }
}
