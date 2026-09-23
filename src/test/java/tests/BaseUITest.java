package tests;

import com.codeborne.selenide.Configuration;
import config.TestConfig;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseUITest {

    @BeforeAll
    static void setUpAll() {
        // 1. Печатаем конфиг перед запуском (без credentials)
        TestConfig.printConfig();

        // 2. Применяем параметры из конфига
        Configuration.baseUrl = TestConfig.getUiBaseUrl();
        Configuration.timeout = TestConfig.getTimeout();
        Configuration.browser = "chrome";

        // ===== Allure Selenide Listener =====
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }
}
