package gusev.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class WebTablesPage {
    private final SelenideElement addButton = $x("//*[@id='addNewRecordButton']");
    private final SelenideElement submitButton = $x("//*[@id='submit']");
    private final SelenideElement firstNameField = $x("//*[@id='firstName']");
    private final SelenideElement actualFirstNameField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[1]");
    private final SelenideElement lastNameField = $x("//*[@id='lastName']");
    private final SelenideElement actualLastNameField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[2]");
    private final SelenideElement emailField = $x("//*[@id='userEmail']");
    private final SelenideElement actualEmailField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[4]");
    private final SelenideElement ageField = $x("//*[@id='age']");
    private final SelenideElement actualAgeField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[3]");
    private final SelenideElement salaryField = $x("//*[@id='salary']");
    private final SelenideElement actualSalaryField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[5]");
    private final SelenideElement departmentField = $x("//*[@id='department']");
    private final SelenideElement actualDepartmentField = $x("//*[@id='app']/div/div/div[2]/div[2]/div[1]/div[3]/div[1]/div[2]/div[4]/div/div[6]");
    private final SelenideElement webTablesMainText = $x("//*[@id='app']/div/div/div[1]/div");
    private final SelenideElement deleteRecordButton = $x("//*[@id='delete-record-4']");

    public WebTablesPage assertWebTablesMainText(String expectedResultText) {
        webTablesMainText.should(Condition.partialText(expectedResultText));
        return this;
    }

    public WebTablesPage clickAddButton() {
        addButton.click();
        return this;
    }

    public WebTablesPage setFirstName(String firstName) {
        firstNameField.setValue(firstName);
        return this;
    }

    public WebTablesPage setLastName(String lastName) {
        lastNameField.setValue(lastName);
        return this;
    }

    public WebTablesPage setEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public WebTablesPage setAge(String age) {
        ageField.setValue(age);
        return this;
    }

    public WebTablesPage setSalary(String salary) {
        salaryField.setValue(salary);
        return this;
    }

    public WebTablesPage setDepartment(String department) {
        departmentField.setValue(department);
        return this;
    }

    public WebTablesPage submit() {
        submitButton.click();
        return this;
    }

    public WebTablesPage assertWebTableInputData(String expectedFirstName, String expectedLastName, String expectedEmail, String expectedAge, String expectedSalary, String expectedDepartment) {
        actualFirstNameField.should(Condition.partialText(expectedFirstName));
        actualLastNameField.should(Condition.partialText(expectedLastName));
        actualEmailField.should(Condition.partialText(expectedEmail));
        actualAgeField.should(Condition.partialText(expectedAge));
        actualSalaryField.should(Condition.partialText(expectedSalary));
        actualDepartmentField.should(Condition.partialText(expectedDepartment));
        return this;
    }

    public WebTablesPage deleteRecord() {
        deleteRecordButton.click();
        return this;
    }

    public WebTablesPage assertThatRecordDeleted() {
        actualFirstNameField.shouldBe(Condition.empty);
        actualLastNameField.shouldBe(Condition.empty);
        actualEmailField.shouldBe(Condition.empty);
        actualAgeField.shouldBe(Condition.empty);
        actualSalaryField.shouldBe(Condition.empty);
        actualDepartmentField.shouldBe(Condition.empty);
        return this;
    }
}