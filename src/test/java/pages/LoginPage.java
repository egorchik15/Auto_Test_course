package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.Step;

public class LoginPage {

    // элементы формы
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement signInButton = $("button.primary");

    @Step("Открыть страницу логина")
    public LoginPage openPage() {
        open("/login");
        return this;
    }

    @Step("Ввести логин '{username}'")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать Sign in")
    public LoginPage clickSignIn() {
        signInButton.click();
        return this;
    }

    @Step("Войти как '{username}'")
    public AdminPage loginAs(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickSignIn();
        return new AdminPage();
    }

    // доступ для Assert-класса
    public SelenideElement getUsernameInput() {
        return usernameInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public SelenideElement getSignInButton() {
        return signInButton;
    }

    public SelenideElement getErrorAlert() {
        return $("div.alert.alert-danger");
    }
}
