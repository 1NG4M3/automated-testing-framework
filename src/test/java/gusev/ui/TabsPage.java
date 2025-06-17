package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class TabsPage {
    private final SelenideElement header = $x("//*[@class='main-header']");
    private final SelenideElement tabWhat = $x("//*[@id='demo-tab-what']");
    private final SelenideElement tabOrigin = $x("//*[@id='demo-tab-origin']");
    private final SelenideElement tabUse = $x("//*[@id='demo-tab-use']");
    private final SelenideElement tabMore = $x("//*[@id='demo-tab-more']");
    private final SelenideElement descWhat = $x("//*[@id='demo-tabpane-what']/p");
    private final SelenideElement descOrigin = $x("//*[@id='demo-tabpane-origin']");
    private final SelenideElement descUse = $x("//*[@id='demo-tabpane-use']/p");
    private final SelenideElement descMore = $x("//*[@id='demo-tabpane-more']");

    @Step("Проверка заголовка страницы: {expectedText}")
    public TabsPage assertHeader(String expectedText) {
        header.shouldBe(visible).shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка вкладки 'What': название = {tabName}, описание = {description}")
    public TabsPage assertWhatTab(String tabName, String description) {
        return assertTabContent(tabWhat, descWhat, tabName, description, false);
    }

    @Step("Проверка вкладки 'Origin': название = {tabName}, описание = {description}")
    public TabsPage assertOriginTab(String tabName, String description) {
        return assertTabContent(tabOrigin, descOrigin, tabName, description, true);
    }

    @Step("Проверка вкладки 'Use': название = {tabName}, описание = {description}")
    public TabsPage assertUseTab(String tabName, String description) {
        return assertTabContent(tabUse, descUse, tabName, description, true);
    }

    @Step("Проверка вкладки 'More': название = {tabName}, описание недоступно")
    public TabsPage assertMoreTab(String tabName) {
        tabMore.shouldBe(visible).shouldHave(Condition.partialText(tabName));
        descMore.shouldNotBe(visible).shouldBe(enabled);
        return this;
    }

    private TabsPage assertTabContent(SelenideElement tab, SelenideElement content, String tabName, String description, boolean clickTab) {
        tab.shouldBe(visible).shouldHave(Condition.partialText(tabName));
        if (clickTab) tab.click();
        content.shouldBe(visible).shouldHave(Condition.partialText(description));
        return this;
    }
}