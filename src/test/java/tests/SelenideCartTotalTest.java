package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideCartTotalTest extends BaseUITest {

    @Test
    void cartTotalShouldBeCalculatedCorrectly() {
        openStore();

        double price1 = getProductPriceByIndex(0);
        double price2 = getProductPriceByIndex(1);
        String expectedTotal = formatPrice(price1 + price2);

        addProductToCartByIndex(0);
        addProductToCartByIndex(1);
        openCart();

        checkCartHasAtLeastTwoItems();
        checkCartTotal(expectedTotal);
    }

    // ===================== UI steps =====================

    @Step("UI: открыть витрину")
    private void openStore() {
        open("/");
    }

    @Step("UI: получить цену товара с индексом {index}")
    private double getProductPriceByIndex(int index) {
        var card = $$("#products-list .product-card").get(index).shouldBe(visible);
        return Double.parseDouble(card.getAttribute("data-price"));
    }

    @Step("UI: добавить товар с индексом {index} в корзину")
    private void addProductToCartByIndex(int index) {
        $$("#products-list .product-card")
                .get(index)
                .shouldBe(visible)
                .$("[data-action='add-to-cart']")
                .shouldBe(visible)
                .click();
    }

    @Step("UI: открыть корзину")
    private void openCart() {
        $("#open-cart-btn").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: в корзине есть минимум 2 товара")
    private void checkCartHasAtLeastTwoItems() {
        $$("#cart-items .cart-item").get(0).shouldBe(visible);
        $$("#cart-items .cart-item").get(1).shouldBe(visible);
    }

    @Step("UI-проверка: сумма корзины = {expectedTotal}")
    private void checkCartTotal(String expectedTotal) {
        $("#total-price")
                .shouldBe(visible)
                .shouldHave(exactText(expectedTotal));
    }

    // ===================== helpers =====================

    private String formatPrice(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}