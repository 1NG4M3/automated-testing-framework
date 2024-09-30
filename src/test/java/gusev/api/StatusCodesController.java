package gusev.api;

import gusev.dto.StatusRequestResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Data;
import org.apache.http.ProtocolException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Data
public class StatusCodesController {

    public static StatusRequestResponse getBadRequest400() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/bad-request")
                .then()
                .statusCode(400)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }

    public static StatusRequestResponse getCreated201() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/created")
                .then()
                .statusCode(201)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }

    public static StatusRequestResponse getForbidden403() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/forbidden")
                .then()
                .statusCode(403)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }

    public static StatusRequestResponse getInvalidUrl404() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/invalid-url")
                .then()
                .statusCode(404)
                .extract()
                .response();
        return response.as(StatusRequestResponse.class);
    }

    public static StatusRequestResponse getMoved301() throws ProtocolException {
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
}