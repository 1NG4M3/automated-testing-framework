package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectMenuPage {
    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement selectValue = $x("//*[@id='withOptGroup']/div/div[1]/div[1]");
    private final SelenideElement selectOne = $x("//*[@id='selectOne']/div/div[1]/div[1]");
    private final SelenideElement oldSelectMenu = $x("//*[@id='oldSelectMenu']");
    private final SelenideElement multiselectDropDown = $(byText("Select..."));
    private final SelenideElement standardMultiSelect = $x("//*[@id='cars']");

    public SelectMenuPage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .should(Condition.partialText(expectedHeaderName));
        return this;
    }

    public SelectMenuPage assertSelectValueMenu(String value) {
        selectValue
                .shouldBe(visible)
                .click();
        $x("//div[text()='" + value + "']").click();
        String actualValue = selectValue.getText();
        assertEquals(value, actualValue, "Ожидаемое значение не совпадает с актуальным: " + actualValue);
        return this;
    }

    public SelectMenuPage assertSelectOneMenu(String value) {
        selectOne
                .shouldBe(visible)
                .click();
        $x("//div[text()='" + value + "']").click();
        String actualValue = selectOne.getText();
        assertEquals(value, actualValue, "Ожидаемое значение не совпадает с актуальным: " + actualValue);
        return this;
    }

    public SelectMenuPage assertOldStyleSelectMenu(String value) {
        oldSelectMenu
                .shouldBe(visible)
                .click();
        oldSelectMenu.selectOptionByValue(value);
        String actualValue = oldSelectMenu.getValue();
        assertEquals(value, actualValue, "Ожидаемое значение не совпадает с актуальным: " + actualValue);
        return this;
    }

    public SelectMenuPage assertMultiselectDropDownMenu(String value) {
        multiselectDropDown
                .shouldBe(visible)
                .click();
        $x("//div[text()='" + value + "']").click();
        $x("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[2]/div").click();
        String actualValue = oldSelectMenu.getSelectedOptionValue();
        assertEquals("1", actualValue, "Ожидаемое значение не совпадает с актуальным: " + actualValue);
        return this;
    }

    public SelectMenuPage assertStandardMultiselectMenu(String value) {
        standardMultiSelect
                .shouldBe(visible);
        standardMultiSelect.selectOptionByValue(value);
        String actualValue = standardMultiSelect.getSelectedOptionValue();
        assertEquals(value, actualValue, "Ожидаемое значение не совпадает с актуальным: " + actualValue);
        return this;
    }
}