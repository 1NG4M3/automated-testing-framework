package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MenuPage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");

    public MenuPage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public MenuPage assertMainItemsBackgroundColor(String mainItem1, String mainItem2, String mainItem3) {
        checkBackgroundColorChangesOnHover(menuItem(mainItem1));
        checkBackgroundColorChangesOnHover(menuItem(mainItem2));
        checkBackgroundColorChangesOnHover(menuItem(mainItem3));
        return this;
    }

    public MenuPage assertInvisibilitySubMenus(String subItem, String subSubList, String subSubItem1, String subSubItem2) {
        menuItem(subItem).shouldNotBe(visible);
        menuItem(subSubList).shouldNotBe(visible);
        menuItem(subSubItem1).shouldNotBe(visible);
        menuItem(subSubItem2).shouldNotBe(visible);
        return this;
    }

    public MenuPage assertVisibilitySubMenuMainItem2(String mainItem2, String subItem, String subSubList) {
        menuItem(mainItem2).hover();
        menuItem(subItem).shouldBe(visible);
        menuItem(subSubList).shouldBe(visible);
        checkBackgroundColorChangesOnHover(menuItem(subItem));
        checkBackgroundColorChangesOnHover(menuItem(subSubList));
        return this;
    }

    public MenuPage assertVisibilitySubMenuOfSubSubList(String subSubList, String subSubItem1, String subSubItem2) {
        menuItem(subSubList).hover();
        menuItem(subSubItem1).shouldBe(visible);
        menuItem(subSubItem2).shouldBe(visible);
        checkBackgroundColorChangesOnHover(menuItem(subSubItem1));
        checkBackgroundColorChangesOnHover(menuItem(subSubItem2));
        return this;
    }

    public static SelenideElement menuItem(String text) {
        return $x("//a[text()='" + text + "']");
    }

    public static void checkBackgroundColorChangesOnHover(SelenideElement element) {
        String initial = element.parent().getCssValue("background-color");
        element.hover();
        String hover = element.getCssValue("background-color");
        assertNotEquals(initial, hover, "Фон не изменился при наведении на: " + element.getText());
    }
}