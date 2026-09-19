package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenidePayThreeItemsTest extends BaseUITest {

    @Test
    void payThreeItemsUnder300ShouldShowNotification() {
        String productName = "Cheap Product 31";
        String productPrice = "50";

        // 1. Открыть админку
        open("/login");

        // 2. Авторизоваться
        $("#username").shouldBe(visible).setValue("admin");
        $("#password").shouldBe(visible).setValue("secret123");
        $("button.primary").shouldBe(visible).click();

        // 3. Создать недорогой товар
        $("#n-name").shouldBe(visible).setValue(productName);
        $("#n-price").shouldBe(visible).setValue(productPrice);
        $("#add-btn").shouldBe(visible).click();

        // 4. Перейти на витрину
        open("/");

        // 5. Найти созданный товар
        var card = $$("#products-list .product-card")
                .findBy(text(productName))
                .shouldBe(visible);

        // 6. Добавить товар в корзину 3 раза
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();
        card.$("[data-action='add-to-cart']").shouldBe(visible).click();

        // 7. Открыть корзину
        $("#open-cart-btn").shouldBe(visible).click();

        // 8. Нажать "Оформить заказ"
        $("#makeOrder").shouldBe(visible).click();

        // 9. Проверить тостер с уведомлением
        $(".toast")
                .shouldBe(visible)
                .shouldHave(text("Заказ принят в обработку!"));
    }
}