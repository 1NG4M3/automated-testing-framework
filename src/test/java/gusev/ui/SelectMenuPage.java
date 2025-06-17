package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectMenuPage {
    private final SelenideElement header = $x("//*[@class='main-header']");
    private final SelenideElement selectValueDropdown = $x("//*[@id='withOptGroup']/div/div[1]/div[1]");
    private final SelenideElement selectOneDropdown = $x("//*[@id='selectOne']/div/div[1]/div[1]");
    private final SelenideElement oldStyleSelect = $("#oldSelectMenu");
    private final SelenideElement multiSelectDropdown = $(byText("Select..."));
    private final SelenideElement standardMultiSelect = $("#cars");

    @Step("Проверка заголовка: {expectedHeader}")
    public SelectMenuPage assertHeaderName(String expectedHeader) {
        header.shouldBe(visible)
                .shouldHave(Condition.partialText(expectedHeader));
        return this;
    }

    @Step("Выбор значения '{value}' в Select Value Menu")
    public SelectMenuPage selectValue(String value) {
        selectOptionFromDropdown(selectValueDropdown, value);
        assertEquals(value, selectValueDropdown.getText(),
                "Значение в Select Value не совпадает с ожидаемым");
        return this;
    }

    @Step("Выбор значения '{value}' в Select One Menu")
    public SelectMenuPage selectOne(String value) {
        selectOptionFromDropdown(selectOneDropdown, value);
        assertEquals(value, selectOneDropdown.getText(),
                "Значение в Select One не совпадает с ожидаемым");
        return this;
    }

    @Step("Выбор значения '{value}' в Old Style Select Menu")
    public SelectMenuPage selectOldStyle(String value) {
        oldStyleSelect.shouldBe(visible).selectOptionByValue(value);
        assertEquals(value, oldStyleSelect.getValue(),
                "Значение в Old Style Select не совпадает с ожидаемым");
        return this;
    }

    @Step("Выбор значения '{value}' в Multiselect Dropdown Menu (кастомный)")
    public SelectMenuPage selectFromMultiDropdown(String value, String expectedIdValue) {
        multiSelectDropdown.shouldBe(visible).click();
        $x("//div[text()='" + value + "']").click();
        // Клик вне поля, чтобы закрыть дропдаун
        $x("//*[@id='selectMenuContainer']/div[7]/div/div/div/div[2]/div").click();
        assertEquals(expectedIdValue, oldStyleSelect.getSelectedOptionValue(),
                "Ожидаемое значение после multiselect выбора не совпадает");
        return this;
    }

    @Step("Выбор значения '{value}' в стандартном multiselect меню")
    public SelectMenuPage selectFromStandardMulti(String value) {
        standardMultiSelect.shouldBe(visible)
                .selectOptionByValue(value);
        assertEquals(value, standardMultiSelect.getSelectedOptionValue(),
                "Значение в стандартном Multiselect не совпадает с ожидаемым");
        return this;
    }

    @Step("Выбор значения '{option}' из дропдауна")
    private void selectOptionFromDropdown(SelenideElement dropdown, String option) {
        dropdown.shouldBe(visible).click();
        $x("//div[text()='" + option + "']").click();
    }
}