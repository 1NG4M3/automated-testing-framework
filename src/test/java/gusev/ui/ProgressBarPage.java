package gusev.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement progressBar = $x("//*[@id='progressBar']");
    private final SelenideElement startStopButton = $x("//*[@id='startStopButton']");
    private final SelenideElement resetButton = $x("//*[@id='resetButton']");

    @Step("Проверка заголовка страницы: {expectedText}")
    public ProgressBarPage assertMainText(String expectedText) {
        mainText.shouldBe(visible)
                .shouldHave(partialText(expectedText));
        return this;
    }

    @Step("Проверка прогресс-бара: старт — {expectedButtonStartText}, стоп — {expectedButtonStopText}, сброс — {expectedButtonResetText}")
    public ProgressBarPage checkProgressBar(String expectedButtonStartText,
                                            String expectedButtonStopText,
                                            String expectedButtonResetText,
                                            String expectedFirstValue,
                                            String expectedSecondValue,
                                            Integer expectedTime) {
        progressBar.shouldBe(visible);

        clickStartStopButtonWithText(expectedButtonStartText);
        waitAndClickStop(expectedTime, expectedButtonStopText);
        assertProgressValue(expectedFirstValue);

        clickStartStopButtonWithText(expectedButtonStartText);
        Selenide.sleep(expectedTime);
        assertProgressValue(expectedSecondValue);

        assertResetButton(expectedButtonResetText);

        return this;
    }

    @Step("Нажимаем кнопку Start/Stop с текстом: {expectedText}")
    private void clickStartStopButtonWithText(String expectedText) {
        startStopButton.shouldHave(partialText(expectedText))
                .click();
    }

    @Step("Ждём {expectedTime} мс и нажимаем Stop с текстом: {expectedText}")
    private void waitAndClickStop(int expectedTime, String expectedText) {
        Selenide.sleep(expectedTime);
        startStopButton.shouldHave(partialText(expectedText))
                .click();
    }

    @Step("Проверка значения прогресс-бара: {expectedValue}")
    private void assertProgressValue(String expectedValue) {
        progressBar.shouldHave(partialText(expectedValue));
    }

    @Step("Проверка кнопки Reset с текстом: {expectedText}")
    private void assertResetButton(String expectedText) {
        resetButton.shouldBe(visible, enabled)
                .shouldHave(partialText(expectedText));
    }
}