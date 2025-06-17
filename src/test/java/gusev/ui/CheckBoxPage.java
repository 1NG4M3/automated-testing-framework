package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {

    private final SelenideElement homeFolderArrow = $x("//*[@id='tree-node']/ol/li/span/button");
    private final SelenideElement homeFolderField = $x("//*[@id='tree-node']/ol/li/span/label");
    private final SelenideElement desktopFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[1]/span/label");
    private final SelenideElement documentsFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[2]/span/label");
    private final SelenideElement downloadsFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[3]/span/label");
    private final SelenideElement displayResultField = $x("//*[@id='result']");

    @Step("Выбор чекбокса 'Home' и проверка текста результата: '{expectedResultFolders}'")
    public CheckBoxPage assertHomeFolderText(String expectedResultFolders) {
        homeFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    @Step("Выбор чекбокса 'Desktop' и проверка текста результата: '{expectedResultFolders}'")
    public CheckBoxPage assertDesktopFolderText(String expectedResultFolders) {
        expandHomeFolderIfNeeded();
        desktopFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    @Step("Выбор чекбокса 'Documents' и проверка текста результата: '{expectedResultFolders}'")
    public CheckBoxPage assertDocumentsFolderText(String expectedResultFolders) {
        expandHomeFolderIfNeeded();
        documentsFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    @Step("Выбор чекбокса 'Downloads' и проверка текста результата: '{expectedResultFolders}'")
    public CheckBoxPage assertDownloadsFolderText(String expectedResultFolders) {
        expandHomeFolderIfNeeded();
        downloadsFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    @Step("Раскрытие папки 'Home' при необходимости")
    private void expandHomeFolderIfNeeded() {
        if (!desktopFolderField.isDisplayed()) {
            homeFolderArrow.click();
        }
    }
}