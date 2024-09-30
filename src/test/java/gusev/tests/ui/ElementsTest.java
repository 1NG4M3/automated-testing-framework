package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Epic("Regression")
@Feature("UI")
@Story("Elements health check")
public class ElementsTest extends BaseSelenideTest {

    private MainPage page = new MainPage();

    @Test
    public void positiveTextBoxTest() {
        String name = "Dmitry Gusev";
        String email = "testemail@mail.ru";
        String currentAddress = "Saint P.";
        String permanentAddress = "Saint P. Russia";

        page.goToTextBox()
                .setFullName(name)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .submit()
                .assertName(name)
                .assertEmail(email)
                .assertCurrentAddress(currentAddress)
                .assertPermanentAddress(permanentAddress);
    }

    @Test
    public void positiveCheckBoxHomeFolderTest() {
        String expectedResultFolders = "You have selected :\n" +
                "home\n" +
                "desktop\n" +
                "notes\n" +
                "commands\n" +
                "documents\n" +
                "workspace\n" +
                "react\n" +
                "angular\n" +
                "veu\n" +
                "office\n" +
                "public\n" +
                "private\n" +
                "classified\n" +
                "general\n" +
                "downloads\n" +
                "wordFile\n" +
                "excelFile";
        page.goToCheckBox()
                .assertHomeFolderText(expectedResultFolders);
    }

    @Test
    public void positiveCheckBoxDesktopFolderTest() {
        String expectedResultDesktop = "You have selected :\n" +
                "desktop\n" +
                "notes\n" +
                "commands";
        page.goToCheckBox()
                .assertDesktopFolderText(expectedResultDesktop);
    }

    @Test
    public void positiveCheckBoxDocumentsFolderTest() {
        String expectedResultDocuments = "You have selected :\n" +
                "documents\n" +
                "workspace\n" +
                "react\n" +
                "angular\n" +
                "veu\n" +
                "office\n" +
                "public\n" +
                "private\n" +
                "classified\n" +
                "general";
        page.goToCheckBox()
                .assertDocumentsFolderText(expectedResultDocuments);
    }

    @Test
    public void positiveCheckBoxDownloadsFolderTest() {
        String expectedResultDownloads = "You have selected :\n" +
                "downloads\n" +
                "wordFile\n" +
                "excelFile";
        page.goToCheckBox()
                .assertDownloadsFolderText(expectedResultDownloads);
    }

    @Test
    public void positiveRadioButtonQuestionTest() {
        String expectedQuestionText = "Do you like the site?";
        page.goToRadioButton()
                .assertRadioButtonMainText(expectedQuestionText);
    }

    @Test
    public void positiveYesButtonResultTextTest() {
        String expectedResultText = "You have selected \n" +
                "Yes";
        page.goToRadioButton()
                .assertYesButtonResultText(expectedResultText);
    }

    @Test
    public void positiveImpressiveButtonResultTextTest() {
        String expectedResultText = "You have selected \n" +
                "Impressive";
        page.goToRadioButton()
                .assertImpressiveButtonResultText(expectedResultText);
    }

    @Test
    public void positiveNoButtonTextTest() {
        String expectedText = "No";
        page.goToRadioButton()
                .assertNoButtonText(expectedText);
    }
}