package gusev.tests.ui;

import gusev.ui.MainPage;
import gusev.utils.DateTimeUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Regression")
@Feature("UI")
@Story("Widgets health check")
@Owner("Гусев Дмитрий Викторович")
public class WidgetsTest {

    private MainPage page = new MainPage();

    @Test
    @DisplayName("Accordian positive check")
    public void positiveAccordianTest() {
        String expectedMainText = "Accordian";
        String expectedSection1Heading = "What is Lorem Ipsum?";
        String expectedSection2Heading = "Where does it come from?";
        String expectedSection3Heading = "Why do we use it?";
        String expectedSection1Content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer" +
                " took a galley of type and scrambled it to make a type specimen book. It has survived not only five" +
                " centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was" +
                " popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and" +
                " more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        String expectedSection2Content = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has" +
                " roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard" +
                " McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure" +
                " Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in" +
                " classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and" +
                " 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45" +
                " BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first" +
                " line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                "\n" +
                "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested." +
                " Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in" +
                " their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.";
        String expectedSection3Content = "It is a long established fact that a reader will be distracted by the readable" +
                " content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a" +
                " more-or-less normal distribution of letters, as opposed to using 'Content here, content here'," +
                " making it look like readable English. Many desktop publishing packages and web page editors now use" +
                " Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites" +
                " still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes" +
                " on purpose (injected humour and the like).";

        page.goToAccordian()
                .assertMainText(expectedMainText)
                .assertFirstAccordian(expectedSection1Heading, expectedSection1Content)
                .assertSecondAccordian(expectedSection2Heading, expectedSection2Content)
                .assertThirdAccordian(expectedSection3Heading, expectedSection3Content);
    }

    @Test
    @DisplayName("Auto Complete positive check")
    public void positiveAutoCompleteTest() {
        String expectedMainText = "Auto Complete";
        String expectedAutoCompleteMultipleDescription = "Type multiple color names";
        String expectedAutoCompleteSingleDescription = "Type single color name";
        String expectedFirstFullColorName = "Red";
        String expectedSecondFullColorName = "Green";
        String expectedSecondCutColorName = "Gr";

        page.goToAutoComplete()
                .assertMainText(expectedMainText)
                .assertAutoCompleteMultiple(expectedAutoCompleteMultipleDescription, expectedFirstFullColorName, expectedSecondCutColorName, expectedSecondFullColorName)
                .assertAutoCompleteSingle(expectedAutoCompleteSingleDescription, expectedSecondFullColorName);
    }

    @Test
    @DisplayName("Date Picker positive check")
    public void positiveDatePickerTest() {
        String expectedMainText = "Date Picker";
        String expectedDate = DateTimeUtils.getCurrentDate();
        String expectedDateAndTime = DateTimeUtils.getCurrentDateTime();
        String formattedNewDate = DateTimeUtils.getFutureDate(1, 1, 1);
        String formattedNewDateTime = DateTimeUtils.getFutureDateTime(1, 1, 1, 1, 30);
        
        page.goToDatePicker()
                .assertMainText(expectedMainText)
                .assertDateSelect(expectedDate, formattedNewDate)
                .assertDateAndTimeSelect(expectedDateAndTime, formattedNewDateTime);
    }
}
