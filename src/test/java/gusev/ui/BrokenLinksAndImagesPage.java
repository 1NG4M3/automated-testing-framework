package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

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

    public BrokenLinksAndImagesPage assertValidImageText(String expectedText) {
        validImageText.should(Condition.partialText(expectedText));
        return this;
    }

    public BrokenLinksAndImagesPage assertValidImage() {
        boolean displayed = validImage.should(Condition.image).isDisplayed();
        assertTrue(displayed);
        validImage.click();
        return this;
    }

    public BrokenLinksAndImagesPage assertBrokenImageText(String expectedText) {
        brokenImageText.should(Condition.partialText(expectedText));
        return this;
    }

    public BrokenLinksAndImagesPage assertBrokenImage() {
        boolean displayed = brokenImage.shouldNot(Condition.image).isDisplayed();
        assertTrue(displayed);
        brokenImage.click();
        return this;
    }

    public BrokenLinksAndImagesPage assertValidLinkText(String expectedText) {
        validLinkText.should(Condition.partialText(expectedText));
        return this;
    }

    public BrokenLinksAndImagesPage assertValidLink(String expectedText, String expectedHref) {
        validLink.should(Condition.partialText(expectedText));
        validLink.should(Condition.href(expectedHref));
        return this;
    }

    public BrokenLinksAndImagesPage assertBrokenLinkText(String expectedText) {
        brokenLinkText.should(Condition.partialText(expectedText));
        return this;
    }

    public BrokenLinksAndImagesPage assertBrokenLink(String expectedText, String expectedHref, String expectedHrefAfterClick) {
        brokenLink.should(Condition.partialText(expectedText));
        brokenLink.should(Condition.href(expectedHref)).click();
        webdriver().shouldHave(url(expectedHrefAfterClick));
        return this;
    }
}