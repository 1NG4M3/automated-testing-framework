package gusev.tests.ui;

import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Regression")
@Feature("UI")
@Story("Game Store Application health check")
@Owner("Гусев Дмитрий Викторович")
public class GameStoreApplicationTest {

    private MainPage page = new MainPage();

    @Test
    @DisplayName("Link Page positive check")
    public void positiveLinkPageTest() {
        String expectedHeaderText = "Link Page";
        String expectedMainText = "Follow the link to visit Game Store Application";

        page.goToLinkPage()
                .assertHeaderName(expectedHeaderText)
                .assertLinkRedirect(expectedMainText);
    }
}