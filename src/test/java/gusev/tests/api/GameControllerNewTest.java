package gusev.tests.api;

import gusev.api.BaseRestAssuredTest;
import gusev.dto.TokenApi;
import gusev.models.RegUser;
import gusev.services.UserService;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static gusev.api.CreateAuthenticationToken.createAuthToken;
import static gusev.api.GameControllerNew.*;

@Epic("Regression")
@Feature("API")
@Owner("Гусев Дмитрий Викторович")
public class GameControllerNewTest extends BaseRestAssuredTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("Adding game for user by token")
    public void addGameTest() throws IOException {
        RegUser regUser = new RegUser("testUser" + System.currentTimeMillis(), "oaimsdoiamsd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = addGame(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Delete dlc game by id")
    public void deleteDlcTest() throws IOException {
        RegUser regUser = new RegUser("testUser3" + System.currentTimeMillis(), "oaimsdoi33amsd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = deleteDlc(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Delete game by id")
    public void deleteGameByIdTest() throws IOException {
        RegUser regUser = new RegUser("testUser4" + System.currentTimeMillis(), "oaimsdoi433amsd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = deleteGame(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Getting game by id")
    public void getGameByIdTest() throws IOException {
        RegUser regUser = new RegUser("testUser5" + System.currentTimeMillis(), "oaimsdoi4335amsd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = getGame(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Getting all user's games")
    public void getGamesTest() {
        RegUser regUser = new RegUser("testUser6" + System.currentTimeMillis(), "oaimsdoi4335a3msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = getGames(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Update game dlc info")
    public void updateGameDlcInfoTest() throws IOException {
        RegUser regUser = new RegUser("testUser7" + System.currentTimeMillis(), "oaimsdoi4335a3msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = updateGameDlcInfo(newToken.getToken());
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Update game field info")
    public void updateGameFieldTest() throws IOException {
        RegUser regUser = new RegUser("testUser8" + System.currentTimeMillis(), "oaimsdoi4335a33msd");
        userService.registerUser(regUser);
        TokenApi newToken = createAuthToken(regUser.getLogin(), regUser.getPass());
        String response = updateGameField(newToken.getToken());
        Assertions.assertNotNull(response);
    }
}