package gusev.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import gusev.dto.RegistrationApi;
import gusev.dto.TokenApi;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
@Story("Authentication and User Management API")
public class CreateAuthenticationToken {

    @Step("Получение токена авторизации по логину и паролю")
    public static TokenApi createAuthToken(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(TokenApi.class);
    }

    @Step("Получение информации о пользователе по токену")
    public static RegistrationApi.RegisterData getUserInfoByToken(String token) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/user")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(RegistrationApi.RegisterData.class);
    }

    @Step("Обновление пароля пользователя по токену")
    public static RegistrationApi updateUserPassword(String token, String newPassword) {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(newPassword);
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(updatePasswordRequest)
                .when()
                .put("/api/user")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(RegistrationApi.class);
    }

    @Step("Удаление информации о пользователе по токену")
    public static RegistrationApi deleteUserInfoByToken(String token) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/api/user")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(RegistrationApi.class);
    }

    @Step("Получение логинов последних 100 пользователей")
    public static String get100LastUsersLogins() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }

    @Data
    @AllArgsConstructor
    private static class LoginRequest {
        @JsonProperty("username")
        private String username;
        @JsonProperty("password")
        private String password;
    }

    @Data
    @AllArgsConstructor
    private static class UpdatePasswordRequest {
        @JsonProperty("password")
        private String password;
    }
}