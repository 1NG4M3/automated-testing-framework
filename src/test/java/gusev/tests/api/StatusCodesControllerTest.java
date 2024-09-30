package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.StatusRequestResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.ProtocolException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.StatusCodesController.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Regression")
@Feature("API")
public class StatusCodesControllerTest extends BaseRestAssuredTest {

    @Test
    @DisplayName("Get bad request 400")
    public void getBadRequest400Test() {
        StatusRequestResponse badRequest = getBadRequest400();
        Assertions.assertEquals(400, badRequest.getStatusCode());
    }

    @Test
    @DisplayName("Get created request 201")
    public void getCreatedTest() {
        StatusRequestResponse createdRequest = getCreated201();
        Assertions.assertEquals(201, createdRequest.getStatusCode());
        Assertions.assertEquals("created", createdRequest.getDescription());
    }

    @Test
    @DisplayName("Get forbidden request 403")
    public void getForbidden403Test() {
        StatusRequestResponse body = getForbidden403();
        Assertions.assertEquals(403, body.getStatusCode());
        Assertions.assertEquals("Forbidden", body.getDescription());
    }

    @Test
    @DisplayName("Get invalid url with 404 status code")
    public void getInvalidUrl404Test() {
        StatusRequestResponse body = getInvalidUrl404();
        Assertions.assertEquals(404, body.getStatusCode());
        Assertions.assertEquals("Not Found", body.getDescription());
    }

    @Test
    @DisplayName("Get moved url with 301 status code")
    public void getMoved301Test() throws ProtocolException {
        StatusRequestResponse response = getMoved301();
        assertNotNull(response);
        Assertions.assertEquals(301, response.getStatusCode());
        Assertions.assertEquals("Moved Permanently", response.getDescription());
    }

    @Test
    @DisplayName("Get no content with status code 204")
    public void getNoContent204Test() {
        String response = getNoContent204();
        Assertions.assertEquals("", response);
    }

    @Test
    @DisplayName("Get unauthorized request with status code 401")
    public void getUnauthorized401Test() {
        String response = getUnauthorized401();
        assertNotNull(response);
        Assertions.assertTrue(response.contains("Unauthorized"));
    }
}