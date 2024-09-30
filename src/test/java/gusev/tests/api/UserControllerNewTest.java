package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.RegistrationApi;
import gusev.dto.TokenApi;
import gusev.models.Info;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.api.CreateAuthenticationToken.*;

@Epic("Regression")
@Feature("API")
public class UserControllerNewTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("Delete user data")
    public void deleteUserTest() {
        RegUser regUser = new RegUser("testUser10" + System.currentTimeMillis(), "oaimsdoi43353a33msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        RegistrationApi deleteUser = deleteUserInfoByToken(newToken.getToken());
        Assertions.assertEquals("success",deleteUser.getInfo().getStatus());
        Assertions.assertEquals("User successfully deleted",deleteUser.getInfo().getMessage());
    }

    @Test
    @DisplayName("Get logins of last 100 registered users")
    public void getLoginsOfLast100Users() {
        String usersLogins = get100LastUsersLogins();
        Assertions.assertNotNull(usersLogins);
    }

    @Test
    @DisplayName("Update user password test")
    public void updateUserPasswordByToken() {
        RegUser regUser = new RegUser("testUser10" + System.currentTimeMillis(), "oaimsdoi43353a33msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        RegistrationApi updatedUser = updateUserPassword(newToken.getToken(), "77889991090");
        Assertions.assertTrue(updatedUser.getInfo().getMessage().contains("User password successfully changed"));
    }

    @Test
    @DisplayName("Get user information tests")
    public void userInfoByToken() {
        RegUser regUser = new RegUser("testUser10" + System.currentTimeMillis(), "oaimsdoi43353a33msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        RegistrationApi.RegisterData infoUser = getUserInfoByToken(newToken.getToken());
        Assertions.assertEquals(regUser.getLogin(), infoUser.getLogin());
        Assertions.assertEquals(regUser.getPass(), infoUser.getPass());
    }

    @Test
    @DisplayName("Register user tests")
    public void registerNewUserUpdated() {
        RegUser regUser = new RegUser("testUser" + System.currentTimeMillis(), "oaimsdoiamsd");
        Info info = userService.registerUser(regUser)
                .statusCode(201)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("success", info.getStatus());
        Assertions.assertEquals("User created", info.getMessage());
    }
}