package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BrowserWindowsPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement newTabButton = $x("//*[@id='tabButton']");
    private final SelenideElement newTabWindowMainText = $x("//*[@id='sampleHeading']");
    private final SelenideElement newWindowButton = $x("//*[@id='windowButton']");
    private final SelenideElement newWindowMessageButton = $x("//*[@id='messageWindowButton']");
    private final SelenideElement newWindowMessageBody = $x("//body");

    @Step("Проверить основной заголовок страницы: {expectedText}")
    public BrowserWindowsPage assertMainText(String expectedText) {
        mainText.shouldBe(Condition.visible)
                .shouldHave(partialText(expectedText));
        return this;
    }

    @Step("Проверить открытие новой вкладки с текстом кнопки: {expectedButtonText} и заголовком: {expectedMainTabText}")
    public BrowserWindowsPage assertNewTab(String expectedButtonText, String expectedMainTabText) {
        newTabButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(partialText(expectedButtonText))
                .click();

        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("https://threadqa.ru/sample.html"));

        newTabWindowMainText.shouldBe(Condition.visible)
                .shouldHave(partialText(expectedMainTabText));

        Selenide.closeWindow();
        Selenide.switchTo().window(0);
        return this;
    }

    @Step("Проверить открытие нового окна с текстом кнопки: {expectedButtonText} и заголовком: {expectedMainTabText}")
    public BrowserWindowsPage assertNewWindow(String expectedButtonText, String expectedMainTabText) {
        newWindowButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(partialText(expectedButtonText))
                .click();

        Selenide.switchTo().window(1);
        webdriver().shouldHave(url("https://threadqa.ru/sample.html"));

        newTabWindowMainText.shouldBe(Condition.visible)
                .shouldHave(partialText(expectedMainTabText));

        Selenide.closeWindow();
        Selenide.switchTo().window(0);
        return this;
    }

    @Step("Проверить окно с сообщением: кнопка '{expectedButtonText}', тело окна содержит '{expectedWindowBodyText}'")
    public BrowserWindowsPage assertNewWindowMessage(String expectedButtonText, String expectedWindowBodyText) {
        newWindowMessageButton.shouldBe(Condition.visible)
                .shouldBe(Condition.enabled)
                .shouldHave(partialText(expectedButtonText))
                .click();

        String newWindow = Wait().until(webDriver -> {
            Set<String> handles = WebDriverRunner.getWebDriver().getWindowHandles();
            return handles.size() > 1
                    ? handles.stream()
                    .filter(h -> !h.equals(WebDriverRunner.getWebDriver().getWindowHandle()))
                    .findFirst()
                    .orElse(null)
                    : null;
        });

        if (newWindow == null) {
            throw new RuntimeException("Новое окно не открылось!");
        }

        Selenide.switchTo().window(newWindow);

        Wait().until(webDriver -> {
            String currentUrl = WebDriverRunner.url();
            return !currentUrl.equals("about:blank") && !currentUrl.isEmpty();
        });

        newWindowMessageBody
                .shouldHave(partialText(expectedWindowBodyText), Duration.ofSeconds(5));

        Selenide.closeWindow();
        Selenide.switchTo().window(1);
        return this;
    }
}