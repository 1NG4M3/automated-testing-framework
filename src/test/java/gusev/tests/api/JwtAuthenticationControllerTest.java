package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.TokenApi;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.CreateAuthenticationToken.createAuthToken;

@Epic("Regression")
@Feature("API")
public class JwtAuthenticationControllerTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("Create user token")
    public void createTokenForUser() {
        RegUser regUser = new RegUser("testUser9" + System.currentTimeMillis(), "oaimsdoi43353a33msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        Assertions.assertNotNull(newToken.getToken());
    }
}