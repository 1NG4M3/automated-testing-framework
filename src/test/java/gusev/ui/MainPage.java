package gusev.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gusev.constants.PropertyConstants;
import gusev.utils.PropertyManager;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class MainPage {

    private final SelenideElement textBoxInput = $x("//input[@type='text']");
    private final SelenideElement element = $("#app > div > div > div > div > div:nth-child(1) > div > div.card-body > h5");
    private final SelenideElement textBox = $x("//*[@id='item-0']/span");
    private final SelenideElement checkBox = $x("//*[@id='item-1']/span");
    private final SelenideElement radioButton = $x("//*[@id='item-2']/span");

    public MainPage() {
        Selenide.open(PropertyManager.propHandler(PropertyConstants.CONFIG, "UI_HOST"));
    }

    public TextBoxPage goToTextBox() {
        element.click();
        textBox.click();
        return new TextBoxPage();
    }

    public CheckBoxPage goToCheckBox() {
        element.click();
        checkBox.click();
        return new CheckBoxPage();
    }

    public RadioButtonPage goToRadioButton() {
        element.click();
        radioButton.click();
        return new RadioButtonPage();
    }
}