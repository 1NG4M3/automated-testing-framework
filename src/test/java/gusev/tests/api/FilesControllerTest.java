package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static gusev.api.FilesController.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
public class FilesControllerTest extends BaseRestAssuredTest {

    @Test
    @DisplayName("Downloading image from server in jpeg form")
    public void downloadImageTest() throws IOException {
        byte[] dowloadedFile = downloadImage();
        try {
            FileOutputStream os = new FileOutputStream(new File("src/test/resources/filesRq/DownloadedImage.jpeg"));
            os.write(dowloadedFile);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Image absolute path: " + "\n" + "C:\\Users\\HP\\IdeaProjects\\automated-testing-framework\\src\\test\\resources\\filesRq\\DownloadedImage.jpeg");
        Assertions.assertNotNull(dowloadedFile);
    }

    @Test
    @DisplayName("Downloading last uploaded file from server")
    public void downloadLastUploadedImageTest() throws IOException {
        byte[] dowloadedLastUploadedFile = downloadLastUploadedFile();
        try {
            FileOutputStream os = new FileOutputStream(new File("src/test/resources/filesRq/DownloadedLastUploadedFile.jpeg"));
            os.write(dowloadedLastUploadedFile);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File absolute path: " + "\n" + "C:\\Users\\HP\\IdeaProjects\\automated-testing-framework\\src\\test\\resources\\filesRq\\DownloadedLastUploadedFile.jpeg");
        Assertions.assertNotNull(dowloadedLastUploadedFile);
    }

    @Test
    @DisplayName("Upload file on server")
    public void uploadFileTest() throws IOException {
        String response = uploadFile();
        assertTrue(response.contains("file uploaded to server"));
        assertTrue(response.contains("success"));
    }
}