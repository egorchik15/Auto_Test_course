package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropToCartTest extends BaseUITest {

    @Test
    void shouldDragProductToCart() {
        openStore();
        String productName = getFirstProductName();
        dragFirstProductToCart();
        openCart();
        checkCartIsNotEmpty();
        checkProductInCart(productName);
    }

    // ===================== UI steps =====================

    @Step("UI: открыть витрину")
    private void openStore() {
        open("/");
    }

    @Step("UI: получить название первого товара")
    private String getFirstProductName() {
        return $$("#products-list .product-card").first().shouldBe(visible).$("h4").getText();
    }

    @Step("UI: перетащить первый товар в корзину (drag-and-drop)")
    private void dragFirstProductToCart() {
        var productCard = $$("#products-list .product-card").first().shouldBe(visible);
        var cartButton = $("#open-cart-btn").shouldBe(visible);

        actions()
                .clickAndHold(productCard)
                .moveToElement(cartButton)
                .pause(500)
                .release()
                .perform();
    }

    @Step("UI: открыть корзину")
    private void openCart() {
        $("#open-cart-btn").shouldBe(visible).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: корзина не пустая")
    private void checkCartIsNotEmpty() {
        $$("#cart-items .cart-item").shouldHave(sizeGreaterThan(0));
    }

    @Step("UI-проверка: товар '{productName}' есть в корзине")
    private void checkProductInCart(String productName) {
        $$("#cart-items .cart-item")
                .findBy(text(productName))
                .shouldBe(visible);
    }
}