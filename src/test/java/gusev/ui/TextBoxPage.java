package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPage {

    private final SelenideElement fullNameInput = $x("//*[@id='userName']");
    private final SelenideElement emailInput = $x("//*[@id='userEmail']");
    private final SelenideElement currentAddressInput = $x("//*[@id='currentAddress']");
    private final SelenideElement permanentAddressInput = $x("//*[@id='permanentAddress']");
    private final SelenideElement submitButton = $x("//*[@id='submit']");

    private final SelenideElement outputName = $x("//*[@id='name']");
    private final SelenideElement outputEmail = $x("//*[@id='email']");
    private final SelenideElement outputCurrentAddress = $x("//*[@id='output']//*[@id='currentAddress']");
    private final SelenideElement outputPermanentAddress = $x("//*[@id='output']//*[@id='permanentAddress']");

    @Step("Заполнить поле Full Name: {value}")
    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);
        return this;
    }

    @Step("Заполнить поле Email: {value}")
    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Заполнить поле Current Address: {value}")
    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    @Step("Заполнить поле Permanent Address: {value}")
    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }

    @Step("Нажать на кнопку Submit")
    public TextBoxPage submit() {
        submitButton.click();
        return this;
    }

    @Step("Проверить имя: {expected}")
    public TextBoxPage assertName(String expected) {
        outputName.shouldHave(Condition.partialText(expected));
        return this;
    }

    @Step("Проверить email: {expected}")
    public TextBoxPage assertEmail(String expected) {
        outputEmail.shouldHave(Condition.partialText(expected));
        return this;
    }

    @Step("Проверить current address: {expected}")
    public TextBoxPage assertCurrentAddress(String expected) {
        outputCurrentAddress.shouldHave(Condition.partialText(expected));
        return this;
    }

    @Step("Проверить permanent address: {expected}")
    public TextBoxPage assertPermanentAddress(String expected) {
        outputPermanentAddress.shouldHave(Condition.partialText(expected));
        return this;
    }
}