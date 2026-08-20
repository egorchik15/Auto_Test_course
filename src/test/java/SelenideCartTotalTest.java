import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideCartTotalTest extends BaseUITest {

    @Test
    void cartTotalShouldBeCalculatedCorrectly() {
        // 1. Открыть витрину
        open("/");

        // 2. Взять два разных товара
        var first = $$("#products-list .product-card").get(0).shouldBe(visible);
        var second = $$("#products-list .product-card").get(1).shouldBe(visible);

        // 3. Прочитать цены из data-price
        double price1 = Double.parseDouble(first.getAttribute("data-price"));
        double price2 = Double.parseDouble(second.getAttribute("data-price"));
        int expectedTotal = (int) (price1 + price2);

        // 4. Добавить оба товара в корзину
        first.$("[data-action='add-to-cart']").shouldBe(visible).click();
        second.$("[data-action='add-to-cart']").shouldBe(visible).click();

        // 5. Открыть корзину
        $("#open-cart-btn").shouldBe(visible).click();

        // 6. Проверить, что оба товара отображаются в корзине
        $$("#cart-items .cart-item").get(0).shouldBe(visible);
        $$("#cart-items .cart-item").get(1).shouldBe(visible);

        // 7. Проверить, что сумма в #total-price посчитана корректно
        $("#total-price")
                .shouldBe(visible)
                .shouldHave(exactText(String.valueOf(expectedTotal)));
    }
}