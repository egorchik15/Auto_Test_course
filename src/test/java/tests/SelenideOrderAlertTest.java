package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideOrderAlertTest extends BaseUITest {

    @Test
    void orderOver300ShouldShowJsAlert() {
        String productName = "Expensive Product 150";
        String productPrice = "150";

        openLoginPage();
        loginAs("admin", "secret123");
        createProduct(productName, productPrice);

        openStore();
        addProductToCartThreeTimes(productName);
        openCart();
        makeOrder();
        checkMoneyLimitAlert();
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

    @Step("UI: открыть витрину")
    private void openStore() {
        open("/");
    }

    @Step("UI: добавить товар '{productName}' в корзину 3 раза")
    private void addProductToCartThreeTimes(String productName) {
        var card = $$("#products-list .product-card")
                .findBy(text(productName))
                .shouldBe(visible);

        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
    }

    @Step("UI: открыть корзину")
    private void openCart() {
        $("#open-cart-btn").shouldBe(visible).click();
    }

    @Step("UI: нажать «Оформить заказ»")
    private void makeOrder() {
        $("#makeOrder").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: JS Alert о лимите 300 ₽")
    private void checkMoneyLimitAlert() {
        String alertText = confirm();

        assertThat(alertText)
                .as("Должен появиться alert о превышении лимита 300 ₽")
                .contains("Денег не хватает")
                .contains("300");
    }
}