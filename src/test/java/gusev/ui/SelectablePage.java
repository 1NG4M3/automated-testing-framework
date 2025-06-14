package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SelectablePage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final ElementsCollection verticalListContainer = $$("#verticalListContainer .list-group-item");
    private final SelenideElement gridTab = $x("//*[@id='demo-tab-grid']");
    private final ElementsCollection gridTabpane = $$("#gridContainer .list-group-item");

    public SelectablePage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public SelectablePage assertListTab(List<String> list) {
        assertEquals(list.toString(), verticalListContainer.texts().toString(), "Ожидаемое значение не совпадает с актуальным: " + verticalListContainer.texts());
        checkBackgroundAndTextColorChangesOnClick(verticalListContainer);
        return this;
    }

    public SelectablePage assertGridTab(List<String> listGrid) {
        gridTab
                .shouldBe(visible)
                .click();
        assertEquals(listGrid.toString(), gridTabpane.texts().toString(), "Ожидаемое значение не совпадает с актуальным: " + gridTabpane.texts());
        checkBackgroundAndTextColorChangesOnClick(gridTabpane);
        return this;
    }

    public static void checkBackgroundAndTextColorChangesOnClick(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            String initialBackgroundColor = element.getCssValue("background-color");
            String initialColor = element.getCssValue("color");
            element.click();
            element.shouldHave(Condition.cssClass("active"));
            String afterClickBackgroundColor = element.getCssValue("background-color");
            String afterClickColor = element.getCssValue("color");
            assertNotEquals(initialBackgroundColor, afterClickBackgroundColor, "Фон не изменился при клике на: " + element.getText());
            assertNotEquals(initialColor, afterClickColor, "Цвет текста не изменился при клике на: " + element.getText());
        }
    }
}