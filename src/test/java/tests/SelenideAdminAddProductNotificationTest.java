package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAdminAddProductNotificationTest extends BaseUITest {

    @Test
    void addProductInAdminShouldShowNotification() {
        String productName = "Admin Notify Product";
        String productPrice = "77";

        openLoginPage();
        loginAs("admin", "secret123");
        createProduct(productName, productPrice);
        checkProductAddedToast();
    }

    // ===================== UI steps =====================

    @Step("UI: открыть страницу логина")
    private void openLoginPage() {
        open("/login");
    }

    @Step("UI: войти как '{username}'")
    private void loginAs(String username, String password) {
        $("#username").shouldBe(visible).setValue(username);
        $("#password").shouldBe(visible).setValue(password);
        $("button.primary").shouldBe(visible).click();
    }

    @Step("UI: создать товар name='{name}', price='{price}'")
    private void createProduct(String name, String price) {
        $("#n-name").shouldBe(visible).setValue(name);
        $("#n-price").shouldBe(visible).setValue(price);
        $("#add-btn").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: тост «Товар успешно добавлен!»")
    private void checkProductAddedToast() {
        $(".toast")
                .shouldBe(visible)
                .shouldHave(text("Товар успешно добавлен!"));
    }
}