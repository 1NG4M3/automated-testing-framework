package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static gusev.api.FilesController.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
@Story("Работа с файлами через FilesController API")
public class FilesControllerTest extends BaseRestAssuredTest {

    private static final Path RESOURCES_DIR = Paths.get("src", "test", "resources", "filesRq");

    @Test
    @DisplayName("Скачивание изображения с сервера в формате JPEG")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12345")
    public void downloadImageTest() throws IOException {
        byte[] downloadedFile = downloadImage();
        File outputFile = RESOURCES_DIR.resolve("DownloadedImage.jpeg").toFile();
        saveFile(outputFile, downloadedFile);

        assertTrue(outputFile.exists(), "Файл не был создан");
        assertTrue(downloadedFile.length > 0, "Файл пустой");
    }

    @Test
    @DisplayName("Скачивание последнего загруженного файла")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12346")
    public void downloadLastUploadedImageTest() throws IOException {
        byte[] downloadedFile = downloadLastUploadedFile();
        File outputFile = RESOURCES_DIR.resolve("DownloadedLastUploadedFile.jpeg").toFile();
        saveFile(outputFile, downloadedFile);

        assertTrue(outputFile.exists(), "Файл не был создан");
        assertTrue(downloadedFile.length > 0, "Файл пустой");
    }

    @Test
    @DisplayName("Загрузка файла на сервер")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("12347")
    public void uploadFileTest() throws IOException {
        String response = uploadFile();

        assertAll(
                () -> assertTrue(response.contains("file uploaded to server"), "Ответ не содержит подтверждения загрузки"),
                () -> assertTrue(response.contains("success"), "Ответ не содержит индикатора успеха")
        );
    }

    @Step("Сохраняем файл в {filePath}")
    private void saveFile(File filePath, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
        } catch (IOException e) {
            fail("Ошибка при сохранении файла: " + e.getMessage(), e);
        }
    }
}