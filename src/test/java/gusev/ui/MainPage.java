package gusev.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gusev.constants.PropertyConstants;
import gusev.utils.PropertyManager;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage {

    private final SelenideElement element = $(byText("Elements"));
    private final SelenideElement forms = $(byText("Forms"));
    private final SelenideElement alertsFrameWindows = $(byText("Alerts, Frame & Windows"));
    private final SelenideElement widgets = $(byText("Widgets"));
    private final SelenideElement interactions = $(byText("Interactions"));
    private final SelenideElement gameStoreApplication = $(byText("Game Store Application"));

    public MainPage() {
        Selenide.open(PropertyManager.propHandler(PropertyConstants.CONFIG, "UI_HOST"));
    }

    private void openElementsBlock() {
        element.click();
    }

    private void openWidgetsBlock() {
        widgets.click();
    }

    private void openFormsBlock() {
        forms.click();
    }

    private void openAlertsFrameWindowsBlock() {
        alertsFrameWindows.click();
    }

    private void openInteractionsBlock() {
        interactions.click();
    }

    private void openGameStoreAppBlock() {
        gameStoreApplication.click();
    }

    @Step("Открыть Text Box")
    public TextBoxPage goToTextBox() {
        openElementsBlock();
        $(byText("Text Box")).click();
        return new TextBoxPage();
    }

    @Step("Открыть Check Box")
    public CheckBoxPage goToCheckBox() {
        openElementsBlock();
        $(byText("Check Box")).click();
        return new CheckBoxPage();
    }

    @Step("Открыть Radio Button")
    public RadioButtonPage goToRadioButton() {
        openElementsBlock();
        $(byText("Radio Button")).click();
        return new RadioButtonPage();
    }

    @Step("Открыть Web Tables")
    public WebTablesPage goToWebTables() {
        openElementsBlock();
        $(byText("Web Tables")).click();
        return new WebTablesPage();
    }

    @Step("Открыть Buttons")
    public ButtonsPage goToButtons() {
        openElementsBlock();
        $(byText("Buttons")).click();
        return new ButtonsPage();
    }

    @Step("Открыть Links")
    public LinksPage goToLinks() {
        openElementsBlock();
        $(byText("Links")).click();
        return new LinksPage();
    }

    @Step("Открыть Broken Links - Images")
    public BrokenLinksAndImagesPage goToBrokenLinksAndImages() {
        openElementsBlock();
        $(byText("Broken Links - Images")).click();
        return new BrokenLinksAndImagesPage();
    }

    @Step("Открыть Upload and Download")
    public UploadAndDownloadPage goToUploadAndDownload() {
        openElementsBlock();
        $(byText("Upload and Download")).click();
        return new UploadAndDownloadPage();
    }

    @Step("Открыть Dynamic Properties")
    public DynamicPropertiesPage goToDynamicProperties() {
        openElementsBlock();
        $(byText("Dynamic Properties")).click();
        return new DynamicPropertiesPage();
    }

    @Step("Открыть Practice Form")
    public PracticeFormPage goToPracticeForm() {
        openFormsBlock();
        $(byText("Practice Form")).click();
        return new PracticeFormPage();
    }

    @Step("Открыть Browser Windows")
    public BrowserWindowsPage goToBrowserWindows() {
        openAlertsFrameWindowsBlock();
        $(byText("Browser Windows")).click();
        return new BrowserWindowsPage();
    }

    @Step("Открыть Alerts")
    public AlertsPage goToAlerts() {
        openAlertsFrameWindowsBlock();
        $(byText("Alerts")).click();
        return new AlertsPage();
    }

    @Step("Открыть Frames")
    public FramesPage goToFrames() {
        openAlertsFrameWindowsBlock();
        $(byText("Frames")).click();
        return new FramesPage();
    }

    @Step("Открыть Nested Frames")
    public NestedFramesPage goToNestedFrames() {
        openAlertsFrameWindowsBlock();
        $(byText("Nested Frames")).click();
        return new NestedFramesPage();
    }

    @Step("Открыть Modal Dialogs")
    public ModalDialogsPage goToModalDialogs() {
        openAlertsFrameWindowsBlock();
        $(byText("Modal Dialogs")).click();
        return new ModalDialogsPage();
    }

    @Step("Открыть Accordian")
    public AccordianPage goToAccordian() {
        openWidgetsBlock();
        $(byText("Accordian")).click();
        return new AccordianPage();
    }

    @Step("Открыть Auto Complete")
    public AutoCompletePage goToAutoComplete() {
        openWidgetsBlock();
        $(byText("Auto Complete")).click();
        return new AutoCompletePage();
    }

    @Step("Открыть Date Picker")
    public DatePickerPage goToDatePicker() {
        openWidgetsBlock();
        $(byText("Date Picker")).click();
        return new DatePickerPage();
    }

    @Step("Открыть Slider")
    public SliderPage goToSlider() {
        openWidgetsBlock();
        $(byText("Slider")).click();
        return new SliderPage();
    }

    @Step("Открыть Progress Bar")
    public ProgressBarPage goToProgressBar() {
        openWidgetsBlock();
        $(byText("Progress Bar")).click();
        return new ProgressBarPage();
    }

    @Step("Открыть Tabs")
    public TabsPage goToTabs() {
        openWidgetsBlock();
        $(byText("Tabs")).click();
        return new TabsPage();
    }

    @Step("Открыть Tool Tips")
    public ToolTipsPage goToToolTips() {
        openWidgetsBlock();
        $(byText("Tool Tips")).click();
        return new ToolTipsPage();
    }

    @Step("Открыть Menu")
    public MenuPage goToMenu() {
        openWidgetsBlock();
        $(byText("Menu")).click();
        return new MenuPage();
    }

    @Step("Открыть Select Menu")
    public SelectMenuPage goToSelectMenu() {
        openWidgetsBlock();
        $(byText("Select Menu")).click();
        return new SelectMenuPage();
    }

    @Step("Открыть Sortable")
    public SortablePage goToSortable() {
        openInteractionsBlock();
        $(byText("Sortable")).click();
        return new SortablePage();
    }

    @Step("Открыть Selectable")
    public SelectablePage goToSelectable() {
        openInteractionsBlock();
        $(byText("Selectable")).click();
        return new SelectablePage();
    }

    @Step("Открыть Resizable")
    public ResizablePage goToResizable() {
        openInteractionsBlock();
        $(byText("Resizable")).click();
        return new ResizablePage();
    }

    @Step("Открыть Droppable")
    public DroppablePage goToDroppable() {
        openInteractionsBlock();
        $(byText("Droppable")).click();
        return new DroppablePage();
    }

    @Step("Открыть Dragabble")
    public DragabblePage goToDragabble() {
        openInteractionsBlock();
        $(byText("Dragabble")).click();
        return new DragabblePage();
    }

    @Step("Открыть Game Store Link Page")
    public LinkPage goToLinkPage() {
        openGameStoreAppBlock();
        $(byText("Link Page")).click();
        return new LinkPage();
    }
}