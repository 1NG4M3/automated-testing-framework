package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SliderPage {
    private final SelenideElement header = $x("//*[@class='main-header']");
    private final SelenideElement sliderInput = $x("//*[@id='sliderContainer']/div[1]/span/input");
    private final SelenideElement sliderOutput = $x("//*[@id='sliderValue']");

    @Step("Проверка заголовка: {expectedText}")
    public SliderPage assertHeaderText(String expectedText) {
        header.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Установка значения слайдера: {value}")
    public SliderPage setSliderValue(String value) {
        sliderInput.shouldBe(visible).setValue(value);
        sliderOutput.shouldBe(visible).shouldHave(value(value));
        return this;
    }
}