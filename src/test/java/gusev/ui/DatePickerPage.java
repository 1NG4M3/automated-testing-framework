package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DatePickerPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement datePickerMonthYearInput = $("#datePickerMonthYearInput");
    private final SelenideElement dateAndTimePickerInput = $("#dateAndTimePickerInput");

    @Step("Проверка заголовка страницы: должен содержать '{expectedText}'")
    public DatePickerPage assertMainText(String expectedText) {
        mainText.shouldBe(visible).should(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка текущей даты: '{expectedDate}', установка новой даты: '{formattedNewDate}'")
    public DatePickerPage assertDateSelect(String expectedDate, String formattedNewDate) {
        datePickerMonthYearInput.shouldBe(visible).shouldHave(value(expectedDate));
        setDateValue(datePickerMonthYearInput, formattedNewDate);
        return this;
    }

    @Step("Проверка текущей даты и времени: '{expectedDateTime}', установка нового значения: '{formattedNewDateTime}'")
    public DatePickerPage assertDateAndTimeSelect(String expectedDateTime, String formattedNewDateTime) {
        dateAndTimePickerInput.shouldBe(visible).shouldHave(value(expectedDateTime));
        setDateValue(dateAndTimePickerInput, formattedNewDateTime);
        return this;
    }

    @Step("Установка значения '{value}' в элемент")
    private void setDateValue(SelenideElement input, String value) {
        input.click();
        executeJavaScript("arguments[0].value = arguments[1];", input, value);
        executeJavaScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", input);
        input.shouldHave(value(value));
    }
}