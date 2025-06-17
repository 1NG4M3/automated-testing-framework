package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;

@Epic("Regression")
@Feature("UI")
@Story("Alerts, Frame & Windows health check")
@Owner("Гусев Дмитрий Викторович")
public class AlertsFrameWindowsTest extends BaseSelenideTest {

    private final MainPage page = new MainPage();

    @ParameterizedTest(name = "Проверка {2}: {0}")
    @MethodSource("browserWindowsData")
    @DisplayName("Browser Windows positive check")
    public void positiveBrowserWindowsTest(String buttonText, String expectedText, String type) {
        page.goToBrowserWindows()
                .assertMainText("Browser Windows");

        switch (type) {
            case "tab" -> page.goToBrowserWindows().assertNewTab(buttonText, expectedText);
            case "window" -> page.goToBrowserWindows().assertNewWindow(buttonText, expectedText);
            case "message" -> page.goToBrowserWindows().assertNewWindowMessage(buttonText, expectedText);
            default -> fail("Unknown type: " + type);
        }
    }

    static Stream<Arguments> browserWindowsData() {
        return Stream.of(
                Arguments.of("New Tab", "Thread QA Sample", "tab"),
                Arguments.of("New Window", "Thread QA Sample", "window"),
                Arguments.of("New Window Message",
                        "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.",
                        "message")
        );
    }

    @ParameterizedTest(name = "Проверка алерта: {1}")
    @MethodSource("alertsData")
    @DisplayName("Alerts positive check")
    public void positiveAlertsTest(String description, String type, String inputText) {
        page.goToAlerts()
                .assertMainText("Alerts");

        switch (type) {
            case "alert" -> page.goToAlerts().assertAlertButton(description);
            case "timerAlert" -> page.goToAlerts().assertTimerAlertButton(description);
            case "confirm" -> page.goToAlerts().assertConfirmButton(description);
            case "prompt" -> page.goToAlerts().assertPromtButton(description, inputText);
            default -> fail("Unknown alert type: " + type);
        }
    }

    static Stream<Arguments> alertsData() {
        return Stream.of(
                Arguments.of("Click Button to see alert", "alert", null),
                Arguments.of("On button click, alert will appear after 5 seconds", "timerAlert", null),
                Arguments.of("On button click, confirm box will appear", "confirm", null),
                Arguments.of("On button click, prompt box will appear", "prompt", "Dmitry")
        );
    }

    @Test
    @DisplayName("Frames positive check")
    public void positiveFramesTest() {
        page.goToFrames()
                .assertMainText("Frames")
                .assertFramesSize(2)
                .assertFrameOne("Parent frame", "Child Thread QA iFrame :)")
                .assertFrameTwo("Parent frame", "Child Thread QA iFrame :)");
    }

    @Test
    @DisplayName("Nested Frames positive check")
    public void positiveNestedFramesTest() {
        page.goToNestedFrames()
                .assertMainText("Nested Frames")
                .assertFramesSize(1)
                .assertFrame("Parent frame", "Child Thread QA iFrame :)");
    }

    @ParameterizedTest(name = "Модальное окно: {0}")
    @MethodSource("modalDialogsData")
    @DisplayName("Modal Dialogs positive check")
    public void positiveModalDialogsTest(String buttonText, String headerText, String bodyText, boolean isSmall) {
        page.goToModalDialogs()
                .assertMainText("Modal Dialogs")
                .assertDescriptionText("Click on button to see modal");

        if (isSmall) {
            page.goToModalDialogs().assertSmallModal(buttonText, headerText, bodyText);
        } else {
            page.goToModalDialogs().assertLargeModal(buttonText, headerText, bodyText);
        }
    }

    static Stream<Arguments> modalDialogsData() {
        return Stream.of(
                Arguments.of("Small modal", "Small Modal", "This is a small modal. It has very less content", true),
                Arguments.of("Large modal", "Large Modal",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer" +
                                " took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
                                " centuries, but also the leap into electronic typesetting, remaining essentially unchanged." +
                                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                                " and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                        false)
        );
    }
}