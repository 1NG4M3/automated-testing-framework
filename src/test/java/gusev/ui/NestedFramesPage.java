package gusev.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class NestedFramesPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement frame = $("iframe[src='https://threadqa.ru/sampleiframe.html']");

    public NestedFramesPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public NestedFramesPage assertFramesSize(int expectedSize) {
        $$("iframe").shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    public NestedFramesPage assertFrame(String expectedParentFrameText, String expectedChildFrameText) {
        frame.shouldBe(Condition.visible);
        switchTo().frame(frame);
        $("body")
                .shouldHave(Condition.text(expectedParentFrameText));
        switchTo().frame($("iframe"));
        $("body")
                .shouldHave(Condition.text(expectedChildFrameText));
        switchTo().defaultContent();
        return this;
    }
}