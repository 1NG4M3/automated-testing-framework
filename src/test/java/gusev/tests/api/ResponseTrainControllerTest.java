package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.ResponseTrainController.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
@Story("Проверка простых GET-запросов с ожидаемыми статусами и содержимым")
public class ResponseTrainControllerTest extends BaseRestAssuredTest {

    @Test
    @DisplayName("Получение текущей версии API (GET /api/easy/version)")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("12350")
    public void getEasyActualApiVersionTest() {
        String response = getActualApiVersion();

        assertAll(
                () -> assertNotNull(response, "Ответ не должен быть null"),
                () -> assertTrue(response.contains("1.0.2"), "Ответ должен содержать версию 1.0.2")
        );
    }

    @Test
    @DisplayName("Получение чисел из /api/easy/nums")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12351")
    public void getEasyNumsTest() {
        String response = getEasyNums();

        assertAll(
                () -> assertNotNull(response, "Ответ не должен быть null"),
                () -> assertTrue(response.contains("nums"), "Ответ должен содержать 'nums'")
        );
    }

    @Test
    @DisplayName("Проверка редиректа и кода 301 (GET /api/easy/redirect)")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12352")
    public void getEasyRedirect301Test() {
        String response = getEasyRedirect301();

        assertNotNull(response, "Ответ не должен быть null");
    }

    @Test
    @DisplayName("Получение списка брендов автомобилей")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("12353")
    public void getListOfCarBrands() {
        String cars = getCarBrands();

        assertAll(
                () -> assertTrue(cars.contains("Sandero"), "Список должен содержать 'Sandero'"),
                () -> assertTrue(cars.contains("Citroën"), "Список должен содержать 'Citroën'")
        );
    }
}