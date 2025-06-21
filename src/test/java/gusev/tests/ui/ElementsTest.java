package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

@Tag("ui")
@Epic("Regression")
@Feature("UI")
@Story("Elements health check")
@Owner("Гусев Дмитрий Викторович")
public class ElementsTest extends BaseSelenideTest {

    private MainPage page;

    @BeforeEach
    public void beforeEach() {
        super.init();
        page = new MainPage();
    }

    @Test
    @DisplayName("Text box positive check")
    void positiveTextBoxTest() {
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

    @ParameterizedTest(name = "Check box check include {0} folder")
    @CsvSource({
            "Home,'You have selected :\nhome\ndesktop\nnotes\ncommands\ndocuments\nworkspace\nreact\nangular\nveu\noffice\npublic\nprivate\nclassified\ngeneral\ndownloads\nwordFile\nexcelFile'",
            "Desktop,'You have selected :\ndesktop\nnotes\ncommands'",
            "Documents,'You have selected :\ndocuments\nworkspace\nreact\nangular\nveu\noffice\npublic\nprivate\nclassified\ngeneral'",
            "Downloads,'You have selected :\ndownloads\nwordFile\nexcelFile'"
    })
    void positiveCheckBoxFolderTest(String folderName, String expectedText) {
        page.goToCheckBox();
        switch (folderName) {
            case "Home" -> page.goToCheckBox().assertHomeFolderText(expectedText);
            case "Desktop" -> page.goToCheckBox().assertDesktopFolderText(expectedText);
            case "Documents" -> page.goToCheckBox().assertDocumentsFolderText(expectedText);
            case "Downloads" -> page.goToCheckBox().assertDownloadsFolderText(expectedText);
        }
    }

    @Test
    @DisplayName("Radio button check main text")
    void positiveRadioButtonQuestionTest() {
        page.goToRadioButton()
                .assertRadioButtonMainText("Do you like the site?");
    }

    @ParameterizedTest(name = "Radio button check '{0}' field")
    @CsvSource({
            "Yes,'You have selected \nYes'",
            "Impressive,'You have selected \nImpressive'",
            "No,'No'"
    })
    void positiveRadioButtonCheck(String buttonType, String expectedText) {
        page.goToRadioButton();
        switch (buttonType) {
            case "Yes" -> page.goToRadioButton().assertYesButtonResultText(expectedText);
            case "Impressive" -> page.goToRadioButton().assertImpressiveButtonResultText(expectedText);
            case "No" -> page.goToRadioButton().assertNoButtonText(expectedText);
        }
    }

    @Test
    @DisplayName("Web tables positive check")
    void positiveWebTablesRegistrationFormTest() {
        String firstName = "Dmitry";
        String lastName = "Gusev";
        String email = "dmitryGusev@mail.ru";
        String age = "30";
        String salary = "300000";
        String department = "Development Department";

        page.goToWebTables()
                .assertMainHeader("Web Tables")
                .clickAddButton()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAge(age)
                .setSalary(salary)
                .setDepartment(department)
                .submit()
                .assertLastRow(firstName, lastName, email, age, salary, department)
                .deleteLastRow()
                .assertTableIsEmpty();
    }

    @Test
    @DisplayName("Buttons positive check")
    void positiveButtonsTest() {
        page.goToButtons()
                .assertDoubleClickButton("Double Click Me", "You have done a double click")
                .assertRightClickButton("Right Click Me", "You have done a right click")
                .assertClickMeButton("Click Me", "You have done a dynamic click");
    }

    @Test
    @DisplayName("Links positive check")
    void positiveLinksTest() {
        page.goToLinks()
                .assertMainTextWithNewTab("Following links will open new tab")
                .assertMainTextWithApiCall("Following links will send an api call")
                .assertHomeLink("Home")
                .assertDynamicLink("Home")
                .clickCreatedLink("Created");
    }

    @Test
    @DisplayName("Broken Links and Images positive check")
    void positiveBrokenLinksAndImagesTest() {
        page.goToBrokenLinksAndImages()
                .assertValidImageText("Valid image")
                .assertValidImage()
                .assertBrokenImageText("Broken image")
                .assertBrokenImage()
                .assertValidLinkText("Valid Link")
                .assertValidLink("Click Here for Valid Link", "https://www.google.com/")
                .assertBrokenLinkText("Broken Link")
                .assertBrokenLink("Click Here for Broken Link", "http://the-internet.herokuapp.com/status_codes/500", "https://the-internet.herokuapp.com/status_codes/500");
    }

    @Test
    @DisplayName("Upload and Download positive check")
    void positiveUploadAndDownloadTest() throws FileNotFoundException {
        page.goToUploadAndDownload()
                .assertDownloadFile("Download", "images/sticker.png")
                .assertSelectFileText("Select a file")
                .uploadFile()
                .assertUploadedFilePath("C:\\fakepath\\MyPhotoForUpload.jpeg");
    }

    @Test
    @DisplayName("Dynamic Properties visible button positive check")
    void positiveDynamicPropertiesVisibleButtonTest() {
        page.goToDynamicProperties()
                .assertMainText("This text has random Id")
                .assertVisibleAfterFiveSecondsButton("Visible After 5 Seconds");
    }

    @Test
    @DisplayName("Dynamic Properties enable button positive check")
    void positiveDynamicPropertiesEnableButtonTest() {
        page.goToDynamicProperties()
                .assertEnableFiveSecondsButton("Will enable 5 seconds");
    }

    @Test
    @DisplayName("Dynamic Properties color button positive check")
    void positiveDynamicPropertiesTest() {
        page.goToDynamicProperties()
                .assertColorChangeButton("Color Change");
    }
}