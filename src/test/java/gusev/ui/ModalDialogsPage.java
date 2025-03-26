package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ModalDialogsPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement modalHeader = $x("//*[@class='modal-header']");
    private final SelenideElement modalBody = $x("//*[@class='modal-body']");
    private final SelenideElement closeSmallModalButton = $x("//*[@id='closeSmallModal']");
    private final SelenideElement closeLargeModalButton = $x("//*[@id='closeLargeModal']");
    private final SelenideElement descriptionText = $x("//*[@id='modalWrapper']");
    private final SelenideElement smallModalButton = $x("//*[@id='showSmallModal']");
    private final SelenideElement largeModalButton = $x("//*[@id='showLargeModal']");
    private final SelenideElement modal = $(".modal");
    private final SelenideElement modalShow = $(".modal.show");
    private final SelenideElement body = $("body");

    public ModalDialogsPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public ModalDialogsPage assertDescriptionText(String expectedText) {
        descriptionText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public ModalDialogsPage assertSmallModal(String expectedSmallModalButtonText, String expectedModalHeaderText, String expectedModalBodyText) {
        smallModalButton
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedSmallModalButtonText))
                .click();
        modalHeader
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedModalHeaderText));
        modalBody
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedModalBodyText));
        closeSmallModalButton.shouldBe(Condition.visible).click();
        modalShow.shouldNotBe(Condition.visible);
        modal.shouldBe(Condition.hidden);
        body.shouldNotHave(Condition.cssClass("modal-open"));
        return this;
    }

    public ModalDialogsPage assertLargeModal(String expectedLargeModalButtonText, String expectedModalHeaderText, String expectedModalBodyText) {
        largeModalButton
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedLargeModalButtonText))
                .click();
        modalHeader
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedModalHeaderText));
        modalBody
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedModalBodyText));
        closeLargeModalButton.shouldBe(Condition.visible).click();
        modalShow.shouldNotBe(Condition.visible);
        modal.shouldBe(Condition.hidden);
        body.shouldNotHave(Condition.cssClass("modal-open"));
        return this;
    }
}