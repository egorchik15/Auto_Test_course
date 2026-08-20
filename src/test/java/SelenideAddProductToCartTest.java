import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAddProductToCartTest extends BaseUITest {

    @Test
    void productShouldAppearInCart() {
        // 1. Открываем витрину
        open("/");

        // 2. Берем название первого товара
        String productName = $$("#products-list .product-card").first().$("h4").getText();

        // 3. Нажимаем "В корзину" у первого товара
        $$("#products-list .product-card").first()
                .$("[data-action='add-to-cart']")
                .shouldBe(visible)
                .click();

        // 4. Открываем корзину
        $("#open-cart-btn").shouldBe(visible).click();

        // 5. Проверить, что товар отображается в корзине
        $$("#cart-items .cart-item")
                .findBy(text(productName))
                .shouldBe(visible);
    }
}
