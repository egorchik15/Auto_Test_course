package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAdminAddProductNotificationTest extends BaseUITest {

    @Test
    void addProductInAdminShouldShowNotification() {
        String productName = "Admin Notify Product";
        String productPrice = "77";

        // 1. Открыть страницу логина
        open("/login");

        // 2. Ввести логин
        $("#username").shouldBe(visible).setValue("admin");

        // 3. Ввести пароль
        $("#password").shouldBe(visible).setValue("secret123");

        // 4. Нажать Sign in
        $("button.primary").shouldBe(visible).click();

        // 5. Заполнить название товара
        $("#n-name").shouldBe(visible).setValue(productName);

        // 6. Заполнить цену
        $("#n-price").shouldBe(visible).setValue(productPrice);

        // 7. Нажать "Создать"
        $("#add-btn").shouldBe(visible).click();

        // 8. Проверить тостер после создания товара
        $(".toast")
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));
    }
}
