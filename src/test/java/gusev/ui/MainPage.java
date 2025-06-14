package gusev.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import gusev.constants.PropertyConstants;
import gusev.utils.PropertyManager;
import lombok.Data;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Data
public class MainPage {

    private final SelenideElement textBoxInput = $x("//input[@type='text']");
    private final SelenideElement element = $("#app > div > div > div > div > div:nth-child(1) > div > div.card-body > h5");
    private final SelenideElement textBox = $x("//*[@id='item-0']/span");
    private final SelenideElement checkBox = $x("//*[@id='item-1']/span");
    private final SelenideElement radioButton = $x("//*[@id='item-2']/span");
    private final SelenideElement webTablesButton = $x("//*[@id='item-3']/span");
    private final SelenideElement buttons = $x("//*[@id='item-4']/span");
    private final SelenideElement links = $x("//*[@id='item-5']/span");
    private final SelenideElement brokenLinksAndImages = $x("//*[@id='item-6']/span");
    private final SelenideElement uploadAndDownload = $x("//*[@id='item-7']/span");
    private final SelenideElement dynamicProperties = $x("//*[@id='item-8']/span");
    private final SelenideElement forms = $x("//*[contains(text(), 'Forms')]");
    private final SelenideElement practiceForm = $x("//*[contains(text(), 'Practice Form')]");
    private final SelenideElement alertsFrameWindows = $x("//*[contains(text(), 'Alerts, Frame & Windows')]");
    private final SelenideElement browserWindows = $x("//*[contains(text(), 'Browser Windows')]");
    private final SelenideElement alerts = $(byText("Alerts"));
    private final SelenideElement frames = $(byText("Frames"));
    private final SelenideElement nestedFrames = $(byText("Nested Frames"));
    private final SelenideElement modalDialogs = $(byText("Modal Dialogs"));
    private final SelenideElement widgets = $(byText("Widgets"));
    private final SelenideElement accordian = $(byText("Accordian"));
    private final SelenideElement autoComplete = $(byText("Auto Complete"));
    private final SelenideElement datePicker = $(byText("Date Picker"));
    private final SelenideElement slider = $(byText("Slider"));
    private final SelenideElement progressBar = $(byText("Progress Bar"));
    private final SelenideElement tabs = $(byText("Tabs"));
    private final SelenideElement toolTips = $(byText("Tool Tips"));
    private final SelenideElement menu = $(byText("Menu"));
    private final SelenideElement selectMenu = $(byText("Select Menu"));
    private final SelenideElement interactions = $(byText("Interactions"));
    private final SelenideElement sortable = $(byText("Sortable"));
    private final SelenideElement selectable = $(byText("Selectable"));
    private final SelenideElement resizable = $(byText("Resizable"));
    private final SelenideElement droppable = $(byText("Droppable"));
    private final SelenideElement dragabble = $(byText("Dragabble"));
    private final SelenideElement gameStoreApplication = $(byText("Game Store Application"));
    private final SelenideElement linkPage = $(byText("Link Page"));

    public MainPage() {
        Selenide.open(PropertyManager.propHandler(PropertyConstants.CONFIG, "UI_HOST"));
    }

    public TextBoxPage goToTextBox() {
        element.click();
        textBox.click();
        return new TextBoxPage();
    }

    public CheckBoxPage goToCheckBox() {
        element.click();
        checkBox.click();
        return new CheckBoxPage();
    }

    public RadioButtonPage goToRadioButton() {
        element.click();
        radioButton.click();
        return new RadioButtonPage();
    }

    public WebTablesPage goToWebTables() {
        element.click();
        webTablesButton.click();
        return new WebTablesPage();
    }

    public ButtonsPage goToButtons() {
        element.click();
        buttons.click();
        return new ButtonsPage();
    }

    public LinksPage goToLinks() {
        element.click();
        links.click();
        return new LinksPage();
    }

    public BrokenLinksAndImagesPage goToBrokenLinksAndImages() {
        element.click();
        brokenLinksAndImages.click();
        return new BrokenLinksAndImagesPage();
    }

    public UploadAndDownloadPage goToUploadAndDownload() {
        element.click();
        uploadAndDownload.click();
        return new UploadAndDownloadPage();
    }

    public DynamicPropertiesPage goToDynamicProperties() {
        element.click();
        dynamicProperties.click();
        return new DynamicPropertiesPage();
    }

    public PracticeFormPage goToPracticeForm() {
        forms.click();
        practiceForm.click();
        return new PracticeFormPage();
    }

    public BrowserWindowsPage goToBrowserWindows() {
        alertsFrameWindows.click();
        browserWindows.click();
        return new BrowserWindowsPage();
    }

    public AlertsPage goToAlerts() {
        alertsFrameWindows.click();
        alerts.click();
        return new AlertsPage();
    }

    public FramesPage goToFrames() {
        alertsFrameWindows.click();
        frames.click();
        return new FramesPage();
    }

    public NestedFramesPage goToNestedFrames() {
        alertsFrameWindows.click();
        nestedFrames.click();
        return new NestedFramesPage();
    }

    public ModalDialogsPage goToModalDialogs() {
        alertsFrameWindows.click();
        modalDialogs.click();
        return new ModalDialogsPage();
    }

    public AccordianPage goToAccordian() {
        widgets.click();
        accordian.click();
        return new AccordianPage();
    }

    public AutoCompletePage goToAutoComplete() {
        widgets.click();
        autoComplete.click();
        return new AutoCompletePage();
    }

    public DatePickerPage goToDatePicker() {
        widgets.click();
        datePicker.click();
        return new DatePickerPage();
    }

    public SliderPage goToSlider() {
        widgets.click();
        slider.click();
        return new SliderPage();
    }

    public ProgressBarPage goToProgressBar() {
        widgets.click();
        progressBar.click();
        return new ProgressBarPage();
    }

    public TabsPage goToTabs() {
        widgets.click();
        tabs.click();
        return new TabsPage();
    }

    public ToolTipsPage goToToolTips() {
        widgets.click();
        toolTips.click();
        return new ToolTipsPage();
    }

    public MenuPage goToMenu() {
        widgets.click();
        menu.click();
        return new MenuPage();
    }

    public SelectMenuPage goToSelectMenu() {
        widgets.click();
        selectMenu.click();
        return new SelectMenuPage();
    }

    public SortablePage goToSortable() {
        interactions.click();
        sortable.click();
        return new SortablePage();
    }

    public SelectablePage goToSelectable() {
        interactions.click();
        selectable.click();
        return new SelectablePage();
    }

    public ResizablePage goToResizable() {
        interactions.click();
        resizable.click();
        return new ResizablePage();
    }

    public DroppablePage goToDroppable() {
        interactions.click();
        droppable.click();
        return new DroppablePage();
    }

    public DragabblePage goToDragabble() {
        interactions.click();
        dragabble.click();
        return new DragabblePage();
    }

    public LinkPage goToLinkPage() {
        gameStoreApplication.click();
        linkPage.click();
        return new LinkPage();
    }
}