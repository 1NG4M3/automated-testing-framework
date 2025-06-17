package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Проверка заголовка страницы: {expectedText}")
    public PracticeFormPage assertMainText(String expectedText) {
        mainText.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка названия формы: {expectedText}")
    public PracticeFormPage assertFormName(String expectedText) {
        formName.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Ввод имени: {firstNameValue}")
    public PracticeFormPage setFirstName(String firstNameValue) {
        firstName.shouldBe(Condition.visible, Condition.enabled)
                .setValue(firstNameValue);
        return this;
    }

    @Step("Ввод фамилии: {lastNameValue}")
    public PracticeFormPage setLastName(String lastNameValue) {
        lastName.shouldBe(Condition.visible, Condition.enabled)
                .setValue(lastNameValue);
        return this;
    }

    @Step("Ввод email: {userEmailValue}")
    public PracticeFormPage setUserEmail(String userEmailValue) {
        userEmail.shouldBe(Condition.visible, Condition.enabled)
                .setValue(userEmailValue);
        return this;
    }

    @Step("Выбор пола: {genderValue}")
    public PracticeFormPage setGender(String genderValue) {
        gender.shouldBe(Condition.visible, Condition.enabled)
                .shouldHave(Condition.partialText(genderValue))
                .click();
        return this;
    }

    @Step("Ввод номера телефона: {userNumberValue}")
    public PracticeFormPage setUserNumber(String userNumberValue) {
        userNumber.shouldBe(Condition.visible, Condition.enabled)
                .setValue(userNumberValue);
        return this;
    }

    @Step("Установка даты рождения")
    public PracticeFormPage setDateOfBirth() {
        dateOfBirthInput.shouldBe(Condition.visible, Condition.enabled)
                .click();
        $(".react-datepicker__year-select").selectOptionByValue("2025");
        $(".react-datepicker__month-select").selectOptionByValue("11");
        $(".react-datepicker__day--002").click();
        return this;
    }

    @Step("Ввод предмета: {subjectsValue}")
    public PracticeFormPage setSubjects(String subjectsValue) {
        subjects.shouldBe(Condition.visible, Condition.enabled)
                .setValue(subjectsValue)
                .pressEnter();
        return this;
    }

    @Step("Выбор хобби")
    public PracticeFormPage setHobby() {
        hobbySports.shouldBe(Condition.enabled)
                .click();
        return this;
    }

    @Step("Загрузка изображения")
    public PracticeFormPage setPicture() {
        File fileToUpload = new File("src/test/resources/filesRq/MyPhotoForUpload.jpeg");
        uploadPicture.shouldBe(Condition.visible, Condition.enabled)
                .uploadFile(fileToUpload);
        return this;
    }

    @Step("Ввод текущего адреса: {currentAddressValue}")
    public PracticeFormPage setCurrentAddress(String currentAddressValue) {
        currentAddress.shouldBe(Condition.visible, Condition.enabled)
                .setValue(currentAddressValue);
        return this;
    }

    @Step("Выбор штата: {stateValue}")
    public PracticeFormPage setState(String stateValue) {
        state.shouldBe(Condition.visible)
                .click();
        $x("//div[text()='NCR']").click();
        state.shouldHave(Condition.partialText(stateValue));
        return this;
    }

    @Step("Выбор города: {cityValue}")
    public PracticeFormPage setCity(String cityValue) {
        city.shouldBe(Condition.visible)
                .click();
        $x("//div[text()='Delhi']").click();
        city.shouldHave(Condition.partialText(cityValue));
        return this;
    }

    @Step("Отправка формы")
    public PracticeFormPage submit() {
        submitButton.shouldBe(Condition.visible, Condition.enabled)
                .click();
        return this;
    }

    @Step("Проверка содержимого модального окна с данными")
    public PracticeFormPage assertModalContent(String expectedFirstName, String expectedLastName, String expectedEmail,
                                               String expectedGender, String expectedMobileNumber,
                                               String expectedDateOfBirth, String expectedSubjects, String expectedHobby,
                                               String expectedPicturePath, String expectedCurrentAddress,
                                               String expectedState, String expectedCity) {
        modalContent.shouldBe(Condition.visible)
                .shouldHave(
                        text(expectedFirstName + " " + expectedLastName),
                        text(expectedEmail),
                        text(expectedGender),
                        text(expectedMobileNumber),
                        text(expectedDateOfBirth),
                        text(expectedSubjects),
                        text(expectedHobby),
                        text(expectedPicturePath),
                        text(expectedCurrentAddress),
                        text(expectedState + " " + expectedCity)
                );
        closeButton.click();
        return this;
    }
}