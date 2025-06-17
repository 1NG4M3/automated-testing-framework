package gusev.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UtilityClass
public class FilesController {

    private static final String FILE_UPLOAD_PATH = "src/test/resources/filesRq/MyPhotoForUpload.jpeg";

    @Step("Скачивание изображения с сервера /api/files/download")
    public static byte[] downloadImage() {
        Response response = given()
                .contentType(ContentType.BINARY)
                .when()
                .get("/api/files/download")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.asByteArray();
    }

    @Step("Скачивание последнего загруженного файла с /api/files/downloadLastUploaded")
    public static byte[] downloadLastUploadedFile() {
        Response response = given()
                .contentType(ContentType.BINARY)
                .when()
                .get("/api/files/downloadLastUploaded")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.asByteArray();
    }

    @Step("Загрузка изображения {FILE_UPLOAD_PATH} на сервер")
    public static String uploadFile() {
        File fileForUpload = new File(FILE_UPLOAD_PATH);
        assertTrue(fileForUpload.exists(), "Файл для загрузки не найден: " + FILE_UPLOAD_PATH);

        Response response = given()
                .multiPart(fileForUpload)
                .when()
                .post("/api/files/upload")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.asPrettyString();
    }
}