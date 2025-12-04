package iscteiul.ista.es_5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HerokuUploadPage {
    // 1. Botão "Escolher ficheiro"
    @FindBy(id = "file-upload")
    public WebElement fileInput;

    // 2. Botão azul "Upload"
    @FindBy(id = "file-submit")
    public WebElement uploadButton;

    // 3. Mensagem de sucesso
    // Tem de ser 'header' para bater certo com o teste
    @FindBy(tagName = "h3")
    public WebElement header;

    // 4. Lista de ficheiros enviados
    @FindBy(id = "uploaded-files")
    public WebElement uploadedFilesList;

    public HerokuUploadPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void uploadFile(String absolutePath) {
        fileInput.sendKeys(absolutePath);
        uploadButton.click();
    }
}