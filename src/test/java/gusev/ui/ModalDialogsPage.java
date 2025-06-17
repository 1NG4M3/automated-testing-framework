package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ModalDialogsPage {

    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement modalHeader = $(".modal-header");
    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement closeSmallModalButton = $("#closeSmallModal");
    private final SelenideElement closeLargeModalButton = $("#closeLargeModal");
    private final SelenideElement descriptionText = $("#modalWrapper");
    private final SelenideElement smallModalButton = $("#showSmallModal");
    private final SelenideElement largeModalButton = $("#showLargeModal");
    private final SelenideElement modal = $(".modal");
    private final SelenideElement modalShow = $(".modal.show");
    private final SelenideElement body = $("body");

    @Step("Проверка основного заголовка: ожидается текст '{expectedText}'")
    public ModalDialogsPage assertMainText(String expectedText) {
        mainText.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка текста описания: ожидается текст '{expectedText}'")
    public ModalDialogsPage assertDescriptionText(String expectedText) {
        descriptionText.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Проверка отображения и закрытия Small Modal")
    public ModalDialogsPage assertSmallModal(String buttonText, String modalHeaderText, String modalBodyText) {
        return openAndValidateModal(
                smallModalButton,
                buttonText,
                modalHeaderText,
                modalBodyText,
                closeSmallModalButton
        );
    }

    @Step("Проверка отображения и закрытия Large Modal")
    public ModalDialogsPage assertLargeModal(String buttonText, String modalHeaderText, String modalBodyText) {
        return openAndValidateModal(
                largeModalButton,
                buttonText,
                modalHeaderText,
                modalBodyText,
                closeLargeModalButton
        );
    }

    @Step("Открываем модальное окно и проверяем его содержимое")
    private ModalDialogsPage openAndValidateModal(SelenideElement openButton,
                                                  String expectedButtonText,
                                                  String expectedHeader,
                                                  String expectedBody,
                                                  SelenideElement closeButton) {
        openButton.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedButtonText))
                .click();

        modalHeader.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedHeader));
        modalBody.shouldBe(Condition.visible)
                .shouldHave(Condition.partialText(expectedBody));

        closeButton.shouldBe(Condition.visible).click();

        modalShow.shouldNotBe(Condition.visible);
        modal.shouldBe(Condition.hidden);
        body.shouldNotHave(Condition.cssClass("modal-open"));

        return this;
    }
}