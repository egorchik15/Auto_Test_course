import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideOrderAlertTest extends BaseUITest {

    @Test
    void orderOver300ShouldShowJsAlert() {
        // 1. Открываем витрину
        open("/");

        // 2. Находим товар с ценой 150 (например Selenide Product 21)
        var card = $$("#products-list .product-card").findBy(text("150"));
        if (!card.exists()) {
            // запасной вариант: берём карточку с data-price="150"
            card = $$("#products-list .product-card").findBy(attribute("data-price", "150"));
        }

        // 3. Добавляем этот товар в корзину 3 раза
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();

        // 4. Открываем корзину
        $("#open-cart-btn").shouldBe(visible).click();

        // 5. Нажимаем "Оформить заказ"
        $("#makeOrder").shouldBe(visible).click();

        // 6. Проверяем JS Alert
        String alertText = confirm();

        assertThat(alertText)
                .as("Должен появиться alert о превышении лимита 300 ₽")
                .contains("Денег не хватает")
                .contains("300");
    }
}