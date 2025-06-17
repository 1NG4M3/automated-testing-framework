package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResizablePage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement resizableBoxWithRestriction = $("#resizableBoxWithRestriction");
    private final SelenideElement handle = resizableBoxWithRestriction.$(".react-resizable-handle");
    private final SelenideElement unlimitedBox = $("#resizable");
    private final SelenideElement handleUnlimitedBox = unlimitedBox.$(".react-resizable-handle");

    private final Actions actions = new Actions(WebDriverRunner.getWebDriver());

    @Step("Проверка заголовка страницы: {expectedHeaderName}")
    public ResizablePage assertHeaderName(String expectedHeaderName) {
        headerText.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверка ограниченного resizable-блока с текстом: {expectedText}")
    public ResizablePage assertResizableBoxWithRestriction(String expectedText) {
        resizableBoxWithRestriction.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedText));

        attemptResize(handle, -100, -100);
        int[] sizeAfterShrink = getSize(resizableBoxWithRestriction);
        assertTrue(sizeAfterShrink[0] >= 150, "Ширина меньше минимума");
        assertTrue(sizeAfterShrink[1] >= 150, "Высота меньше минимума");

        resizableBoxWithRestriction.scrollIntoView(true);
        attemptResize(handle, 400, 200);
        int[] sizeAfterGrow = getSize(resizableBoxWithRestriction);
        assertTrue(sizeAfterGrow[0] <= 500, "Ширина больше максимума");
        assertTrue(sizeAfterGrow[1] <= 300, "Высота больше максимума");

        return this;
    }

    @Step("Проверка неограниченного resizable-блока с текстом: {expectedText}")
    public ResizablePage assertResizableBox(String expectedText) {
        unlimitedBox.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedText));
        unlimitedBox.scrollIntoView(true);

        int[] sizeBefore = getSize(unlimitedBox);
        attemptResize(handleUnlimitedBox, 300, 200);
        int[] sizeAfter = getSize(unlimitedBox);

        assertTrue(sizeAfter[0] > sizeBefore[0], "Ширина не увеличилась");
        assertTrue(sizeAfter[1] > sizeBefore[1], "Высота не увеличилась");

        return this;
    }

    @Step("Изменение размера элемента на ({xOffset}, {yOffset})")
    private void attemptResize(SelenideElement handleElement, int xOffset, int yOffset) {
        actions.moveToElement(handleElement)
                .clickAndHold()
                .moveByOffset(xOffset, yOffset)
                .release()
                .perform();
    }

    private static int[] getSize(SelenideElement element) {
        int width = Integer.parseInt(element.getCssValue("width").replace("px", "").trim());
        int height = Integer.parseInt(element.getCssValue("height").replace("px", "").trim());
        return new int[]{width, height};
    }
}