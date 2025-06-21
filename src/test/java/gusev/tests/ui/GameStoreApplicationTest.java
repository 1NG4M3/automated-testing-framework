package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ui")
@Epic("Regression")
@Feature("UI")
@Story("Game Store Application health check")
@Owner("Гусев Дмитрий Викторович")
public class GameStoreApplicationTest extends BaseSelenideTest {

    @Test
    @DisplayName("Link Page positive check")
    public void positiveLinkPageTest() {

        page.goToLinkPage()
                .assertHeaderName("Link Page")
                .assertLinkRedirect("Follow the link to visit Game Store Application");
    }
}