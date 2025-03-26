package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DatePickerPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement datePickerMonthYearInput = $("#datePickerMonthYearInput");
    private final SelenideElement dateAndTimePickerInput = $("#dateAndTimePickerInput");

    public DatePickerPage assertMainText(String expectedText) {
        mainText
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public DatePickerPage assertDateSelect(String expectedDate, String formattedNewDate) {
        datePickerMonthYearInput
                .shouldBe(visible)
                .shouldHave(value(expectedDate));
        System.out.println("Проверка актуальной даты прошла успешно!");

        datePickerMonthYearInput.click();
        executeJavaScript("document.querySelector('#datePickerMonthYearInput').value = arguments[0];", formattedNewDate);
        executeJavaScript("document.querySelector('#datePickerMonthYearInput').dispatchEvent(new Event('change', { bubbles: true }));");
        datePickerMonthYearInput.shouldHave(value(formattedNewDate));
        System.out.println("Проверка выбора новой даты прошла успешно!");
        return this;
    }

    public DatePickerPage assertDateAndTimeSelect(String expectedDateTime, String formattedNewDateTime) {
        dateAndTimePickerInput
                .shouldBe(visible)
                .shouldHave(value(expectedDateTime));
        System.out.println("Проверка актуальной даты и времени прошла успешно!");

        dateAndTimePickerInput.click();
        executeJavaScript("document.querySelector('#dateAndTimePickerInput').value = arguments[0];", formattedNewDateTime);
        executeJavaScript("document.querySelector('#dateAndTimePickerInput').dispatchEvent(new Event('change', { bubbles: true }));");
        dateAndTimePickerInput.shouldHave(value(formattedNewDateTime));
        System.out.println("Проверка выбора нового времени прошла успешно");
        return this;
    }
}