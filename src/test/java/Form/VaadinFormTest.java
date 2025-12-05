package Form;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Epic("Parte 2 - Selenide")
@Feature("Formulário Vaadin")
public class VaadinFormTest {

    VaadinFormPage page = new VaadinFormPage();

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    @Story("Registo com Sucesso e Email")
    @Description("Preenche todos os campos, incluindo o Email obrigatório.")
    public void testSignupSuccess() {
        page.openPage();


        page.fillFormJS("Rui", "Ninja", "rui_ninja", "Pass1234!", "rui.teste@iscte.pt");

        page.submit();

        page.assertSuccess();
    }
}