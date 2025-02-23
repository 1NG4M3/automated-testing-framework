package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.ResponseTrainController.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
public class ResponseTrainControllerTest extends BaseRestAssuredTest {

    @Test
    @DisplayName("Get easy actual api version")
    public void getEasyActualApiVersionTest() {
        String response = getActualApiVersion();
        Assertions.assertTrue(response.contains("1.0.2"));
    }

    @Test
    @DisplayName("Get easy nums")
    public void getEasyNumsTest() {
        String response = getEasyNums();
        assertNotNull(response);
        Assertions.assertTrue(response.contains("nums"));
    }

    @Test
    @DisplayName("Get easy redirect and status code 301")
    public void getEasyRedirect301Test() {
        String response = getEasyRedirect301();
        assertNotNull(response);
    }

    @Test
    @DisplayName("Get list of car brands")
    public void getListOfCarBrands() {
        String cars = getCarBrands();
        Assertions.assertTrue(cars.contains("Sandero"));
        Assertions.assertTrue(cars.contains("Citroën"));
    }
}