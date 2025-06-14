package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortablePage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final ElementsCollection verticalListContainer = $$("#demo-tabpane-list .list-group-item");
    private final SelenideElement gridTab = $x("//*[@id='demo-tab-grid']");
    private final ElementsCollection gridTabpane = $$(".create-grid .list-group-item");

    public SortablePage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public SortablePage assertListTab(List<String> list) {
        assertEquals(list.toString(), verticalListContainer.texts().toString(), "Ожидаемое значение не совпадает с актуальным: " + verticalListContainer.texts());
        SelenideElement source = $(byText("Three"));
        SelenideElement target = $(byText("One"));
        executeJavaScript("arguments[1].parentNode.insertBefore(arguments[0], arguments[1]);", source, target);
        List<String> newOrder = verticalListContainer.texts();
        List<String> expectedOrder = List.of("Three", "One", "Two", "Four", "Five", "Six");
        assertEquals(expectedOrder.toString(), newOrder.toString());
        return this;
    }

    public SortablePage assertGridTab(List<String> listGrid) {
        gridTab
                .shouldBe(visible)
                .click();
        assertEquals(listGrid.toString(), gridTabpane.texts().toString(), "Ожидаемое значение не совпадает с актуальным: " + gridTabpane.texts());
        SelenideElement source = gridTabpane.findBy(text("Eight"));
        SelenideElement target = gridTabpane.findBy(text("Two"));
        executeJavaScript("arguments[1].parentNode.insertBefore(arguments[0], arguments[1]);", source, target);
        List<String> newOrder = gridTabpane.texts();
        List<String> expectedOrder = List.of("One", "Eight", "Two", "Three", "Four", "Five", "Six", "Seven", "Nine");
        assertEquals(expectedOrder.toString(), newOrder.toString());
        return this;
    }
}