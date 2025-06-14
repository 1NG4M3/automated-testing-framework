package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class ProgressBarPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement progressBar = $x("//*[@id='progressBar']");
    private final SelenideElement startStopButton = $x("//*[@id='startStopButton']");
    private final SelenideElement resetButton = $x("//*[@id='resetButton']");

    public ProgressBarPage assertMainText(String expectedText) {
        mainText
                .shouldBe(visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public ProgressBarPage checkProgressBar(String expectedButtonStartText, String expectedButtonStopText, String expectedButtonResetText, String expectedFirstValue, String expectedSecondValue, Integer expectedTime) {
        progressBar.shouldBe(visible);
        startStopButton.should(Condition.partialText(expectedButtonStartText))
                .click();
        Selenide.sleep(expectedTime);
        startStopButton.should(Condition.partialText(expectedButtonStopText))
                .click();
        progressBar.shouldHave(Condition.partialText(expectedFirstValue));
        startStopButton.click();
        Selenide.sleep(expectedTime);
        progressBar.shouldHave(Condition.partialText(expectedSecondValue));
        resetButton.shouldBe(visible)
                .shouldBe(enabled)
                .should(Condition.partialText(expectedButtonResetText));
        return this;
    }
}