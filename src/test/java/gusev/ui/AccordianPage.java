package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AccordianPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement section1Heading = $x("//*[@id='section1Heading']");
    private final SelenideElement section2Heading = $x("//*[@id='section2Heading']");
    private final SelenideElement section3Heading = $x("//*[@id='section3Heading']");
    private final SelenideElement section1Content = $x("//*[@id='section1Content']");
    private final SelenideElement section2Content = $x("//*[@id='section2Content']");
    private final SelenideElement section3Content = $x("//*[@id='section3Content']");

    public AccordianPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public AccordianPage assertFirstAccordian(String expectedAccordianTitle, String expectedAccordianContent) {
        section1Heading
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianTitle))
                .click();
        section1Content
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianContent));
        return this;
    }

    public AccordianPage assertSecondAccordian(String expectedAccordianTitle, String expectedAccordianContent) {
        section2Heading
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianTitle))
                .click();
        section2Content
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianContent));
        return this;
    }

    public AccordianPage assertThirdAccordian(String expectedAccordianTitle, String expectedAccordianContent) {
        section3Heading
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianTitle))
                .click();
        section3Content
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedAccordianContent));
        return this;
    }
}