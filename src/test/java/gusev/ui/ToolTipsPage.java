package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolTipsPage {

    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement toolTipButton = $x("//*[@id='toolTipButton']");
    private final SelenideElement toolTipInputField = $x("//*[@id='toolTipTextField']");
    private final SelenideElement textToolTopContainer = $x("//*[@id='texToolTopContainer']");
    private final SelenideElement contraryLink = $x("//*[@id='texToolTopContainer']/a[1]");
    private final SelenideElement secondLink = $x("//*[@id='texToolTopContainer']/a[2]");
    private final SelenideElement toolTipInnerText = $(".tooltip-inner");

    @Step("Проверить заголовок: {expectedHeaderName}")
    public ToolTipsPage assertHeaderName(String expectedHeaderName) {
        headerText.shouldBe(visible).shouldHave(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверить tooltip у кнопки: '{expectedButtonName}' с текстом '{expectedToolTipText}'")
    public ToolTipsPage assertToolTipButton(String expectedButtonName, String expectedToolTipText) {
        toolTipButton
                .shouldBe(visible)
                .shouldHave(Condition.partialText(expectedButtonName))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedToolTipText));
        return this;
    }

    @Step("Проверить tooltip у поля ввода с placeholder: '{expectedFieldName}' и текстом '{expectedToolTipText}'")
    public ToolTipsPage assertInputField(String expectedFieldName, String expectedToolTipText, String inputText) {
        toolTipInputField.shouldBe(visible).hover();
        toolTipInnerText.shouldBe(visible).shouldHave(text(expectedToolTipText));

        String actualPlaceholder = toolTipInputField.getAttribute("placeholder");
        assertEquals(expectedFieldName, actualPlaceholder, "Placeholder не совпадает");

        toolTipInputField.setValue(inputText);
        assertEquals(inputText, toolTipInputField.getValue(), "Введённое значение не совпадает");

        return this;
    }

    @Step("Проверить текстовый контейнер с ссылками и tooltip'ами")
    public ToolTipsPage assertMainText(String expectedIncludeText,
                                       String expectedContraryText, String expectedContraryTooltip,
                                       String expectedSecondText, String expectedSecondTooltip,
                                       String expectedLinkColor) {

        textToolTopContainer
                .shouldBe(visible)
                .shouldHave(Condition.partialText(expectedIncludeText));

        contraryLink
                .shouldBe(visible)
                .shouldHave(Condition.partialText(expectedContraryText))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedContraryTooltip));

        secondLink
                .shouldBe(visible)
                .shouldHave(Condition.partialText(expectedSecondText))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedSecondTooltip));

        assertEquals(expectedLinkColor, contraryLink.getCssValue("color"), "Цвет первой ссылки не совпадает");
        assertEquals(expectedLinkColor, secondLink.getCssValue("color"), "Цвет второй ссылки не совпадает");

        return this;
    }
}