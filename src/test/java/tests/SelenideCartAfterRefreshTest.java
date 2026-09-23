package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideCartAfterRefreshTest extends BaseUITest {

    @Test
    void cartShouldKeepProductsAfterRefresh() {
        openStore();
        String productName = getFirstProductName();
        addFirstProductToCart();
        openCart();
        checkProductInCart(productName, "до refresh");

        refreshPage();
        openCart();
        checkProductInCart(productName, "после refresh");
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

    @Step("UI: обновить страницу")
    private void refreshPage() {
        refresh();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: товар '{productName}' есть в корзине ({stage})")
    private void checkProductInCart(String productName, String stage) {
        $$("#cart-items .cart-item")
                .findBy(text(productName))
                .shouldBe(visible);
    }
}