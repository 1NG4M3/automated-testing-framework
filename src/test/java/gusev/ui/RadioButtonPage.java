package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RadioButtonPage {
    private final SelenideElement radioButtonQuestionText = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[1]");
    private final SelenideElement yesButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[2]");
    private final SelenideElement impressiveButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]");
    private final SelenideElement noButton = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[4]");
    private final SelenideElement displayResultField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/p");

    public RadioButtonPage assertRadioButtonMainText(String expectedResultText) {
        radioButtonQuestionText.should(Condition.partialText(expectedResultText));
        return this;
    }

    public RadioButtonPage assertYesButtonResultText(String expectedResultText) {
        yesButton.click();
        displayResultField.should(Condition.partialText(expectedResultText));
        return this;
    }

    public RadioButtonPage assertImpressiveButtonResultText(String expectedResultText) {
        impressiveButton.click();
        displayResultField.should(Condition.partialText(expectedResultText));
        return this;
    }

    public RadioButtonPage assertNoButtonText(String expectedButtonText) {
        noButton.should(Condition.partialText(expectedButtonText));
        return this;
    }
}