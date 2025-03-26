package gusev.tests.ui;

import com.codeborne.selenide.junit5.SoftAssertsExtension;
import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith({SoftAssertsExtension.class})
@Epic("Regression")
@Feature("UI")
@Story("Forms health check")
@Owner("Гусев Дмитрий Викторович")
public class FormsTest extends BaseSelenideTest {

    private MainPage page = new MainPage();

    @CsvSource(value = {
            "Practice Form, Student Registration Form, Dmitry, Gusev, d.gusev@mail.ru, Male, 9522280353, Maths, Saint P. Russia, NCR, Delhi, MyPhotoForUpload.jpeg, Sports, 02 December"
    })
    @ParameterizedTest(name = "{0}")
    @DisplayName("Form positive check")
    public void positiveFormTest(String expectedMainText, String expectedFormName, String firstName, String lastName,
                                 String email, String gender, String mobileNumber, String subjects,
                                 String currentAddress, String state, String city, String picturePath, String hobby, String expectedDateOfBirth) {

        page.goToPracticeForm()
                .assertMainText(expectedMainText)
                .assertFormName(expectedFormName)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(mobileNumber)
                .setDateOfBirth()
                .setSubjects(subjects)
                .setHobby()
                .setPicture()
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit()
                .assertModalContent(firstName,lastName,email,gender,mobileNumber,expectedDateOfBirth,subjects,hobby,picturePath,currentAddress,state,city);
    }
}