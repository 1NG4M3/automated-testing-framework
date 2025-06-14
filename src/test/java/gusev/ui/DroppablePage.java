package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DroppablePage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement draggable = $("#draggable");
    private final SelenideElement droppable = $("#droppable");
    private final SelenideElement droppableText = $("#droppable p");

    public DroppablePage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public DroppablePage assertSimpleDrop(String expectedTextBefore, String expectedTextAfter) {
        droppableText.shouldHave(text(expectedTextBefore));
        String initialColor = droppable.getCssValue("background-color");
        injectSimulateJs();
        dragAndDropJQueryUI(draggable, droppable);
        droppableText.shouldHave(text(expectedTextAfter));
        String newColor = droppable.getCssValue("background-color");
        assertNotEquals(initialColor, newColor, "Цвет фона не изменился");
        return this;
    }

    public void dragAndDropJQueryUI(SelenideElement source, SelenideElement target) {
        String script = """
        const $source = $(arguments[0]);
        const $target = $(arguments[1]);

        const offset = $target.offset();
        const dx = offset.left - $source.offset().left + 10;
        const dy = offset.top - $source.offset().top + 10;

        $source.simulate('drag', {
            dx: dx,
            dy: dy
        });
    """;
        executeJavaScript(script, source, target);
    }

    public void injectSimulateJs() {
        String simulateJsUrl = "https://raw.githubusercontent.com/jquery/jquery-simulate/master/jquery.simulate.js";
        String script = """
        var script = document.createElement('script');
        script.src = '%s';
        document.head.appendChild(script);
    """.formatted(simulateJsUrl);
        executeJavaScript(script);
        sleep(1000); // ждём, чтобы script успел подгрузиться
    }
}