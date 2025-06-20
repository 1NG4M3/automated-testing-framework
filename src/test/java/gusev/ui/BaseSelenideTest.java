package gusev.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest {

    protected MainPage page;
    /**
     * Инициализация selenide с настройками
     */
    public static void setUp() {
        Configuration.remote = "http://chrome:9515";
        Configuration.browser = "chrome";
        Configuration.headless = true;
//        Configuration.headless = false;
//        Configuration.browserSize = "1920x1080";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        Configuration.browserCapabilities = options;
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init() {
        setUp();
        page = new MainPage();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}