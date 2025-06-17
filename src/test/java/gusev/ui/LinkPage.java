package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinkPage {

    private final SelenideElement headerText = $x("//*[@class='main-header']");
    private final SelenideElement mainText = $("#notLoggin-label");
    private final SelenideElement link = $("#notLoggin-label a");

    @Step("Проверка заголовка страницы: ожидается, что содержит '{expectedHeaderName}'")
    public LinkPage assertHeaderName(String expectedHeaderName) {
        headerText
                .shouldBe(visible)
                .shouldHave(Condition.partialText(expectedHeaderName));
        return this;
    }

    @Step("Проверка редиректа по ссылке: ожидается текст '{expectedMainText}' и переход на https://www.google.com/")
    public LinkPage assertLinkRedirect(String expectedMainText) {
        mainText.shouldHave(text(expectedMainText));

        link.shouldBe(visible)
                .shouldHave(text("link"))
                .shouldHave(attribute("href", "https://www.google.com/"))
                .shouldHave(cssValue("font-weight", "700"));

        executeJavaScript("document.querySelector('a').removeAttribute('target');");
        link.click();

        webdriver().shouldHave(url("https://www.google.com/"));
        return this;
    }
}