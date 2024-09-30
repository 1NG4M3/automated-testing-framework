package gusev.api;

import gusev.dto.RegistrationApi;
import gusev.models.Info;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

@Data
public class FilesController {
    public static byte[] downloadImage() throws IOException {
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

    public static byte[] downloadLastUploadedFile() throws IOException {
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

    public static String uploadFile() throws IOException {
        File fileForUpload = new File("src/test/resources/filesRq/MyPhotoForUpload.jpeg");
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