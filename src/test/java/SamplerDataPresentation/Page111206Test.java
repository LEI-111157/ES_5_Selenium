package SamplerDataPresentation;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Page111206Test {

    @Test
    public void tableDeveMostrarPeloMenosUmaLinhaDeDados() {
        Page111206 page = new Page111206();

        // abrir o exemplo
        page.openPage();

        // garantir que a tabela aparece
        page.dataTable().shouldBe(Condition.visible);

        // garantir que existe pelo menos 1 linha de dados
        page.dataRows().shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
