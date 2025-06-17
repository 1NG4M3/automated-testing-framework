package gusev.ui;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage {

    private final SelenideElement addButton = $x("//*[@id='addNewRecordButton']");
    private final SelenideElement submitButton = $x("//*[@id='submit']");
    private final SelenideElement firstNameField = $x("//*[@id='firstName']");
    private final SelenideElement lastNameField = $x("//*[@id='lastName']");
    private final SelenideElement emailField = $x("//*[@id='userEmail']");
    private final SelenideElement ageField = $x("//*[@id='age']");
    private final SelenideElement salaryField = $x("//*[@id='salary']");
    private final SelenideElement departmentField = $x("//*[@id='department']");
    private final ElementsCollection tableRows = $$x("//div[@class='rt-tbody']/div[@class='rt-tr-group']");
    private final SelenideElement mainHeader = $x("//*[@id='app']/div/div/div[1]/div");

    @Step("Проверить заголовок таблицы: '{expectedText}'")
    public WebTablesPage assertMainHeader(String expectedText) {
        mainHeader.shouldHave(Condition.partialText(expectedText));
        return this;
    }

    @Step("Нажать кнопку Add")
    public WebTablesPage clickAddButton() {
        addButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Заполнить First Name: {value}")
    public WebTablesPage setFirstName(String value) {
        firstNameField.setValue(value);
        return this;
    }

    @Step("Заполнить Last Name: {value}")
    public WebTablesPage setLastName(String value) {
        lastNameField.setValue(value);
        return this;
    }

    @Step("Заполнить Email: {value}")
    public WebTablesPage setEmail(String value) {
        emailField.setValue(value);
        return this;
    }

    @Step("Заполнить Age: {value}")
    public WebTablesPage setAge(String value) {
        ageField.setValue(value);
        return this;
    }

    @Step("Заполнить Salary: {value}")
    public WebTablesPage setSalary(String value) {
        salaryField.setValue(value);
        return this;
    }

    @Step("Заполнить Department: {value}")
    public WebTablesPage setDepartment(String value) {
        departmentField.setValue(value);
        return this;
    }

    @Step("Нажать Submit")
    public WebTablesPage submit() {
        submitButton.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Проверить последнюю строку таблицы: {firstName}, {lastName}, {email}, {age}, {salary}, {department}")
    public WebTablesPage assertLastRow(
            String firstName,
            String lastName,
            String email,
            String age,
            String salary,
            String department
    ) {
        tableRows.shouldBe(CollectionCondition.sizeGreaterThan(0));
        ElementsCollection lastRowCells = tableRows.last().$$("div.rt-td");

        lastRowCells.get(0).shouldHave(Condition.text(firstName));
        lastRowCells.get(1).shouldHave(Condition.text(lastName));
        lastRowCells.get(2).shouldHave(Condition.text(age));
        lastRowCells.get(3).shouldHave(Condition.text(email));
        lastRowCells.get(4).shouldHave(Condition.text(salary));
        lastRowCells.get(5).shouldHave(Condition.text(department));

        return this;
    }

    @Step("Удалить последнюю запись")
    public WebTablesPage deleteLastRow() {
        tableRows.last().$x(".//span[@title='Delete']").click();
        return this;
    }

    @Step("Проверить, что таблица пуста")
    public WebTablesPage assertTableIsEmpty() {
        tableRows.shouldHave(CollectionCondition.size(0));
        return this;
    }
}
