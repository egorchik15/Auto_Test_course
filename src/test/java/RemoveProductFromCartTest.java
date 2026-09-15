import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;

public class RemoveProductFromCartTest extends BaseUITest {

    @Test
    void shouldRemoveProductFromCart() {
        // 1. Открываем витрину
        open("/");

        // 2. Берем карточку товара и название
        var productCard = $$("#products-list .product-card").first().shouldBe(visible);
        String productName = productCard.$("h4").getText();

        // 3. Иконка корзины должна быть видимой
        var cartButton = $("#open-cart-btn").shouldBe(visible);

        // 4. Drag-and-Drop
        actions()
                .clickAndHold(productCard)
                .moveToElement(cartButton)
                .pause(500)
                .release()
                .perform();

        // 5. Открываем корзину
        cartButton.click();

        // 6. Проверяем, что товар есть в корзине
        var cartItem = $$("#cart-items .cart-item")
                .findBy(text(productName))
                .shouldBe(visible);

        // 7. Нажимаем крестик удаления
        cartItem.$("[data-action='remove']")
                .shouldBe(visible)
                .click();

        // 8. Проверить, что товара больше нет в корзине
        $$("#cart-items .cart-item")
                .filterBy(text(productName))
                .shouldHave(size(0));
    }
}
