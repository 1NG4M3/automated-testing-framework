package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.TokenApi;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static gusev.api.CreateAuthenticationToken.createAuthToken;
import static gusev.api.GameController.*;
import static gusev.utils.TestUserGenerator.generateUniqueUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameControllerTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();
    private String token;

    @BeforeEach
    void setUp() {
        RegUser regUser = generateUniqueUser();
        userService.registerUser(regUser).statusCode(201);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        token = newToken.getToken();
    }

    @Test
    @DisplayName("Adding game for user by token")
    @Story("Add Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(1)
    void addGameTest() {
        String response = String.valueOf(addGame(token, 500));
        assertNotNull(response,"Response should not be null");
    }

    @Test
    @DisplayName("Delete dlc game by id")
    @Story("Delete Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void deleteDlcTest() {
        String response = String.valueOf(deleteDlc(token, 400));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Delete game by id")
    @Story("Delete Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    public void deleteGameByIdTest() {
        String response = String.valueOf(deleteGame(token, 400));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Getting game by id")
    @Story("Get Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void getGameByIdTest() {
        String response = String.valueOf(getGame(token, 400));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Getting all user's games")
    @Story("Get Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void getGamesTest() {
        String response = String.valueOf(getGames(token, 200));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Update game dlc info")
    @Story("Update Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void updateGameDlcInfoTest() {
        String response = String.valueOf(updateGameDlcInfo(token, 400));
        assertNotNull(response);
    }

    @Test
    @DisplayName("Update game field info")
    @Story("Update Game")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void updateGameFieldTest() {
        String response = String.valueOf(updateGameField(token, 400));
        assertNotNull(response);
    }
}