package asserts;

import pages.LoginPage;
import static com.codeborne.selenide.Condition.*;

public class LoginAssert {

    private final LoginPage page;

    public LoginAssert(LoginPage page) {
        this.page = page;
    }

    public LoginAssert usernameIsVisible() {
        page.getUsernameInput().shouldBe(visible);
        return this;
    }

    public LoginAssert passwordIsVisible() {
        page.getPasswordInput().shouldBe(visible);
        return this;
    }

    public LoginAssert signInIsVisible() {
        page.getSignInButton().shouldBe(visible);
        return this;
    }

    public LoginAssert usernameHasValue(String value) {
        page.getUsernameInput().shouldHave(value(value));
        return this;
    }

    public LoginAssert passwordHasValue(String value) {
        page.getPasswordInput().shouldHave(value(value));
        return this;
    }

    public LoginAssert errorIsVisibleWithText(String text) {
        page.getErrorAlert().shouldBe(visible).shouldHave(exactText(text));
        return this;
    }
}
