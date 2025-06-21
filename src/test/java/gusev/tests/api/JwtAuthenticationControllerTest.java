package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.TokenApi;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static gusev.api.CreateAuthenticationToken.createAuthToken;
import static gusev.utils.TestUserGenerator.generateUniqueUser;

@Tag("api")
@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
public class JwtAuthenticationControllerTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("Create user token")
    @Step("Получение токена")
    public void createTokenForUser() {
        RegUser regUser = generateUniqueUser();
        userService.registerUser(regUser).statusCode(201);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        Assertions.assertNotNull(newToken.getToken());
    }
}