package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAddProductToCartTest extends BaseUITest {

    @Test
    void productShouldAppearInCart() {
        openStore();
        String productName = getFirstProductName();
        addFirstProductToCart();
        openCart();
        checkProductInCart(productName);
    }

    // ===================== UI steps =====================

    @Step("UI: открыть витрину")
    private void openStore() {
        open("/");
    }

    @Step("UI: получить название первого товара")
    private String getFirstProductName() {
        return $$("#products-list .product-card").first().$("h4").getText();
    }

    @Step("UI: добавить первый товар в корзину")
    private void addFirstProductToCart() {
        $$("#products-list .product-card").first()
                .$("[data-action='add-to-cart']")
                .shouldBe(visible)
                .click();
    }

    @Step("UI: открыть корзину")
    private void openCart() {
        $("#open-cart-btn").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: товар '{productName}' отображается в корзине")
    private void checkProductInCart(String productName) {
        $$("#cart-items .cart-item")
                .findBy(text(productName))
                .shouldBe(visible);
    }
}