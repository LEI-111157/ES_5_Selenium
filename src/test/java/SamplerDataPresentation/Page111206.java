package SamplerDataPresentation;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object para o exemplo "Data presentation" (Test suite 7) da Vaadin Sampler.
 */
public class Page111206 {

    // URL direta para o exemplo de data presentation (ajusta se usarem outro componente)
    private static final String DATA_PRESENTATION_URL =
            "https://demo.vaadin.com/sampler/#ui/data-presentation/table";

    /** Abre a página do exemplo de Data Presentation na Sampler. */
    public void openPage() {
        open(DATA_PRESENTATION_URL);
    }

    /** Devolve o elemento principal da tabela/grade de dados. */
    public SelenideElement dataTable() {
        // 👉 aqui podes ajustar depois com o seletor real que vires no inspector F12
        return $("table");        // primeiro `table` da página
    }

    /** Devolve as linhas de dados da tabela. */
    public ElementsCollection dataRows() {
        // também podes ajustar o seletor conforme o HTML real
        return $$("table tr");
    }
}