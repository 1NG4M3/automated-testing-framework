package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.StatusRequestResponse;
import io.qameta.allure.*;
import org.apache.http.ProtocolException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.StatusCodesController.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
@Story("Проверка различных HTTP-статусов API")
public class StatusCodesControllerTest extends BaseRestAssuredTest {

    @Test
    @DisplayName("Получение 400 Bad Request")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12400")
    public void getBadRequest400Test() {
        StatusRequestResponse response = getBadRequest400();

        assertAll(
                () -> assertEquals(400, response.getStatusCode(), "Ожидался код 400"),
                () -> assertNotNull(response.getDescription(), "Описание не должно быть null")
        );
    }

    @Test
    @DisplayName("Получение 201 Created")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("12401")
    public void getCreatedTest() {
        StatusRequestResponse response = getCreated201();

        assertAll(
                () -> assertEquals(201, response.getStatusCode(), "Ожидался код 201"),
                () -> assertEquals("created", response.getDescription(), "Неверное описание")
        );
    }

    @Test
    @DisplayName("Получение 403 Forbidden")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12402")
    public void getForbidden403Test() {
        StatusRequestResponse response = getForbidden403();

        assertAll(
                () -> assertEquals(403, response.getStatusCode(), "Ожидался код 403"),
                () -> assertEquals("Forbidden", response.getDescription(), "Неверное описание")
        );
    }

    @Test
    @DisplayName("Получение 404 Not Found по невалидному URL")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12403")
    public void getInvalidUrl404Test() {
        StatusRequestResponse response = getInvalidUrl404();

        assertAll(
                () -> assertEquals(404, response.getStatusCode(), "Ожидался код 404"),
                () -> assertEquals("Not Found", response.getDescription(), "Неверное описание")
        );
    }

    @Test
    @DisplayName("Получение 301 Moved Permanently")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("12404")
    public void getMoved301Test() throws ProtocolException {
        StatusRequestResponse response = getMoved301();

        assertAll(
                () -> assertNotNull(response, "Ответ не должен быть null"),
                () -> assertEquals(301, response.getStatusCode(), "Ожидался код 301"),
                () -> assertEquals("Moved Permanently", response.getDescription(), "Неверное описание")
        );
    }

    @Test
    @DisplayName("Получение 204 No Content")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("12405")
    public void getNoContent204Test() {
        String response = getNoContent204();

        assertEquals("", response, "Ответ при 204 должен быть пустым");
    }

    @Test
    @DisplayName("Получение 401 Unauthorized")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12406")
    public void getUnauthorized401Test() {
        String response = getUnauthorized401();

        assertAll(
                () -> assertNotNull(response, "Ответ не должен быть null"),
                () -> assertTrue(response.contains("Unauthorized"), "Ожидалось содержимое 'Unauthorized'")
        );
    }
}