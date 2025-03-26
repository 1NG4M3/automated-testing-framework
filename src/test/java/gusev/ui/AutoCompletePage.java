package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public AutoCompletePage assertMainText(String expectedText) {
        mainText
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public AutoCompletePage assertAutoCompleteMultiple(String expectedAutoCompleteDescription, String expectedFirstFullColorName, String expectedSecondCutColorName, String expectedSecondFullColorName) {
        autoCompleteMultiple
                .shouldBe(visible)
                .should(Condition.partialText(expectedAutoCompleteDescription));
        autoCompleteMultipleInput.setValue(expectedFirstFullColorName).pressEnter();
        multipleContainer.findBy(text(expectedFirstFullColorName)).shouldBe(visible);
        autoCompleteMultipleInput.setValue(expectedSecondCutColorName).pressEnter();
        multipleContainer.findBy(text(expectedSecondFullColorName)).shouldBe(visible);
        return this;
    }

    public AutoCompletePage assertAutoCompleteSingle(String expectedAutoCompleteDescription, String expectedSecondFullColorName) {
        autoCompleteSingle
                .shouldBe(visible)
                .should(Condition.partialText(expectedAutoCompleteDescription));
        autoCompleteSingleInput.setValue(expectedSecondFullColorName).pressEnter();
        singleContainer
                .findBy(text(expectedSecondFullColorName))
                .shouldBe(visible)
                .shouldHave(cssValue("color", "rgba(51, 51, 51, 1)"));
        return this;
    }
}