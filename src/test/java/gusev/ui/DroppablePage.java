package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DroppablePage {

    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement draggable = $("#draggable");
    private final SelenideElement droppable = $("#droppable");
    private final SelenideElement droppableText = $("#droppable p");

    private static final String SIMULATE_JS_URL = "https://raw.githubusercontent.com/jquery/jquery-simulate/master/jquery.simulate.js";

    private static final String DRAG_AND_DROP_SCRIPT = """
        const $source = $(arguments[0]);
        const $target = $(arguments[1]);
        const offset = $target.offset();
        const dx = offset.left - $source.offset().left + 10;
        const dy = offset.top - $source.offset().top + 10;
        $source.simulate('drag', { dx: dx, dy: dy });
    """;

    private static final String INJECT_SIMULATE_SCRIPT = """
        var script = document.createElement('script');
        script.src = '%s';
        document.head.appendChild(script);
    """;

    @Step("Проверка заголовка страницы: должен содержать '{expectedHeaderName}'")
    public DroppablePage assertHeaderName(String expectedHeaderName) {
        headerText.shouldBe(visible).should(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверка перетаскивания: до — '{expectedTextBefore}', после — '{expectedTextAfter}'")
    public DroppablePage assertSimpleDrop(String expectedTextBefore, String expectedTextAfter) {
        droppableText.shouldHave(text(expectedTextBefore));

        String initialColor = droppable.getCssValue("background-color");

        injectSimulateJs();
        dragAndDropJQueryUI(draggable, droppable);

        droppableText.shouldHave(text(expectedTextAfter));
        String newColor = droppable.getCssValue("background-color");

        assertNotEquals(initialColor, newColor, "Цвет фона не изменился после дропа");
        return this;
    }

    @Step("Исполняем drag&drop с помощью jQuery Simulate")
    public void dragAndDropJQueryUI(SelenideElement source, SelenideElement target) {
        executeJavaScript(DRAG_AND_DROP_SCRIPT, source, target);
    }

    @Step("Инжектим библиотеку jQuery Simulate.js")
    public void injectSimulateJs() {
        String script = INJECT_SIMULATE_SCRIPT.formatted(SIMULATE_JS_URL);
        executeJavaScript(script);
        sleep(1000); // Подождать подгрузку библиотеки
    }
}