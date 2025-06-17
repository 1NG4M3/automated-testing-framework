package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RadioButtonPage {

    private final SelenideElement radioButtonQuestionText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]");
    private final SelenideElement yesButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[2]");
    private final SelenideElement impressiveButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]");
    private final SelenideElement noButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[4]");
    private final SelenideElement displayResultField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p");

    @Step("Проверка текста вопроса в радио-кнопках: {expectedResultText}")
    public RadioButtonPage assertRadioButtonMainText(String expectedResultText) {
        radioButtonQuestionText.should(Condition.visible)
                .shouldHave(Condition.partialText(expectedResultText));
        return this;
    }

    @Step("Нажимаем кнопку 'Yes' и проверяем результат: {expectedResultText}")
    public RadioButtonPage assertYesButtonResultText(String expectedResultText) {
        clickAndCheckResult(yesButton, expectedResultText);
        return this;
    }

    @Step("Нажимаем кнопку 'Impressive' и проверяем результат: {expectedResultText}")
    public RadioButtonPage assertImpressiveButtonResultText(String expectedResultText) {
        clickAndCheckResult(impressiveButton, expectedResultText);
        return this;
    }

    @Step("Проверяем, что кнопка 'No' содержит текст: {expectedButtonText}")
    public RadioButtonPage assertNoButtonText(String expectedButtonText) {
        noButton.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedButtonText));
        return this;
    }

    @Step("Кликаем по кнопке и проверяем текст результата: {expectedText}")
    private void clickAndCheckResult(SelenideElement button, String expectedText) {
        button.shouldBe(Condition.visible, Condition.enabled).click();
        displayResultField.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
    }
}