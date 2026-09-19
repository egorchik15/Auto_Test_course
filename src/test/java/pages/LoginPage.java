package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    // элементы формы
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement signInButton = $("button.primary");

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage clickSignIn() {
        signInButton.click();
        return this;
    }

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
