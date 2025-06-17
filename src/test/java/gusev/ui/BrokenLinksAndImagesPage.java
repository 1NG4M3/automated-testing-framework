package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenLinksAndImagesPage {

    private final SelenideElement validImageText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p[1]");
    private final SelenideElement validImage = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/img[1]");
    private final SelenideElement brokenImageText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p[2]");
    private final SelenideElement brokenImage = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/img[2]");
    private final SelenideElement validLinkText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p[3]");
    private final SelenideElement validLink = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/a[1]");
    private final SelenideElement brokenLinkText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p[4]");
    private final SelenideElement brokenLink = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/a[2]");

    @Step("Проверить текст под валидной картинкой: {expectedText}")
    public BrokenLinksAndImagesPage assertValidImageText(String expectedText) {
        validImageText.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверить валидную картинку и выполнить клик по ней")
    public BrokenLinksAndImagesPage assertValidImage() {
        boolean displayed = validImage.shouldBe(Condition.image).isDisplayed();
        assertTrue(displayed, "Валидная картинка должна быть отображаема");
        validImage.click();
        return this;
    }

    @Step("Проверить текст под сломанной картинкой: {expectedText}")
    public BrokenLinksAndImagesPage assertBrokenImageText(String expectedText) {
        brokenImageText.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверить, что изображение сломано, и выполнить клик по нему")
    public BrokenLinksAndImagesPage assertBrokenImage() {
        boolean displayed = brokenImage.shouldNot(Condition.image).isDisplayed();
        assertTrue(displayed, "Сломанное изображение должно быть невалидным, но отображаемым");
        brokenImage.click();
        return this;
    }

    @Step("Проверить текст под валидной ссылкой: {expectedText}")
    public BrokenLinksAndImagesPage assertValidLinkText(String expectedText) {
        validLinkText.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверить валидную ссылку с текстом: {expectedText} и href: {expectedHref}")
    public BrokenLinksAndImagesPage assertValidLink(String expectedText, String expectedHref) {
        validLink.shouldHave(Condition.partialText(expectedText));
        validLink.shouldHave(Condition.href(expectedHref));
        return this;
    }

    @Step("Проверить текст под сломанной ссылкой: {expectedText}")
    public BrokenLinksAndImagesPage assertBrokenLinkText(String expectedText) {
        brokenLinkText.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверить сломанную ссылку с текстом: {expectedText}, href: {expectedHref} и результат после клика: {expectedHrefAfterClick}")
    public BrokenLinksAndImagesPage assertBrokenLink(String expectedText, String expectedHref, String expectedHrefAfterClick) {
        brokenLink.shouldHave(Condition.partialText(expectedText));
        brokenLink.shouldHave(Condition.href(expectedHref)).click();
        webdriver().shouldHave(url(expectedHrefAfterClick));
        return this;
    }
}