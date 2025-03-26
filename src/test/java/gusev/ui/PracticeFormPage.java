package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement formName = $x("//*[@class='practice-form-wrapper']");
    private final SelenideElement firstName = $x("//*[@id='firstName']");
    private final SelenideElement lastName = $x("//*[@id='lastName']");
    private final SelenideElement userEmail = $x("//*[@id='userEmail']");
    private final SelenideElement gender = $x("//*[contains(text(), 'Male')]");
    private final SelenideElement userNumber = $x("//*[@id='userNumber']");
    private final SelenideElement dateOfBirthInput = $x("//*[@id='dateOfBirthInput']");
    private final SelenideElement subjects = $x("//*[@id='subjectsInput']");
    private final SelenideElement hobbySports = $x("//*[contains(text(), 'Sports')]");
    private final SelenideElement uploadPicture = $x("//*[@id='uploadPicture']");
    private final SelenideElement currentAddress = $x("//*[@id='currentAddress']");
    private final SelenideElement state = $x("//*[@id='state']");
    private final SelenideElement city = $x("//*[@id='city']");
    private final SelenideElement submitButton = $x("//*[@id='submit']");
    private final SelenideElement modalContent = $(".modal-content");
    private final SelenideElement closeButton = $x("//*[@id='closeLargeModal']");

    public PracticeFormPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public PracticeFormPage assertFormName(String expectedText) {
        formName
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public PracticeFormPage setFirstName(String firstNameValue) {
        firstName
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(firstNameValue);
        return this;
    }

    public PracticeFormPage setLastName(String lastNameValue) {
        lastName
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(lastNameValue);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmailValue) {
        userEmail
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(userEmailValue);
        return this;
    }

    public PracticeFormPage setGender(String genderValue) {
        gender
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .should(Condition.partialText(genderValue))
                .click();
        return this;
    }

    public PracticeFormPage setUserNumber(String userNumberValue) {
        userNumber
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(userNumberValue);
        return this;
    }

    public PracticeFormPage setDateOfBirth() {
        dateOfBirthInput
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();
        $(".react-datepicker__year-select").selectOptionByValue("2025");
        $(".react-datepicker__month-select").selectOptionByValue("11");
        $(".react-datepicker__day--002").click();
        return this;
    }

    public PracticeFormPage setSubjects(String subjectsValue) {
        subjects
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(subjectsValue)
                .pressEnter();
        return this;
    }

    public PracticeFormPage setHobby() {
        hobbySports
                .shouldBe(Condition.enabled)
                .click();
        return this;
    }

    public PracticeFormPage setPicture() {
        File fileToUpload = new File("src/test/resources/filesRq/MyPhotoForUpload.jpeg");
        uploadPicture
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .uploadFile(fileToUpload);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String currentAddressValue) {
        currentAddress
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .setValue(currentAddressValue);
        return this;
    }

    public PracticeFormPage setState(String stateValue) {
        state
                .shouldBe(Condition.visible)
                .click();
        $x("//div[text()='NCR']").click();
        state.should(Condition.partialText(stateValue));
        return this;
    }

    public PracticeFormPage setCity(String cityValue) {
        city
                .shouldBe(Condition.visible)
                .click();
        $x("//div[text()='Delhi']").click();
        city.should(Condition.partialText(cityValue));
        return this;
    }

    public PracticeFormPage submit() {
        submitButton
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .click();
        return this;
    }

    public PracticeFormPage assertModalContent(String expectedFirstName, String expectedLastName, String expectedEmail,
                                               String expectedGender, String expectedMobileNumber,
                                               String expectedDateOfBirth, String expectedSubjects, String expectedHobby,
                                               String expectedPicturePath, String expectedCurrentAddress, String expectedState, String expectedCity) {
        modalContent
                .shouldBe(Condition.visible)
                .shouldHave(text(expectedFirstName.concat(" ").concat(expectedLastName)),
                        text(expectedEmail),
                        text(expectedGender),
                        text(expectedMobileNumber),
                        text(expectedDateOfBirth),
                        text(expectedSubjects),
                        text(expectedHobby),
                        text(expectedPicturePath),
                        text(expectedCurrentAddress),
                        text(expectedState.concat(" ").concat(expectedCity)));
        closeButton.click();
        return this;
    }
}