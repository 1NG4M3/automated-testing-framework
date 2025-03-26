package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

@Epic("Regression")
@Feature("UI")
@Story("Elements health check")
@Owner("Гусев Дмитрий Викторович")
public class ElementsTest extends BaseSelenideTest {

    private MainPage page = new MainPage();

    @Test
    @DisplayName("Text box positive check")
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
    @DisplayName("Check box check, include all folders and files")
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
    @DisplayName("Check box check include Desktop folder")
    public void positiveCheckBoxDesktopFolderTest() {
        String expectedResultDesktop = "You have selected :\n" +
                "desktop\n" +
                "notes\n" +
                "commands";

        page.goToCheckBox()
                .assertDesktopFolderText(expectedResultDesktop);
    }

    @Test
    @DisplayName("Check box check include Documents folder")
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
    @DisplayName("Check box check include Downloads folder")
    public void positiveCheckBoxDownloadsFolderTest() {
        String expectedResultDownloads = "You have selected :\n" +
                "downloads\n" +
                "wordFile\n" +
                "excelFile";

        page.goToCheckBox()
                .assertDownloadsFolderText(expectedResultDownloads);
    }

    @Test
    @DisplayName("Radio button check main text")
    public void positiveRadioButtonQuestionTest() {
        String expectedQuestionText = "Do you like the site?";

        page.goToRadioButton()
                .assertRadioButtonMainText(expectedQuestionText);
    }

    @Test
    @DisplayName("Radio button check 'Yes' field")
    public void positiveYesButtonResultTextTest() {
        String expectedResultText = "You have selected \n" +
                "Yes";

        page.goToRadioButton()
                .assertYesButtonResultText(expectedResultText);
    }

    @Test
    @DisplayName("Radio button check 'Impressive' field")
    public void positiveImpressiveButtonResultTextTest() {
        String expectedResultText = "You have selected \n" +
                "Impressive";

        page.goToRadioButton()
                .assertImpressiveButtonResultText(expectedResultText);
    }

    @Test
    @DisplayName("Radio button check 'No' field")
    public void positiveNoButtonTextTest() {
        String expectedText = "No";

        page.goToRadioButton()
                .assertNoButtonText(expectedText);
    }

    @Test
    @DisplayName("Web tables positive check")
    public void positiveWebTablesRegistrationFormTest() {
        String expectedText = "Web Tables";
        String expectedFirstName = "Dmitry";
        String expectedLastName = "Gusev";
        String expectedEmail = "dmitryGusev@mail.ru";
        String expectedAge = "30";
        String expectedSalary = "300000";
        String expectedDepartment = "Development Department";

        page.goToWebTables()
                .assertWebTablesMainText(expectedText)
                .clickAddButton()
                .setFirstName(expectedFirstName)
                .setLastName(expectedLastName)
                .setEmail(expectedEmail)
                .setAge(expectedAge)
                .setSalary(expectedSalary)
                .setDepartment(expectedDepartment)
                .submit()
                .assertWebTableInputData(expectedFirstName, expectedLastName, expectedEmail, expectedAge, expectedSalary, expectedDepartment)
                .deleteRecord()
                .assertThatRecordDeleted();
    }

    @Test
    @DisplayName("Buttons positive check")
    public void positiveButtonsTest() {
        String expectedDoubleClickButtonText = "Double Click Me";
        String expectedDoubleClickMessage = "You have done a double click";
        String expectedRightClickButtonText = "Right Click Me";
        String expectedRightClickMessage = "You have done a right click";
        String expectedClickMeButtonText = "Click Me";
        String expectedClickMeMessage = "You have done a dynamic click";

        page.goToButtons()
                .assertDoubleClickButton(expectedDoubleClickButtonText, expectedDoubleClickMessage)
                .assertRightClickButton(expectedRightClickButtonText, expectedRightClickMessage)
                .assertClickMeButton(expectedClickMeButtonText, expectedClickMeMessage);
    }

    @Test
    @DisplayName("Links positive check")
    public void positiveLinksTest() {
        String expectedMainTextWithNewTab = "Following links will open new tab";
        String expectedTextWithApiCall = "Following links will send an api call";
        String expectedHomeLinkText = "Home";
        String expectedCreatedApiCallText = "Created";

        page.goToLinks()
                .assertMainTextWithNewTab(expectedMainTextWithNewTab)
                .assertMainTextWithApiCall(expectedTextWithApiCall)
                .assertHomeLink(expectedHomeLinkText)
                .assertDynamicLink(expectedHomeLinkText)
                .clickCreatedLink(expectedCreatedApiCallText);
    }

    @Test
    @DisplayName("Broken Links and Images positive check")
    public void positiveBrokenLinksAndImagesTest() {
        String expectedValidImageText = "Valid image";
        String expectedBrokenImageText = "Broken image";
        String expectedValidLnkText = "Valid Link";
        String expectedValidLink = "Click Here for Valid Link";
        String expectedValidHref = "https://www.google.com/";
        String expectedBrokenLinkText = "Broken Link";
        String expectedBrokenLink = "Click Here for Broken Link";
        String expectedBrokenHref = "http://the-internet.herokuapp.com/status_codes/500";
        String expectedBrokenHrefAfterClick = "https://the-internet.herokuapp.com/status_codes/500";

        page.goToBrokenLinksAndImages()
                .assertValidImageText(expectedValidImageText)
                .assertValidImage()
                .assertBrokenImageText(expectedBrokenImageText)
                .assertBrokenImage()
                .assertValidLinkText(expectedValidLnkText)
                .assertValidLink(expectedValidLink, expectedValidHref)
                .assertBrokenLinkText(expectedBrokenLinkText)
                .assertBrokenLink(expectedBrokenLink, expectedBrokenHref, expectedBrokenHrefAfterClick);
    }

    @Test
    @DisplayName("Upload and Download positive check")
    public void positiveUploadAndDownloadTest() throws FileNotFoundException {
        String expectedDownloadButtonText = "Download";
        String expectedHref = "images/sticker.png";
        String expectedSelectFileText = "Select a file";
        String expectedUploadedFilePath = "C:\\fakepath\\MyPhotoForUpload.jpeg";

        page.goToUploadAndDownload()
                .assertDownloadFile(expectedDownloadButtonText, expectedHref)
                .assertSelectFileText(expectedSelectFileText)
                .assertUploadFile()
                .assertUploadedFilePath(expectedUploadedFilePath);
    }

    @Test
    @DisplayName("Dynamic Properties visible button positive check")
    public void positiveDynamicPropertiesVisibleButtonTest() {
        String expectedMainText = "This text has random Id";
        String expectedVisibleButtonText = "Visible After 5 Seconds";

        page.goToDynamicProperties()
                .assertMainText(expectedMainText)
                .assertVisibleAfterFiveSecondsButton(expectedVisibleButtonText);
    }

    @Test
    @DisplayName("Dynamic Properties enable button positive check")
    public void positiveDynamicPropertiesEnableButtonTest() {
        String expectedEnableButtonText = "Will enable 5 seconds";

        page.goToDynamicProperties()
                .assertEnableFiveSecondsButton(expectedEnableButtonText);
    }

    @Test
    @DisplayName("Dynamic Properties color button positive check")
    public void positiveDynamicPropertiesTest() {
        String expectedColorButtonText = "Color Change";

        page.goToDynamicProperties()
                .assertColorChangeButton(expectedColorButtonText);
    }
}