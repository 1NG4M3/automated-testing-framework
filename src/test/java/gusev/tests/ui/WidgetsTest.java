package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import gusev.utils.DateTimeUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static gusev.utils.Texts.*;

@Epic("Regression")
@Feature("UI")
@Story("Widgets health check")
@Owner("Гусев Дмитрий Викторович")
public class WidgetsTest extends BaseSelenideTest {

    private final MainPage page = new MainPage();

    // -------- Accordian Section --------
    @Test
    @DisplayName("Check Accordian content")
    void checkAccordian() {
        page.goToAccordian()
                .assertMainText(ACCORDIAN_MAIN_TEXT)
                .assertFirstAccordian(ACCORDIAN_TITLE_1, ACCORDIAN_SECTION_1)
                .assertSecondAccordian(ACCORDIAN_TITLE_2, ACCORDIAN_SECTION_2)
                .assertThirdAccordian(ACCORDIAN_TITLE_3, ACCORDIAN_SECTION_3);
    }

    // -------- Auto Complete Section --------
    @Test
    @DisplayName("Check Auto Complete behavior")
    void checkAutoComplete() {
        page.goToAutoComplete()
                .assertMainText(AUTO_COMPLETE_MAIN_TEXT)
                .assertAutoCompleteMultiple(AUTO_COMPLETE_MULTI_LABEL, AUTO_COMPLETE_COLOR_1, AUTO_COMPLETE_COLOR_PARTIAL, AUTO_COMPLETE_COLOR_2)
                .assertAutoCompleteSingle(AUTO_COMPLETE_SINGLE_LABEL, AUTO_COMPLETE_COLOR_2);
    }

    // -------- Date Picker Section --------
    @Test
    @DisplayName("Check Date Picker functionality")
    void checkDatePicker() {
        String today = DateTimeUtils.getCurrentDate();
        String now = DateTimeUtils.getCurrentDateTime();
        String futureDate = DateTimeUtils.getFutureDate(1, 1, 1);
        String futureDateTime = DateTimeUtils.getFutureDateTime(1, 1, 1, 1, 30);

        page.goToDatePicker()
                .assertMainText(DATE_PICKER_MAIN_TEXT)
                .assertDateSelect(today, futureDate)
                .assertDateAndTimeSelect(now, futureDateTime);
    }

    // -------- Slider Section --------
    @Test
    @DisplayName("Check Slider default value")
    void checkSlider() {
        page.goToSlider()
                .assertHeaderText(SLIDER_MAIN_TEXT)
                .setSliderValue(SLIDER_DEFAULT_VALUE);
    }

    // -------- Progress Bar Section --------
    @Test
    @DisplayName("Check Progress Bar flow")
    void checkProgressBar() {
        page.goToProgressBar()
                .assertMainText(PROGRESS_BAR_MAIN_TEXT)
                .checkProgressBar(PROGRESS_BAR_START, PROGRESS_BAR_STOP, PROGRESS_BAR_RESET, PROGRESS_BAR_HALF, PROGRESS_BAR_FULL, PROGRESS_BAR_TIMEOUT_MS);
    }

    // -------- Tabs Section --------
    @Test
    @DisplayName("Check Tabs content")
    void checkTabs() {
        page.goToTabs()
                .assertHeader(TABS_MAIN_TEXT)
                .assertWhatTab(TABS_WHAT_TITLE, TABS_WHAT)
                .assertOriginTab(TABS_ORIGIN_TITLE, TABS_ORIGIN)
                .assertUseTab(TABS_USE_TITLE, TABS_USE)
                .assertMoreTab(TABS_MORE_TITLE);
    }

    // -------- Tool Tips Section --------
    @Test
    @DisplayName("Check Tool Tips")
    void checkToolTips() {
        page.goToToolTips()
                .assertHeaderName(TOOL_TIP_HEADER)
                .assertToolTipButton(TOOL_TIP_BUTTON_LABEL, TOOL_TIP_BUTTON_TEXT)
                .assertInputField(TOOL_TIP_INPUT_LABEL, TOOL_TIP_INPUT_TEXT, TOOL_TIP_INPUT_VALUE)
                .assertMainText(TOOL_TIP_MAIN, TOOL_TIP_CONTRARY, TOOL_TIP_CONTRARY_TEXT, TOOL_TIP_NUMBER, TOOL_TIP_NUMBER_TEXT, TOOL_TIP_COLOR);
    }

    // -------- Menu Section --------
    @Test
    @DisplayName("Check Menu items visibility")
    void checkMenu() {
        page.goToMenu()
                .assertHeaderName(MENU_HEADER)
                .assertMainItemsBackgroundColor(MENU_MAIN_ITEM_1, MENU_MAIN_ITEM_2, MENU_MAIN_ITEM_3)
                .assertInvisibilitySubMenus(MENU_SUB_ITEM, MENU_SUB_LIST, MENU_SUB_SUB_ITEM_1, MENU_SUB_SUB_ITEM_2)
                .assertVisibilitySubMenuMainItem2(MENU_MAIN_ITEM_2, MENU_SUB_ITEM, MENU_SUB_LIST)
                .assertVisibilitySubMenuOfSubSubList(MENU_SUB_LIST, MENU_SUB_SUB_ITEM_1, MENU_SUB_SUB_ITEM_2);
    }

    // -------- Select Menu Section --------
    @Test
    @DisplayName("Check Select Menu options")
    void checkSelectMenu() {
        page.goToSelectMenu()
                .assertHeaderName(SELECT_MENU_HEADER)
                .selectValue(SELECT_MENU_VALUE)
                .selectOne(SELECT_MENU_ONE)
                .selectOldStyle(SELECT_MENU_OLD_STYLE)
                .selectFromMultiDropdown(SELECT_MENU_MULTI_DROP, SELECT_MENU_MULTI_DROP_EXPECTED_ID_VALUE)
                .selectFromStandardMulti(SELECT_MENU_STANDARD);
    }
}