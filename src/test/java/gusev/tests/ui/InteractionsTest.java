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

import java.util.List;

@Epic("Regression")
@Feature("UI")
@Story("Interactions health check")
@Owner("Гусев Дмитрий Викторович")
public class InteractionsTest extends BaseSelenideTest {

    private final MainPage page = new MainPage();

    @Test
    @DisplayName("Sortable positive check")
    public void positiveSortableTest() {
        page.goToSortable()
                .assertHeader("Sortable")
                .assertInitialListOrder(List.of("One", "Two", "Three", "Four", "Five", "Six"))
                .reorderListTabItems()
                .assertNewListOrder(List.of("Three", "One", "Two", "Four", "Five", "Six"))
                .assertInitialGridOrder(List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"))
                .reorderGridTabItems()
                .assertNewGridOrder(List.of("One", "Eight", "Two", "Three", "Four", "Five", "Six", "Seven", "Nine"));
    }

    @Test
    @DisplayName("Selectable positive check")
    public void positiveSelectableTest() {
        page.goToSelectable()
                .assertHeaderName("Selectable")
                .assertListTab(List.of(
                        "Cras justo odio",
                        "Dapibus ac facilisis in",
                        "Morbi leo risus",
                        "Porta ac consectetur ac"
                ))
                .assertGridTab(List.of("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"));
    }

    @Test
    @DisplayName("Resizable positive check")
    public void positiveResizableTest() {
        page.goToResizable()
                .assertHeaderName("Resizable")
                .assertResizableBoxWithRestriction("Resizable box, starting at 200x200. Min size is 150x150, max is 500x300.")
                .assertResizableBox("Resizable");
    }

    @Test
    @Disabled("Feature under development or flaky in CI")
    @DisplayName("Droppable positive check")
    public void positiveDroppableTest() {
        page.goToDroppable()
                .assertHeaderName("Droppable")
                .assertSimpleDrop("Drop here", "Dropped!");
    }

    @Test
    @DisplayName("Draggable positive check")
    public void positiveDraggableTest() {
        page.goToDragabble()
                .verifyHeaderName("Dragabble")
                .verifySimpleTab("Drag me")
                .verifyAxisRestrictedTab()
                .verifyContainerRestrictedTab()
                .verifyCursorStyleTab();
    }
}