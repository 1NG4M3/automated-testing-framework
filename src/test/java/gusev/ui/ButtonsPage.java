package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ButtonsPage {

    private final SelenideElement doubleClickBtn = $x("//*[@id='doubleClickBtn']");
    private final SelenideElement doubleClickMessage = $x("//*[@id='doubleClickMessage']");
    private final SelenideElement rightClickBtn = $x("//*[@id='rightClickBtn']");
    private final SelenideElement rightClickMessage = $x("//*[@id='rightClickMessage']");
    private final SelenideElement clickMeBtn = $x("/html/body/div/div/div/div[2]/div[2]/div[1]/div[3]/button");
    private final SelenideElement clickMeBtnMessage = $x("//*[@id='dynamicClickMessage']");

    @Step("Проверка двойного клика по кнопке: текст кнопки '{expectedButtonText}', ожидаемое сообщение '{expectedMessageAfterClick}'")
    public ButtonsPage assertDoubleClickButton(String expectedButtonText, String expectedMessageAfterClick) {
        doubleClickBtn.should(Condition.partialText(expectedButtonText)).doubleClick();
        doubleClickMessage.should(Condition.partialText(expectedMessageAfterClick));
        return this;
    }

    @Step("Проверка правого клика по кнопке: текст кнопки '{expectedButtonText}', ожидаемое сообщение '{expectedMessageAfterClick}'")
    public ButtonsPage assertRightClickButton(String expectedButtonText, String expectedMessageAfterClick) {
        rightClickBtn.should(Condition.partialText(expectedButtonText)).contextClick();
        rightClickMessage.should(Condition.partialText(expectedMessageAfterClick));
        return this;
    }

    @Step("Проверка обычного клика по кнопке: текст кнопки '{expectedButtonText}', ожидаемое сообщение '{expectedMessageAfterClick}'")
    public ButtonsPage assertClickMeButton(String expectedButtonText, String expectedMessageAfterClick) {
        clickMeBtn.should(Condition.partialText(expectedButtonText)).click();
        clickMeBtnMessage.should(Condition.partialText(expectedMessageAfterClick));
        return this;
    }
}