package gusev.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class AutoCompletePage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement autoCompleteMultiple = $x("//*[@id='autoCompleteMultiple']");
    private final SelenideElement autoCompleteMultipleInput = $x("//*[@id='autoCompleteMultipleInput']");
    private final SelenideElement autoCompleteSingle = $x("//*[@id='autoCompleteSingle']");
    private final SelenideElement autoCompleteSingleInput = $x("//*[@id='autoCompleteSingleInput']");
    private final ElementsCollection multipleContainer = $$("div.auto-complete__multi-value");
    private final ElementsCollection singleContainer = $$("div.auto-complete__single-value");

    @Step("Проверить основной заголовок страницы содержит текст: {expectedText}")
    public AutoCompletePage assertMainText(String expectedText) {
        mainText
                .shouldBe(visible)
                .shouldHave(partialText(expectedText));
        return this;
    }

    @Step("Проверить автозаполнение для мультиввода с описанием: {expectedAutoCompleteDescription}")
    public AutoCompletePage assertAutoCompleteMultiple(String expectedAutoCompleteDescription,
                                                       String expectedFirstFullColorName,
                                                       String expectedSecondCutColorName,
                                                       String expectedSecondFullColorName) {
        autoCompleteMultiple
                .shouldBe(visible)
                .shouldHave(partialText(expectedAutoCompleteDescription));

        autoCompleteMultipleInput.setValue(expectedFirstFullColorName).pressEnter();
        multipleContainer.findBy(text(expectedFirstFullColorName)).shouldBe(visible);

        autoCompleteMultipleInput.setValue(expectedSecondCutColorName).pressEnter();
        multipleContainer.findBy(text(expectedSecondFullColorName)).shouldBe(visible);

        return this;
    }

    @Step("Проверить автозаполнение для одного значения с описанием: {expectedAutoCompleteDescription}")
    public AutoCompletePage assertAutoCompleteSingle(String expectedAutoCompleteDescription,
                                                     String expectedSecondFullColorName) {
        autoCompleteSingle
                .shouldBe(visible)
                .shouldHave(partialText(expectedAutoCompleteDescription));

        autoCompleteSingleInput.setValue(expectedSecondFullColorName).pressEnter();
        singleContainer
                .findBy(text(expectedSecondFullColorName))
                .shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(51, 51, 51, 1)"));

        return this;
    }
}