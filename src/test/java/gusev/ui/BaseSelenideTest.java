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

    /**
     * Инициализация selenide с настройками
     */
    public void setUp() {
        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080"; // Пока не нужно, при запуске на Jenkins.
        Configuration.headless = true;
        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-dev-shm-usage")
                .addArguments("--disable-gpu")
                .addArguments("--remote-allow-origins=*");
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init() {
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}