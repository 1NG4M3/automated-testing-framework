package gusev.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

public class GameController {

    private static final String BASE_PATH = "src/test/resources/jsonFiles/";

    private static RequestSpecification baseRequest(String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
    }

    private static String readJson(String fileName) {
        try {
            return Files.readString(Path.of(BASE_PATH + fileName));
        } catch (Exception e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }

    @Step("Get all games")
    public static Response getGames(String token, int expectedStatusCode) {
        return baseRequest(token)
                .when()
                .get("/api/user/games")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Add new game")
    public static Response addGame(String token, int expectedStatusCode) {
        return baseRequest(token)
                .body(readJson("games.json"))
                .when()
                .post("/api/user/games")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Update game DLC info")
    public static Response updateGameDlcInfo(String token, int expectedStatusCode) {
        return baseRequest(token)
                .body(readJson("updateGameDlcInfo.json"))
                .when()
                .put("/api/user/games/0")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Update game field")
    public static Response updateGameField(String token, int expectedStatusCode) {
        return baseRequest(token)
                .body(readJson("updateGameField.json"))
                .when()
                .put("/api/user/games/0/updateField")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Get game by ID")
    public static Response getGame(String token, int expectedStatusCode) {
        return baseRequest(token)
                .when()
                .get("/api/user/games/0")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Delete game by ID")
    public static Response deleteGame(String token, int expectedStatusCode) {
        return baseRequest(token)
                .when()
                .delete("/api/user/games/0")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    @Step("Delete DLC by ID")
    public static Response deleteDlc(String token, int expectedStatusCode) {
        return baseRequest(token)
                .body(readJson("deleteDlcById.json"))
                .when()
                .delete("/api/user/games/0/dlc")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
}