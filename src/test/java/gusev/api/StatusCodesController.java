package gusev.api;

import gusev.dto.StatusRequestResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class StatusCodesController {

    @Step("Получение 400 Bad Request")
    public static StatusRequestResponse getBadRequest400() {
        return sendJsonGet("/api/bad-request", 400);
    }

    @Step("Получение 201 Created")
    public static StatusRequestResponse getCreated201() {
        return sendJsonGet("/api/created", 201);
    }

    @Step("Получение 403 Forbidden")
    public static StatusRequestResponse getForbidden403() {
        return sendJsonGet("/api/forbidden", 403);
    }

    @Step("Получение 404 Not Found")
    public static StatusRequestResponse getInvalidUrl404() {
        return sendJsonGet("/api/invalid-url", 404);
    }

    @Step("Получение 301 Moved Permanently (без редиректа)")
    public static StatusRequestResponse getMoved301() {
        Response response = given()
                .contentType(ContentType.JSON)
                .redirects().follow(false)
                .when()
                .get("/api/moved")
                .then()
                .statusCode(301)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }

    @Step("Получение 204 No Content")
    public static String getNoContent204() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/no-content")
                .then()
                .statusCode(204)
                .extract()
                .response();
        return response.asPrettyString();
    }

    @Step("Получение 401 Unauthorized")
    public static String getUnauthorized401() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/unauthorized")
                .then()
                .statusCode(401)
                .extract()
                .response();
        return response.asPrettyString();
    }

    private static StatusRequestResponse sendJsonGet(String endpoint, int expectedStatusCode) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }
}