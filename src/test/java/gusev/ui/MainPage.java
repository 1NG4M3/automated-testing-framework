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
    private final SelenideElement webTablesButton = $x("//*[@id='item-3']/span");
    private final SelenideElement buttons = $x("//*[@id='item-4']/span");
    private final SelenideElement links = $x("//*[@id='item-5']/span");
    private final SelenideElement brokenLinksAndImages = $x("//*[@id='item-6']/span");
    private final SelenideElement uploadAndDownload = $x("//*[@id='item-7']/span");

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

    public WebTablesPage goToWebTables() {
        element.click();
        webTablesButton.click();
        return new WebTablesPage();
    }

    public ButtonsPage goToButtons() {
        element.click();
        buttons.click();
        return new ButtonsPage();
    }

    public LinksPage goToLinks() {
        element.click();
        links.click();
        return new LinksPage();
    }

    public BrokenLinksAndImagesPage goToBrokenLinksAndImages() {
        element.click();
        brokenLinksAndImages.click();
        return new BrokenLinksAndImagesPage();
    }

    public UploadAndDownloadPage goToUploadAndDownload() {
        element.click();
        uploadAndDownload.click();
        return new UploadAndDownloadPage();
    }
}