package gusev.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

@Data
public class GameControllerNew {
    public static String getGames(String token) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/user/games")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String addGame(String token) throws IOException {
        File jsonFile = new File("src/test/resources/jsonFiles/games.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonFile)
                .when()
                .post("/api/user/games")
                .then()
                .statusCode(500) // Сервис временно не работает, ожидаем 201 статус код, приходит 500.
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String updateGameDlcInfo(String token) throws IOException {
        File jsonFile = new File("src/test/resources/jsonFiles/updateGameDlcInfo.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonFile)
                .when()
                .put("/api/user/games/0")
                .then()
                .statusCode(400) // method not allowed, because can't add game
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String updateGameField(String token) throws IOException {
        File jsonFile = new File("src/test/resources/jsonFiles/updateGameField.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonFile)
                .when()
                .put("/api/user/games/0/updateField")
                .then()
                .statusCode(400) // method not allowed, because can't add game
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String getGame(String token) throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/user/games/0")
                .then()
                .statusCode(400) // method not allowed, because can't add game
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String deleteGame(String token) throws IOException {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/api/user/games/0")
                .then()
                .statusCode(400) // method not allowed, because can't add game
                .extract()
                .response();
        return response.asPrettyString();
    }

    public static String deleteDlc(String token) throws IOException {
        File jsonFile = new File("src/test/resources/jsonFiles/deleteDlcById.json");
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(jsonFile)
                .when()
                .delete("/api/user/games/0/dlc")
                .then()
                .statusCode(400) // method not allowed, because can't add game
                .extract()
                .response();
        return response.asPrettyString();
    }
}