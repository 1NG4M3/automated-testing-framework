package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LinksPage {

    private final SelenideElement homeLink = $x("//*[@id='simpleLink']");
    private final SelenideElement dynamicLink = $x("//*[@id='dynamicLink']");
    private final SelenideElement createdApiCall = $x("//*[@id='created']");
    private final SelenideElement noContentApiCall = $x("//*[@id='no-content']");
    private final SelenideElement movedApiCall = $x("//*[@id='moved']");
    private final SelenideElement badRequestApiCall = $x("//*[@id='bad-request']");
    private final SelenideElement unauthorizedApiCall = $x("//*[@id='unauthorized']");
    private final SelenideElement forbiddenApiCall = $x("//*[@id='forbidden']");
    private final SelenideElement notFoundApiCall = $x("//*[@id='invalid-url']");

    private final SelenideElement mainTextWithNewTab = $x("//*[@id='linkWrapper']/h5[1]/strong");
    private final SelenideElement mainTextWithApiCall = $x("//*[@id='linkWrapper']/h5[2]/strong");

    private static final String EXPECTED_REDIRECT_URL = "http://85.192.34.140:8081/";

    @Step("Проверка заголовка блока 'Links to open in new tab': ожидается '{expectedText}'")
    public LinksPage assertMainTextWithNewTab(String expectedText) {
        mainTextWithNewTab.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка заголовка блока 'Links with API call': ожидается '{expectedText}'")
    public LinksPage assertMainTextWithApiCall(String expectedText) {
        mainTextWithApiCall.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Клик по ссылке 'Home' и проверка перехода на {EXPECTED_REDIRECT_URL}")
    public LinksPage assertHomeLink(String expectedText) {
        homeLink.shouldHave(Condition.partialText(expectedText)).click();
        switchTo().window(1);
        webdriver().shouldHave(url(EXPECTED_REDIRECT_URL));
        switchTo().window(0);
        return this;
    }

    @Step("Клик по ссылке 'Dynamic link' и проверка перехода на {EXPECTED_REDIRECT_URL}")
    public LinksPage assertDynamicLink(String expectedText) {
        dynamicLink.shouldHave(Condition.partialText(expectedText)).click();
        switchTo().window(1);
        webdriver().shouldHave(url(EXPECTED_REDIRECT_URL));
        switchTo().window(0);
        return this;
    }

    @Step("Клик по API-ссылке 'Created' (201) с текстом '{expectedText}'")
    public LinksPage clickCreatedLink(String expectedText) {
        createdApiCall.shouldHave(Condition.partialText(expectedText)).click();
        return this;
    }
}