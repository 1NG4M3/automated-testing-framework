package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPage {
    private final SelenideElement homeFolderArrow = $x("//*[@id='tree-node']/ol/li/span/button");
    private final SelenideElement homeFolderField = $x("//*[@id='tree-node']/ol/li/span/label");
    private final SelenideElement desktopFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[1]/span/label");
    private final SelenideElement documentsFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[2]/span/label");
    private final SelenideElement downloadsFolderField = $x("//*[@id='tree-node']/ol/li/ol/li[3]/span/label");
    private final SelenideElement displayResultField = $x("//*[@id='result']");

    public CheckBoxPage assertHomeFolderText(String expectedResultFolders) {
        homeFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    public CheckBoxPage assertDesktopFolderText(String expectedResultFolders) {
        homeFolderArrow.click();
        desktopFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    public CheckBoxPage assertDocumentsFolderText(String expectedResultFolders) {
        homeFolderArrow.click();
        documentsFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }

    public CheckBoxPage assertDownloadsFolderText(String expectedResultFolders) {
        homeFolderArrow.click();
        downloadsFolderField.click();
        displayResultField.should(Condition.partialText(expectedResultFolders));
        return this;
    }
}