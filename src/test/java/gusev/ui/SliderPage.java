package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SliderPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement sliderRange = $x("//*[@id='sliderContainer']/div[1]/span/input");
    private final SelenideElement sliderValue = $x("//*[@id='sliderValue']");

    public SliderPage assertMainText(String expectedText) {
        mainText
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public SliderPage assertSliderValue(String expectedValue) {
        sliderRange
                .shouldBe(visible)
                .setValue(expectedValue);
        sliderValue
                .shouldBe(visible)
                .shouldHave(value(expectedValue));
        return this;
    }
}