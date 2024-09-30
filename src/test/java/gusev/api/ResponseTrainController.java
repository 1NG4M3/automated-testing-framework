package gusev.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data
public class ResponseTrainController {
    public static String getCarBrands() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/easy/carBrands")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String getEasyNums() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/easy/nums")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String getEasyRedirect301() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/easy/redirect")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String getActualApiVersion() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/easy/version")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }
}