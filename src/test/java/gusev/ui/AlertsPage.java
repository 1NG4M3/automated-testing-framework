package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AlertsPage {
    private final SelenideElement mainText = $x("//*[@class='main-header']");
    private final SelenideElement alertDesc = $x("//*[@id='javascriptAlertsWrapper']/div[1]/div[1]");
    private final SelenideElement timerAlertDesc = $x("//*[@id='javascriptAlertsWrapper']/div[2]/div[1]");
    private final SelenideElement confirmButtonDesc = $x("//*[@id='javascriptAlertsWrapper']/div[3]/div[1]");
    private final SelenideElement promtButtonDesc = $x("//*[@id='javascriptAlertsWrapper']/div[4]/div[1]");
    private final SelenideElement alertButton = $x("//*[@id='alertButton']");
    private final SelenideElement timerAlertButton = $x("//*[@id='timerAlertButton']");
    private final SelenideElement confirmButton = $x("//*[@id='confirmButton']");
    private final SelenideElement promtButton = $x("//*[@id='promtButton']");

    public AlertsPage assertMainText(String expectedText) {
        mainText
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        return this;
    }

    public AlertsPage assertAlertButton(String expectedText) {
        alertDesc
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        alertButton.click();
        Selenide.confirm();
        return this;
    }

    public AlertsPage assertTimerAlertButton(String expectedText) {
        timerAlertDesc
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        timerAlertButton.click();
        Selenide.sleep(6000);
        Selenide.confirm();
        return this;
    }

    public AlertsPage assertConfirmButton(String expectedText) {
        confirmButtonDesc
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        confirmButton.click();
        Selenide.confirm();
        confirmButtonDesc.should(Condition.partialText(expectedText + "You selected Ok"));
        return this;
    }

    public AlertsPage assertPromtButton(String expectedText, String inputName) {
        promtButtonDesc
                .shouldBe(Condition.visible)
                .should(Condition.partialText(expectedText));
        promtButton.click();
        Selenide.prompt(inputName);
        promtButtonDesc.should(Condition.partialText(expectedText.concat("You entered ").concat(inputName)));
        return this;
    }
}