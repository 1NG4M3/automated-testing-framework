package gusev.ui;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class AccordianPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement section1Heading = $x("//*[@id='section1Heading']");
    private final SelenideElement section2Heading = $x("//*[@id='section2Heading']");
    private final SelenideElement section3Heading = $x("//*[@id='section3Heading']");
    private final SelenideElement section1Content = $x("//*[@id='section1Content']");
    private final SelenideElement section2Content = $x("//*[@id='section2Content']");
    private final SelenideElement section3Content = $x("//*[@id='section3Content']");

    @Step("Проверка основного заголовка Accordian: должен содержать текст '{expectedText}'")
    public AccordianPage assertMainText(String expectedText) {
        mainText.shouldBe(visible)
                .shouldHave(partialText(expectedText));
        return this;
    }

    @Step("Проверка первого аккордеона")
    public AccordianPage assertFirstAccordian(String expectedTitle, String expectedContent) {
        return assertAccordianSection(section1Heading, section1Content, expectedTitle, expectedContent);
    }

    @Step("Проверка второго аккордеона")
    public AccordianPage assertSecondAccordian(String expectedTitle, String expectedContent) {
        return assertAccordianSection(section2Heading, section2Content, expectedTitle, expectedContent);
    }

    @Step("Проверка третьего аккордеона")
    public AccordianPage assertThirdAccordian(String expectedTitle, String expectedContent) {
        return assertAccordianSection(section3Heading, section3Content, expectedTitle, expectedContent);
    }

    @Step("Проверка секции аккордеона: заголовок '{expectedTitle}', содержимое '{expectedContent}'")
    private AccordianPage assertAccordianSection(SelenideElement heading, SelenideElement content,
                                                 String expectedTitle, String expectedContent) {
        heading.shouldBe(visible, enabled)
                .shouldHave(partialText(expectedTitle))
                .click();
        content.shouldBe(visible)
                .shouldHave(partialText(expectedContent));
        return this;
    }
}