package asserts;

import pages.LoginPage;
import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

public class LoginAssert {

    private final LoginPage page;

    public LoginAssert(LoginPage page) {
        this.page = page;
    }

    @Step("UI: Поле логина видно")
    public LoginAssert usernameIsVisible() {
        page.getUsernameInput().shouldBe(visible);
        return this;
    }

    @Step("UI: Поле пароля видно")
    public LoginAssert passwordIsVisible() {
        page.getPasswordInput().shouldBe(visible);
        return this;
    }

    @Step("UI: Кнопка Sign in видна")
    public LoginAssert signInIsVisible() {
        page.getSignInButton().shouldBe(visible);
        return this;
    }

    @Step("UI: Поле логина содержит '{value}'")
    public LoginAssert usernameHasValue(String value) {
        page.getUsernameInput().shouldHave(value(value));
        return this;
    }

    @Step("UI: Поле пароля содержит '{value}'")
    public LoginAssert passwordHasValue(String value) {
        page.getPasswordInput().shouldHave(value(value));
        return this;
    }

    @Step("UI: Ошибка логина содержит текст '{text}'")
    public LoginAssert errorIsVisibleWithText(String text) {
        page.getErrorAlert().shouldBe(visible).shouldHave(exactText(text));
        return this;
    }
}
