package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
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

    public LinksPage assertMainTextWithNewTab(String expectedText) {
        mainTextWithNewTab.should(Condition.partialText(expectedText));
        return this;
    }

    public LinksPage assertMainTextWithApiCall(String expectedText) {
        mainTextWithApiCall.should(Condition.partialText(expectedText));
        return this;
    }

    public LinksPage assertHomeLink(String expectedText) {
        homeLink.should(Condition.partialText(expectedText)).click();
        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("http://85.192.34.140:8081/"));
        Selenide.switchTo().window(0);
        return this;
    }

    public LinksPage assertDynamicLink(String expectedText) {
        dynamicLink.should(Condition.partialText(expectedText)).click();
        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("http://85.192.34.140:8081/"));
        Selenide.switchTo().window(0);
        return this;
    }

    public LinksPage clickCreatedLink(String expectedText) {
        createdApiCall.should(Condition.partialText(expectedText)).click();
        return this;
    }
}