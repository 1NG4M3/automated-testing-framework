package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPage {
    private final SelenideElement fullName = $x("//*[@id='userName']");
    private final SelenideElement email = $x("//*[@id='userEmail']");
    private final SelenideElement currentAddress = $x("//*[@id='currentAddress']");
    private final SelenideElement permanentAddress = $x("//*[@id='permanentAddress']");
    private final SelenideElement submitTextBox = $x("//*[@id='submit']");

    private final SelenideElement outputName = $x("//*[@id='name']");
    private final SelenideElement outputEmail = $x("//*[@id='email']");
    private final SelenideElement outputCurrentAddress = $x("//*[@id='output']//*[@id='currentAddress']");
    private final SelenideElement outputPermanentAddress = $x("//*[@id='output']//*[@id='permanentAddress']");

    public TextBoxPage setFullName(String fullNameValue){
        fullName.setValue(fullNameValue);
        return this;
    }

    public TextBoxPage setEmail(String emailValue){
        email.setValue(emailValue);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddressValue){
        currentAddress.setValue(currentAddressValue);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddressValue){
        permanentAddress.setValue(permanentAddressValue);
        return this;
    }

    public TextBoxPage submit(){
        submitTextBox.click();
        return this;
    }

    public TextBoxPage assertName(String expectedName){
        outputName.should(Condition.partialText(expectedName));
        return this;
    }

    public TextBoxPage assertEmail(String expectedEmail){
        outputEmail.should(Condition.partialText(expectedEmail));
        return this;
    }

    public TextBoxPage assertCurrentAddress(String expectedCurrentAddress){
        outputCurrentAddress.should(Condition.partialText(expectedCurrentAddress));
        return this;
    }

    public TextBoxPage assertPermanentAddress(String expectedPermanentAddress){
        outputPermanentAddress.should(Condition.partialText(expectedPermanentAddress));
        return this;
    }
}