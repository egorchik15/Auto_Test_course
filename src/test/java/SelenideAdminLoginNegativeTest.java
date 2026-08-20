import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAdminLoginNegativeTest extends BaseUITest {

    @Test
    void loginWithWrongCredentialsShouldShowError() {
        // 1. Открываем страницу логина
        open("/login");

        // 2. Вводим неверный логин
        $("#username").shouldBe(visible).setValue("wrong_user");

        // 3. Вводим неверный пароль
        $("#password").shouldBe(visible).setValue("wrong_password");

        // 4. Нажимаем Sign in
        $("button.primary").shouldBe(visible).click();

        // 5. Проверяем, что показана ошибка
        $("div.alert.alert-danger")
                .shouldBe(visible)
                .shouldHave(exactText("Неверные учетные данные пользователя"));
    }
}
