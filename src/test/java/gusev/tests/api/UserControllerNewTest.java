package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.RegistrationApi;
import gusev.models.Info;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static gusev.api.CreateAuthenticationToken.*;
import static gusev.utils.TestUserGenerator.generateUniqueUser;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
@Story("UserController — работа с пользователями через токен")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserControllerNewTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();
    private String token;
    private RegUser currentUser;

    @BeforeEach
    void setUp() {
        currentUser = generateUniqueUser(); // Вынес генерацию пользователя в отдельный метод
        userService.registerUser(currentUser).statusCode(201);
        token = createAuthToken(currentUser.getLogin(), currentUser.getPass()).getToken();
    }

    @Test
    @DisplayName("1. Удаление пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("UCN-001")
    public void deleteUserTest() {
        RegistrationApi response = deleteUserInfoByToken(token);

        assertAll(
                () -> assertEquals("success", response.getInfo().getStatus(), "Статус должен быть success"),
                () -> assertEquals("User successfully deleted", response.getInfo().getMessage(), "Неверное сообщение")
        );
    }

    @Test
    @DisplayName("2. Получение логинов 100 последних пользователей")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("UCN-002")
    public void getLoginsOfLast100Users() {
        String usersLogins = get100LastUsersLogins();
        assertNotNull(usersLogins, "Список логинов не должен быть null");
        assertTrue(usersLogins.contains("testUser"), "Список логинов должен содержать логины пользователей");
    }

    @Test
    @DisplayName("3. Изменение пароля пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("UCN-003")
    public void updateUserPasswordByToken() {
        String newPassword = "newSecurePassword456";
        RegistrationApi updated = updateUserPassword(token, newPassword);

        assertTrue(updated.getInfo().getMessage().contains("User password successfully changed"),
                "Ожидалось сообщение об успешной смене пароля");
    }

    @Test
    @DisplayName("4. Получение информации о пользователе по токену")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("UCN-004")
    public void userInfoByToken() {
        RegistrationApi.RegisterData userInfo = getUserInfoByToken(token);

        assertAll(
                () -> assertEquals(currentUser.getLogin(), userInfo.getLogin(), "Логины не совпадают"),
                () -> assertEquals(currentUser.getPass(), userInfo.getPass(), "Пароли не совпадают")
        );
    }

    @Test
    @DisplayName("5. Регистрация нового пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("UCN-005")
    public void registerNewUserUpdated() {
        RegUser newUser = generateUniqueUser();

        Info info = userService.registerUser(newUser)
                .statusCode(201)
                .extract()
                .jsonPath()
                .getObject("info", Info.class);

        assertAll(
                () -> assertEquals("success", info.getStatus(), "Ожидался статус 'success'"),
                () -> assertEquals("User created", info.getMessage(), "Неверное сообщение о создании пользователя")
        );
    }
}