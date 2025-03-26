package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import static com.codeborne.selenide.Selenide.$x;

public class DynamicPropertiesPage {
    private final SelenideElement mainText = $x("//*[contains(text(), 'This text has random Id')]");
    private final SelenideElement enableFiveSeconds = $x("//*[@id='enableAfter']");
    private final SelenideElement colorChange = $x("//*[@id='colorChange']");
    private final SelenideElement visibleAfterFiveSeconds = $x("//*[@id='visibleAfter']");

    public DynamicPropertiesPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public DynamicPropertiesPage assertEnableFiveSecondsButton(String expectedText) {
        enableFiveSeconds
                .should(Condition.partialText(expectedText))
                .shouldBe(Condition.visible)
                .shouldBe(Condition.enabled, Duration.ofSeconds(6));
        return this;
    }

    public DynamicPropertiesPage assertColorChangeButton(String expectedText) {
        colorChange
                .should(Condition.partialText(expectedText))
                .shouldBe(Condition.visible)
                .shouldHave(Condition.cssValue("color", "rgba(220, 53, 69, 1)"), Duration.ofSeconds(6));
        return this;
    }

    public DynamicPropertiesPage assertVisibleAfterFiveSecondsButton(String expectedText) {
        visibleAfterFiveSeconds
                .shouldBe(Condition.visible, Duration.ofSeconds(6))
                .should(Condition.partialText(expectedText));
        return this;
    }
}