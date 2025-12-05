package Form;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class VaadinFormPage {


    private final SelenideElement firstNameField = $("vaadin-form-layout > vaadin-text-field:nth-of-type(1)");
    private final SelenideElement lastNameField = $("vaadin-form-layout > vaadin-text-field:nth-of-type(2)");
    private final SelenideElement userHandleField = $("vaadin-form-layout > vaadin-text-field:nth-of-type(3)");

    private final SelenideElement passwordField = $("vaadin-form-layout > vaadin-password-field:nth-of-type(1)");
    private final SelenideElement confirmPasswordField = $("vaadin-form-layout > vaadin-password-field:nth-of-type(2)");


    private final SelenideElement emailField = $("vaadin-email-field");

    private final SelenideElement marketingCheckbox = $("vaadin-checkbox");
    private final SelenideElement submitButton = $("vaadin-button[theme~='primary']");



    @Step("Abrir página")
    public void openPage() {
        open("https://vaadin-form-example.demo.vaadin.com/");
    }

    @Step("Preencher o formulário completo (Ninja JS Mode)")
    public void fillFormJS(String first, String last, String user, String pass, String email) {

        String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'))";

        // 1. Preencher dados normais
        Selenide.executeJavaScript(script, firstNameField, first);
        Selenide.executeJavaScript(script, lastNameField, last);
        Selenide.executeJavaScript(script, userHandleField, user);
        Selenide.executeJavaScript(script, passwordField, pass);
        Selenide.executeJavaScript(script, confirmPasswordField, pass);

        // 2. Tratar do Email
        if (email != null && !email.isEmpty()) {
            marketingCheckbox.click();

            // Esperar que o campo apareça antes de tentar escrever
            emailField.shouldBe(visible);

            // Injetar o email
            Selenide.executeJavaScript(script, emailField, email);
        }
    }

    @Step("Submeter Formulário")
    public void submit() {
        submitButton.click();
    }

    @Step("Validar Sucesso")
    public void assertSuccess() {

        $("vaadin-notification-card").shouldBe(visible);
    }
}