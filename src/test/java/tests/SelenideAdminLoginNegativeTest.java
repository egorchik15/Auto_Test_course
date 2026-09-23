package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAdminLoginNegativeTest extends BaseUITest {

    @Test
    void loginWithWrongCredentialsShouldShowError() {
        openLoginPage();
        enterCredentials("wrong_user", "wrong_password");
        clickSignIn();
        checkLoginError("Неверные учетные данные пользователя");
    }

    // ===================== UI steps =====================

    @Step("UI: открыть страницу логина")
    private void openLoginPage() {
        open("/login");
    }

    @Step("UI: ввести логин '{username}' и пароль")
    private void enterCredentials(String username, String password) {
        $("#username").shouldBe(visible).setValue(username);
        $("#password").shouldBe(visible).setValue(password);
    }

    @Step("UI: нажать Sign in")
    private void clickSignIn() {
        $("button.primary").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: ошибка логина = '{expectedText}'")
    private void checkLoginError(String expectedText) {
        $("div.alert.alert-danger")
                .shouldBe(visible)
                .shouldHave(exactText(expectedText));
    }
}