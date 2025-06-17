package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadPage {

    private final SelenideElement downloadButton = $x("//*[@id='downloadButton']");
    private final SelenideElement selectFileText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[2]/form/div/label");
    private final SelenideElement uploadButton = $x("//*[@id='uploadFile']");
    private final SelenideElement uploadedFilePath = $x("//*[@id='uploadedFilePath']");

    @Step("Скачать файл и проверить: текст '{expectedText}', ссылка содержит '{expectedHref}'")
    public UploadAndDownloadPage assertDownloadFile(String expectedText, String expectedHref) throws FileNotFoundException {
        File downloadedFile = downloadButton
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(Condition.partialText(expectedText))
                .shouldHave(Condition.href(expectedHref))
                .download();

        assertTrue(downloadedFile.exists(), "Файл не был загружен");
        return this;
    }

    @Step("Проверить текст кнопки выбора файла: '{expectedText}'")
    public UploadAndDownloadPage assertSelectFileText(String expectedText) {
        selectFileText
                .shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Загрузить файл 'MyPhotoForUpload.jpeg'")
    public UploadAndDownloadPage uploadFile() {
        File fileToUpload = new File("src/test/resources/filesRq/MyPhotoForUpload.jpeg");
        uploadButton
                .shouldBe(Condition.visible)
                .uploadFile(fileToUpload);
        return this;
    }

    @Step("Проверить путь к загруженному файлу: содержит '{expectedText}'")
    public UploadAndDownloadPage assertUploadedFilePath(String expectedText) {
        uploadedFilePath
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
        return this;
    }
}