package gusev.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class FramesPage {

    private static final String FRAME_SELECTOR = "iframe";

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement firstIFrame = $("iframe[src='https://threadqa.ru/sampleiframe.html']");
    private final SelenideElement secondIFrame = $$(FRAME_SELECTOR).get(1);

    @Step("Проверка заголовка страницы: ожидается, что содержит '{expectedText}'")
    public FramesPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка количества iframe: ожидается {expectedSize}")
    public FramesPage assertFramesSize(int expectedSize) {
        $$(FRAME_SELECTOR)
                .shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    @Step("Проверка содержимого первого iframe: внешний текст = '{expectedParentFrameText}', вложенный = '{expectedChildFrameText}'")
    public FramesPage assertFrameOne(String expectedParentFrameText, String expectedChildFrameText) {
        firstIFrame.shouldBe(Condition.visible);
        switchTo().frame(firstIFrame);
        $("body").shouldHave(Condition.text(expectedParentFrameText));

        switchTo().frame($(FRAME_SELECTOR));
        $("body").shouldHave(Condition.text(expectedChildFrameText));

        switchTo().defaultContent();
        return this;
    }

    @Step("Проверка содержимого второго iframe: внешний текст = '{expectedParentFrameText}', вложенный = '{expectedChildFrameText}'")
    public FramesPage assertFrameTwo(String expectedParentFrameText, String expectedChildFrameText) {
        secondIFrame.shouldBe(Condition.visible);
        switchTo().frame(secondIFrame);
        $("body").shouldHave(Condition.text(expectedParentFrameText));

        switchTo().frame($(FRAME_SELECTOR));
        $("body").shouldHave(Condition.text(expectedChildFrameText));

        switchTo().defaultContent();
        return this;
    }
}