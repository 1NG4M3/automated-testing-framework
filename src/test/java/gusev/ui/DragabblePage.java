package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

    public DragabblePage verifyHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public DragabblePage verifySimpleTab(String expectedText) {
        dragBox.shouldHave(text(expectedText));
        actions.dragAndDropBy(dragBox, 200, 50).perform();
        return this;
    }

    public DragabblePage verifyAxisRestrictedTab() {
        axisRestriction.click();
        actions.dragAndDropBy(restrictedX, 50,0).perform();
        actions.dragAndDropBy(restrictedY, 0,20).perform();
        return this;
    }

    public DragabblePage verifyContainerRestrictedTab() {
        containerRestriction.click();
        actions.dragAndDropBy(containerWithinBox, 225,10).perform();
        actions.dragAndDropBy(containerWithinParent, 26,4).perform();
        return this;
    }

    public DragabblePage verifyCursorStyleTab() {
        cursorStyle.click();
        actions.dragAndDropBy(cursorCenter, 200,-8).perform();
        actions.dragAndDropBy(cursorTopLeft, 200,-20).perform();
        actions.dragAndDropBy(cursorBottom, 200,-28).perform();
        return this;
    }
}