package gusev.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class FramesPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement firstIFrame = $("iframe[src='https://threadqa.ru/sampleiframe.html']");
    private final SelenideElement secondIFrame = $$("iframe").get(1);

    public FramesPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public FramesPage assertFramesSize(int expectedSize) {
        $$("iframe").shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    public FramesPage assertFrameOne(String expectedParentFrameText, String expectedChildFrameText) {
        firstIFrame.shouldBe(Condition.visible);
        switchTo().frame(firstIFrame);
        $("body").shouldHave(Condition.text(expectedParentFrameText));
        switchTo().frame($("iframe"));
        $("body").shouldHave(Condition.text(expectedChildFrameText));
        switchTo().defaultContent();
        return this;
    }

    public FramesPage assertFrameTwo(String expectedParentFrameText, String expectedChildFrameText) {
        secondIFrame.shouldBe(Condition.visible);
        switchTo().frame(secondIFrame);
        $("body").shouldHave(Condition.text(expectedParentFrameText));
        switchTo().frame($("iframe"));
        $("body").shouldHave(Condition.text(expectedChildFrameText));
        switchTo().defaultContent();
        return this;
    }
}