package Database;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

// page_url = https://vaadin-database-example.demo.vaadin.com/
public class Page{

    private static final String DATABASE_URL = "https://vaadin-database-example.demo.vaadin.com/";

    private final ElementsCollection movieCells = $$("vaadin-grid-cell-content");

    public void openPage() {
        open(DATABASE_URL);
    }

    public ElementsCollection getMovieCells() {
        return movieCells;
    }

    public SelenideElement findMovieByTitle(String title) {
        return movieCells.findBy(text(title));
    }
}
