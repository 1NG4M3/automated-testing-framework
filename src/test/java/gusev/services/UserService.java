package gusev.services;

import gusev.models.RegUser;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserService {

    public ValidatableResponse registerUser(RegUser user) {
        return given()
                .basePath("/api/signup")
                .contentType(ContentType.JSON)
                .body(user)
                .post()
                .then();
    }
}