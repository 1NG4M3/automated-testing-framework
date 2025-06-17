package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AlertsPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement alertDesc = $x("//*[@id='javascriptAlertsWrapper']/div[1]/div[1]");
    private final SelenideElement timerAlertDesc = $x("//*[@id='javascriptAlertsWrapper']/div[2]/div[1]");
    private final SelenideElement confirmButtonDesc = $x("//*[@id='javascriptAlertsWrapper']/div[3]/div[1]");
    private final SelenideElement promtButtonDesc = $x("//*[@id='javascriptAlertsWrapper']/div[4]/div[1]");
    private final SelenideElement alertButton = $x("//*[@id='alertButton']");
    private final SelenideElement timerAlertButton = $x("//*[@id='timerAlertButton']");
    private final SelenideElement confirmButton = $x("//*[@id='confirmButton']");
    private final SelenideElement promtButton = $x("//*[@id='promtButton']");

    @Step("Проверка заголовка страницы: должен содержать '{expectedText}'")
    public AlertsPage assertMainText(String expectedText) {
        mainText.shouldBe(visible)
                .shouldHave(partialText(expectedText));
        return this;
    }

    @Step("Проверка обычного Alert окна")
    public AlertsPage assertAlertButton(String expectedText) {
        return assertAlertSection(alertDesc, alertButton, expectedText, AlertAction.SIMPLE, null);
    }

    @Step("Проверка отложенного Alert окна")
    public AlertsPage assertTimerAlertButton(String expectedText) {
        return assertAlertSection(timerAlertDesc, timerAlertButton, expectedText, AlertAction.TIMER, null);
    }

    @Step("Проверка Confirm Alert окна")
    public AlertsPage assertConfirmButton(String expectedText) {
        return assertAlertSection(confirmButtonDesc, confirmButton, expectedText, AlertAction.CONFIRM, null);
    }

    @Step("Проверка Prompt Alert окна с вводом '{inputName}'")
    public AlertsPage assertPromtButton(String expectedText, String inputName) {
        return assertAlertSection(promtButtonDesc, promtButton, expectedText, AlertAction.PROMPT, inputName);
    }

    @Step("Общая логика для обработки alert окон")
    private AlertsPage assertAlertSection(SelenideElement description,
                                          SelenideElement button,
                                          String expectedText,
                                          AlertAction actionType,
                                          String inputText) {
        description.shouldBe(visible)
                .shouldHave(partialText(expectedText));
        button.shouldBe(enabled).click();

        switch (actionType) {
            case SIMPLE -> Selenide.confirm();
            case TIMER -> {
                Selenide.sleep(6000);
                Selenide.confirm();
            }
            case CONFIRM -> {
                Selenide.confirm();
                description.shouldHave(partialText(expectedText + "You selected Ok"));
            }
            case PROMPT -> {
                Selenide.prompt(inputText);
                description.shouldHave(partialText(expectedText + "You entered " + inputText));
            }
        }
        return this;
    }

    private enum AlertAction {
        SIMPLE, TIMER, CONFIRM, PROMPT
    }
}