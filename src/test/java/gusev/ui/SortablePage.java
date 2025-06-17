package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortablePage {
    private final SelenideElement header = $x("//*[@class='main-header']");
    private final ElementsCollection listItems = $$("#demo-tabpane-list .list-group-item");
    private final SelenideElement gridTab = $x("//*[@id='demo-tab-grid']");
    private final ElementsCollection gridItems = $$(".create-grid .list-group-item");

    @Step("Проверка заголовка страницы: {expectedHeader}")
    public SortablePage assertHeader(String expectedHeader) {
        header.shouldBe(visible).shouldHave(Condition.partialText(expectedHeader));
        return this;
    }

    @Step("Проверка начального порядка списка (List tab)")
    public SortablePage assertInitialListOrder(List<String> expectedOrder) {
        assertEquals(expectedOrder, listItems.texts(), "Начальный порядок в List tab не совпадает");
        return this;
    }

    @Step("Изменение порядка в List tab: перетаскивание 'Three' перед 'One'")
    public SortablePage reorderListTabItems() {
        SelenideElement source = $(byText("Three"));
        SelenideElement target = $(byText("One"));
        executeJavaScript("arguments[1].parentNode.insertBefore(arguments[0], arguments[1]);", source, target);
        return this;
    }

    @Step("Проверка изменённого порядка в List tab")
    public SortablePage assertNewListOrder(List<String> expectedNewOrder) {
        assertEquals(expectedNewOrder, listItems.texts(), "Новый порядок в List tab не совпадает");
        return this;
    }

    @Step("Проверка начального порядка в Grid tab")
    public SortablePage assertInitialGridOrder(List<String> expectedGridOrder) {
        gridTab.shouldBe(visible).click();
        assertEquals(expectedGridOrder, gridItems.texts(), "Начальный порядок в Grid tab не совпадает");
        return this;
    }

    @Step("Изменение порядка в Grid tab: перетаскивание 'Eight' перед 'Two'")
    public SortablePage reorderGridTabItems() {
        SelenideElement source = gridItems.findBy(text("Eight"));
        SelenideElement target = gridItems.findBy(text("Two"));
        executeJavaScript("arguments[1].parentNode.insertBefore(arguments[0], arguments[1]);", source, target);
        return this;
    }

    @Step("Проверка нового порядка в Grid tab")
    public SortablePage assertNewGridOrder(List<String> expectedNewOrder) {
        assertEquals(expectedNewOrder, gridItems.texts(), "Новый порядок в Grid tab не совпадает");
        return this;
    }
}