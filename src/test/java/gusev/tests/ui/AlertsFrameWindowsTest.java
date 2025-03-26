package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Regression")
@Feature("UI")
@Story("Alerts, Frame & Windows health check")
@Owner("Гусев Дмитрий Викторович")
public class AlertsFrameWindowsTest extends BaseSelenideTest {

    private MainPage page = new MainPage();

    @Test
    @DisplayName("Browser Windows positive check")
    public void positiveBrowserWindowsTest() {
        String expectedMainText = "Browser Windows";
        String expectedTabButtonText = "New Tab";
        String expectedNewTabWindowText = "Thread QA Sample";
        String expectedWindowButtonText = "New Window";
        String expectedWindowMessageButtonText = "New Window Message";
        String expectedWindowBodyText = "Knowledge increases by sharing but not by saving." +
                " Please share this website with your friends and in your organization.";

        page.goToBrowserWindows()
                .assertMainText(expectedMainText)
                .assertNewTab(expectedTabButtonText, expectedNewTabWindowText)
                .assertNewWindow(expectedWindowButtonText, expectedNewTabWindowText)
                .assertNewWindowMessage(expectedWindowMessageButtonText, expectedWindowBodyText);
    }

    @Test
    @DisplayName("Alerts positive check")
    public void positiveAlertsTest() {
        String expectedMainText = "Alerts";
        String expectedAlertDescription = "Click Button to see alert";
        String expectedTimerAlertDescription = "On button click, alert will appear after 5 seconds";
        String expectedConfirmDescription = "On button click, confirm box will appear";
        String expectedPromtDescription = "On button click, prompt box will appear";
        String inputPromtText = "Dmitry";

        page.goToAlerts()
                .assertMainText(expectedMainText)
                .assertAlertButton(expectedAlertDescription)
                .assertTimerAlertButton(expectedTimerAlertDescription)
                .assertConfirmButton(expectedConfirmDescription)
                .assertPromtButton(expectedPromtDescription, inputPromtText);
    }

    @Test
    @DisplayName("Frames positive check")
    public void positiveFramesTest() {
        String expectedMainText = "Frames";
        int expectedFramesSize = 2;
        String expectedParentFrameText = "Parent frame";
        String expectedChildFrameText = "Child Thread QA iFrame :)";

        page.goToFrames()
                .assertMainText(expectedMainText)
                .assertFramesSize(expectedFramesSize)
                .assertFrameOne(expectedParentFrameText, expectedChildFrameText)
                .assertFrameTwo(expectedParentFrameText, expectedChildFrameText);
    }

    @Test
    @DisplayName("Nested Frames positive check")
    public void positiveNestedFramesTest() {
        String expectedMainText = "Nested Frames";
        int expectedFramesSize = 1;
        String expectedParentFrameText = "Parent frame";
        String expectedChildFrameText = "Child Thread QA iFrame :)";

        page.goToNestedFrames()
                .assertMainText(expectedMainText)
                .assertFramesSize(expectedFramesSize)
                .assertFrame(expectedParentFrameText, expectedChildFrameText);
    }

    @Test
    @DisplayName("Modal Dialogs positive check")
    public void positiveModalDialogsTest() {
        String expectedMainText = "Modal Dialogs";
        String expectedDescription = "Click on button to see modal";
        String expectedSmallModalButtonText = "Small modal";
        String expectedSmallModalHeaderText = "Small Modal";
        String expectedSmallModalBodyText = "This is a small modal. It has very less content";
        String expectedLargeModalButtonText = "Large modal";
        String expectedLargeModalHeaderText = "Large Modal";
        String expectedLargeModalBodyText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer" +
                " took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
                " centuries, but also the leap into electronic typesetting, remaining essentially unchanged." +
                " It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";

        page.goToModalDialogs()
                .assertMainText(expectedMainText)
                .assertDescriptionText(expectedDescription)
                .assertSmallModal(expectedSmallModalButtonText, expectedSmallModalHeaderText, expectedSmallModalBodyText)
                .assertLargeModal(expectedLargeModalButtonText, expectedLargeModalHeaderText, expectedLargeModalBodyText);
    }
}