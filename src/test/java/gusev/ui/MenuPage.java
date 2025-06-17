package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MenuPage {

    private final SelenideElement headerText = $x("//*[@class='main-header']");

    @Step("Проверяем, что заголовок страницы содержит текст: {expectedHeaderName}")
    public MenuPage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверяем смену фона при наведении на элементы меню: {mainItem1}, {mainItem2}, {mainItem3}")
    public MenuPage assertMainItemsBackgroundColor(String mainItem1, String mainItem2, String mainItem3) {
        hoverShouldChangeColor(mainItem1);
        hoverShouldChangeColor(mainItem2);
        hoverShouldChangeColor(mainItem3);
        return this;
    }

    @Step("Проверяем, что подменю скрыты: {subItem}, {subSubList}, {subSubItem1}, {subSubItem2}")
    public MenuPage assertInvisibilitySubMenus(String subItem, String subSubList, String subSubItem1, String subSubItem2) {
        menuItem(subItem).shouldNotBe(visible);
        menuItem(subSubList).shouldNotBe(visible);
        menuItem(subSubItem1).shouldNotBe(visible);
        menuItem(subSubItem2).shouldNotBe(visible);
        return this;
    }

    @Step("Проверяем, что при наведении на {mainItem2} становятся видимыми: {subItem}, {subSubList}")
    public MenuPage assertVisibilitySubMenuMainItem2(String mainItem2, String subItem, String subSubList) {
        menuItem(mainItem2).hover();
        menuItem(subItem).shouldBe(visible);
        menuItem(subSubList).shouldBe(visible);
        hoverShouldChangeColor(subItem);
        hoverShouldChangeColor(subSubList);
        return this;
    }

    @Step("Проверяем, что при наведении на {subSubList} становятся видимыми: {subSubItem1}, {subSubItem2}")
    public MenuPage assertVisibilitySubMenuOfSubSubList(String subSubList, String subSubItem1, String subSubItem2) {
        menuItem(subSubList).hover();
        menuItem(subSubItem1).shouldBe(visible);
        menuItem(subSubItem2).shouldBe(visible);
        hoverShouldChangeColor(subSubItem1);
        hoverShouldChangeColor(subSubItem2);
        return this;
    }

    private void hoverShouldChangeColor(String menuText) {
        checkBackgroundColorChangesOnHover(menuItem(menuText));
    }

    public static SelenideElement menuItem(String text) {
        return $x("//a[normalize-space(text())='" + text + "']");
    }

    public static void checkBackgroundColorChangesOnHover(SelenideElement element) {
        String initial = element.parent().getCssValue("background-color");
        element.hover();
        String hover = element.getCssValue("background-color");
        assertNotEquals(initial, hover, "Фон не изменился при наведении на: " + element.getText());
    }
}