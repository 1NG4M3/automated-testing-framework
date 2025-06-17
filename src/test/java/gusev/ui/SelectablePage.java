package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SelectablePage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final ElementsCollection listTabItems = $$("#verticalListContainer .list-group-item");
    private final SelenideElement gridTab = $x("//*[@id='demo-tab-grid']");
    private final ElementsCollection gridTabItems = $$("#gridContainer .list-group-item");

    @Step("Проверка заголовка страницы: {expectedHeaderName}")
    public SelectablePage assertHeaderName(String expectedHeaderName) {
        headerText.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверка значений и интерактивности в List вкладке")
    public SelectablePage assertListTab(List<String> expectedItems) {
        assertEquals(expectedItems, listTabItems.texts(),
                "Несовпадение элементов в List табе. Актуальное: " + listTabItems.texts());
        checkStyleChangeOnClick(listTabItems);
        return this;
    }

    @Step("Проверка значений и интерактивности в Grid вкладке")
    public SelectablePage assertGridTab(List<String> expectedItems) {
        gridTab.shouldBe(visible).click();
        assertEquals(expectedItems, gridTabItems.texts(),
                "Несовпадение элементов в Grid табе. Актуальное: " + gridTabItems.texts());
        checkStyleChangeOnClick(gridTabItems);
        return this;
    }

    @Step("Проверка изменения стиля при клике на элементы")
    private void checkStyleChangeOnClick(ElementsCollection elements) {
        for (SelenideElement element : elements) {
            String text = element.getText();
            String initialBackground = element.getCssValue("background-color");
            String initialTextColor = element.getCssValue("color");

            element.click();
            element.shouldHave(Condition.cssClass("active"));

            String newBackground = element.getCssValue("background-color");
            String newTextColor = element.getCssValue("color");

            assertNotEquals(initialBackground, newBackground, "Фон не изменился при клике на элемент: " + text);
            assertNotEquals(initialTextColor, newTextColor, "Цвет текста не изменился при клике на элемент: " + text);
        }
    }
}