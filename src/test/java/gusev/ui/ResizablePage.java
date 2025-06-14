package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
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

    Actions actions = new Actions(WebDriverRunner.getWebDriver());

    public ResizablePage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public ResizablePage assertResizableBoxWithRestriction(String expectedText) {
        resizableBoxWithRestriction
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        // Попытка уменьшить ниже минимума
        actions.clickAndHold(handle).moveByOffset(-100, -100).release().perform();
        int[] sizeAfterShrink = getSize(resizableBoxWithRestriction);
        assertTrue(sizeAfterShrink[0] >= 150, "Width ниже минимума");
        assertTrue(sizeAfterShrink[1] >= 150, "Height ниже минимума");

        // Попытка увеличить выше максимума
        resizableBoxWithRestriction.scrollIntoView(true);
        actions.moveToElement(handle).clickAndHold().moveByOffset(400, 200).release().perform();
        int[] sizeAfterGrow = getSize(resizableBoxWithRestriction);
        assertTrue(sizeAfterGrow[0] <= 500, "Width выше максимума");
        assertTrue(sizeAfterGrow[1] <= 300, "Height выше максимума");
        return this;
    }

    public ResizablePage assertResizableBox(String expectedText) {
        unlimitedBox
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        unlimitedBox.scrollIntoView(true);
        int[] sizeBefore = getSize(unlimitedBox);
        actions.moveToElement(handleUnlimitedBox).clickAndHold().moveByOffset(300, 200).release().perform();
        int[] sizeAfter = getSize(unlimitedBox);

        assertTrue(sizeAfter[0] > sizeBefore[0], "Ширина не увеличилась");
        assertTrue(sizeAfter[1] > sizeBefore[1], "Высота не увеличилась");
        return this;
    }

    protected static int[] getSize(SelenideElement element) {
        int width = Integer.parseInt(element.getCssValue("width").replace("px", "").trim());
        int height = Integer.parseInt(element.getCssValue("height").replace("px", "").trim());
        return new int[]{width, height};
    }
}