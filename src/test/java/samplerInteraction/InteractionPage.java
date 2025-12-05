package samplerInteraction;

import static com.codeborne.selenide.Selenide.*;

public class InteractionPage {

    private static final String INTERACTION_URL = "https://demo.vaadin.com/sampler/#ui/interaction";

    public void openPage() {
        open(INTERACTION_URL);
        sleep(6000); // Espera carregar
    }

    public boolean elementExists(String selector) {
        try {
            return $(selector).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public int countElements(String selector) {
        try {
            return $$(selector).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getPageTitle() {
        return title();
    }

    public String getCurrentUrl() {
        return webdriver().driver().url();
    }
}