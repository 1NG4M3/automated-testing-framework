package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DragabblePage {

    private final SelenideElement headerText = $x("//*[@class='main-header']");

    // Simple tab
    private final SelenideElement dragBox = $("#dragBox");

    // Axis restriction tab
    private final SelenideElement axisRestriction = $("#draggableExample-tab-axisRestriction");
    private final SelenideElement restrictedX = $("#restrictedX");
    private final SelenideElement restrictedY = $("#restrictedY");

    // Container restriction tab
    private final SelenideElement containerRestriction = $("#draggableExample-tab-containerRestriction");
    private final SelenideElement containerWithinBox = $("#containmentWrapper > div");
    private final SelenideElement containerWithinParent = $("#draggableExample-tabpane-containerRestriction > div.draggable.ui-widget-content.m-3 > span");

    // Cursor style tab
    private final SelenideElement cursorStyle = $("#draggableExample-tab-cursorStyle");
    private final SelenideElement cursorCenter = $("#cursorCenter");
    private final SelenideElement cursorTopLeft = $("#cursorTopLeft");
    private final SelenideElement cursorBottom = $("#cursorBottom");

    private final Actions actions = new Actions(WebDriverRunner.getWebDriver());

    @Step("Проверка заголовка: ожидаем '{expectedHeaderName}'")
    public DragabblePage verifyHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверка вкладки Simple: перетаскиваем элемент с текстом '{expectedText}'")
    public DragabblePage verifySimpleTab(String expectedText) {
        dragBox.shouldHave(text(expectedText));
        dragAndDropByOffset(dragBox, 200, 50);
        return this;
    }

    @Step("Проверка вкладки Axis Restricted: перетаскиваем элементы по X и Y")
    public DragabblePage verifyAxisRestrictedTab() {
        axisRestriction.click();
        dragAndDropByOffset(restrictedX, 50, 0);
        dragAndDropByOffset(restrictedY, 0, 20);
        return this;
    }

    @Step("Проверка вкладки Container Restricted: перетаскиваем оба контейнера")
    public DragabblePage verifyContainerRestrictedTab() {
        containerRestriction.click();
        dragAndDropByOffset(containerWithinBox, 225, 10);
        dragAndDropByOffset(containerWithinParent, 26, 4);
        return this;
    }

    @Step("Проверка вкладки Cursor Style: перетаскиваем все элементы")
    public DragabblePage verifyCursorStyleTab() {
        cursorStyle.click();
        dragAndDropByOffset(cursorCenter, 200, -8);
        dragAndDropByOffset(cursorTopLeft, 200, -20);
        dragAndDropByOffset(cursorBottom, 200, -28);
        return this;
    }

    @Step("Перетаскиваем элемент на смещение X: {xOffset}, Y: {yOffset}")
    private void dragAndDropByOffset(SelenideElement element, int xOffset, int yOffset) {
        actions.dragAndDropBy(element, xOffset, yOffset).perform();
    }
}