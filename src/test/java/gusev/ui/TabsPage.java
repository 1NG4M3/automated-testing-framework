package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class TabsPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement whatTab = $x("//*[@id='demo-tab-what']");
    private final SelenideElement originTab = $x("//*[@id='demo-tab-origin']");
    private final SelenideElement useTab = $x("//*[@id='demo-tab-use']");
    private final SelenideElement moreTab = $x("//*[@id='demo-tab-more']");
    private final SelenideElement whatTabDesc = $x("//*[@id='demo-tabpane-what']/p");
    private final SelenideElement originTabDesc = $x("//*[@id='demo-tabpane-origin']");
    private final SelenideElement useTabDesc = $x("//*[@id='demo-tabpane-use']/p");
    private final SelenideElement moreTabDesc = $x("//*[@id='demo-tabpane-more']");

    public TabsPage assertMainText(String expectedTabName) {
        mainText
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabName));
        return this;
    }

    public TabsPage assertWhatTab(String expectedTabName, String expectedTabDescription) {
        whatTab
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabName));
        whatTabDesc
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabDescription));
        return this;
    }

    public TabsPage assertOriginTab(String expectedTabName, String expectedTabDescription) {
        originTab
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabName))
                .click();
        originTabDesc
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabDescription));
        return this;
    }

    public TabsPage assertUseTab(String expectedTabName, String expectedTabDescription) {
        useTab
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabName))
                .click();
        useTabDesc
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabDescription));
        return this;
    }

    public TabsPage assertMoreTab(String expectedTabName) {
        moreTab
                .shouldBe(visible)
                .should(Condition.partialText(expectedTabName));
        moreTabDesc
                .shouldNotBe(visible)
                .shouldBe(enabled);
        return this;
    }
}