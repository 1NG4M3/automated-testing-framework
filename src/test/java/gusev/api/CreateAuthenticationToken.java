package gusev.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import gusev.dto.RegistrationApi;
import gusev.dto.TokenApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data
public class CreateAuthenticationToken {

    @AllArgsConstructor
    private static class LoginRequest {
        @JsonProperty("username")
        private String username;
        @JsonProperty("password")
        private String password;
    }

    @AllArgsConstructor
    private static class UpdatePasswordRequest {
        @JsonProperty("password")
        private String password;
    }

    public static TokenApi createAuthToken(String username, String password) {
        LoginRequest logingRequest = new LoginRequest(username, password);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(logingRequest)
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.as(TokenApi.class);
    }

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

    public static RegistrationApi updateUserPassword(String token, String password) {
        UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest(password);
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
}