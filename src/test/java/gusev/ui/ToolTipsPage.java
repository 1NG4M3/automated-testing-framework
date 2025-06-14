package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolTipsPage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement toolTipButton = $x("//*[@id='toolTipButton']");
    private final SelenideElement toolTipInputField = $x("//*[@id='toolTipTextField']");
    private final SelenideElement texToolTopContainer = $x("//*[@id='texToolTopContainer']");
    private final SelenideElement contraryLink = $x("//*[@id='texToolTopContainer']/a[1]");
    private final SelenideElement secondLink = $x("//*[@id='texToolTopContainer']/a[2]");
    private final SelenideElement toolTipInnerText = $(".tooltip-inner");

    public ToolTipsPage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public ToolTipsPage assertToolTipButton(String expectedButtonName, String expectedButtonToolTipText) {
        toolTipButton
                .shouldBe(visible)
                .should(Condition.partialText(expectedButtonName))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedButtonToolTipText));
        return this;
    }

    public ToolTipsPage assertInputField(String expectedFieldName, String expectedInputToolTipText, String expectedInputText) {
        toolTipInputField
                .shouldBe(visible)
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedInputToolTipText));
        String actualPlaceHolder = toolTipInputField.getAttribute("placeholder");
        assertEquals(expectedFieldName, actualPlaceHolder);
        toolTipInputField.setValue(expectedInputText);
        String actualValue = toolTipInputField.getValue();
        assertEquals(expectedInputText, actualValue);
        return this;
    }

    public ToolTipsPage assertMainText(String expectedIncludeText, String expectedContraryName, String expectedContraryToolTipText, String expectedSecondLinkName, String expectedSecondLinkToolTipText, String expectedColorOfLinks) {
        texToolTopContainer
                .shouldBe(visible)
                .should(Condition.partialText(expectedIncludeText));
        contraryLink
                .shouldBe(visible)
                .should(Condition.partialText(expectedContraryName))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedContraryToolTipText));
        secondLink
                .shouldBe(visible)
                .should(Condition.partialText(expectedSecondLinkName))
                .hover();
        toolTipInnerText
                .shouldBe(visible)
                .shouldHave(text(expectedSecondLinkToolTipText));
        String colorOfContrary = contraryLink.getCssValue("color");
        String colorOfSecondLink = secondLink.getCssValue("color");
        assertEquals(expectedColorOfLinks, colorOfContrary);
        assertEquals(expectedColorOfLinks, colorOfSecondLink);
        return this;
    }
}