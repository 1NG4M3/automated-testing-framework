package gusev.tests.ui;

import gusev.ui.BaseSelenideTest;
import gusev.ui.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Epic("Regression")
@Feature("UI")
@Story("Interactions health check")
@Owner("Гусев Дмитрий Викторович")
public class InteractionsTest extends BaseSelenideTest {

    private MainPage page = new MainPage();

    @Test
    @DisplayName("Sortable positive check")
    public void positiveSortableTest() {
        String expectedHeaderText = "Sortable";
        List<String> list = List.of("One", "Two", "Three", "Four", "Five", "Six");
        List<String> listGrid = new ArrayList<>(list);
        listGrid.add("Seven");
        listGrid.add("Eight");
        listGrid.add("Nine");
        page.goToSortable()
                .assertHeaderName(expectedHeaderText)
                .assertListTab(list)
                .assertGridTab(listGrid);
    }

    @Test
    @DisplayName("Selectable positive check")
    public void positiveSelectableTest() {
        String expectedHeaderText = "Selectable";
        List<String> list = List.of("Cras justo odio", "Dapibus ac facilisis in", "Morbi leo risus", "Porta ac consectetur ac");
        List<String> listGrid = List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine");
        page.goToSelectable()
                .assertHeaderName(expectedHeaderText)
                .assertListTab(list)
                .assertGridTab(listGrid);
    }

    @Test
    @DisplayName("Resizable positive check")
    public void positiveResizableTest() {
        String expectedHeaderText = "Resizable";
        String expectedFirstBoxText = "Resizable box, starting at 200x200. Min size is 150x150, max is 500x300.";
        String expectedSecondBoxText = "Resizable";
        page.goToResizable()
                .assertHeaderName(expectedHeaderText)
                .assertResizableBoxWithRestriction(expectedFirstBoxText)
                .assertResizableBox(expectedSecondBoxText);
    }

    @Test
    @Disabled
    @DisplayName("Droppable positive check")
    public void positiveDroppableTest() {
        String expectedHeaderText = "Droppable";
        String expectedTextBefore = "Drop here";
        String expectedTextAfter = "Dropped!";
        page.goToDroppable()
                .assertHeaderName(expectedHeaderText)
                .assertSimpleDrop(expectedTextBefore, expectedTextAfter);
    }

    @Test
    @DisplayName("Dragabble positive check")
    public void positiveDragabbleTest() {
        String expectedHeaderText = "Dragabble";
        String expectedText = "Drag me";

        page.goToDragabble()
                .assertHeaderName(expectedHeaderText)
                .assertSimpleTab(expectedText)
                .assertAxisRestrictedTab()
                .assertContainerRestrictedTab()
                .assertCursorStyleTab();
    }
}