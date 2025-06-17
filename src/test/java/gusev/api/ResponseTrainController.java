package gusev.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static io.restassured.RestAssured.given;

@UtilityClass
public class ResponseTrainController {

    @Step("Получение списка автомобильных брендов /api/easy/carBrands")
    public static String getCarBrands() {
        return sendGetRequest("/api/easy/carBrands");
    }

    @Step("Получение числовых данных /api/easy/nums")
    public static String getEasyNums() {
        return sendGetRequest("/api/easy/nums");
    }

    @Step("Получение редиректа 301 (ожидаем 200 после редиректа) /api/easy/redirect")
    public static String getEasyRedirect301() {
        return sendGetRequest("/api/easy/redirect");
    }

    @Step("Получение актуальной версии API /api/easy/version")
    public static String getActualApiVersion() {
        return sendGetRequest("/api/easy/version");
    }

    private static String sendGetRequest(String endpoint) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response.asPrettyString();
    }
}