package gusev.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class NestedFramesPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement parentFrame = $("iframe[src='https://threadqa.ru/sampleiframe.html']");

    @Step("Проверка заголовка страницы: ожидается '{expectedText}'")
    public NestedFramesPage assertMainText(String expectedText) {
        mainText.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка количества фреймов: ожидается {expectedSize}")
    public NestedFramesPage assertFramesSize(int expectedSize) {
        $$("iframe").shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    @Step("Проверка текста во вложенных фреймах")
    public NestedFramesPage assertFrame(String expectedParentFrameText, String expectedChildFrameText) {
        parentFrame.shouldBe(Condition.visible);
        switchToParentFrame();
        assertBodyText(expectedParentFrameText);

        switchToChildFrame();
        assertBodyText(expectedChildFrameText);

        returnToDefaultContent();
        return this;
    }

    @Step("Переключение на родительский фрейм")
    private void switchToParentFrame() {
        switchTo().frame(parentFrame);
    }

    @Step("Переключение на дочерний фрейм")
    private void switchToChildFrame() {
        switchTo().frame($("iframe"));
    }

    @Step("Возврат в основной контент страницы")
    private void returnToDefaultContent() {
        switchTo().defaultContent();
    }

    @Step("Проверка, что текст <body> содержит '{expectedText}'")
    private void assertBodyText(String expectedText) {
        $("body").shouldHave(Condition.text(expectedText));
    }
}