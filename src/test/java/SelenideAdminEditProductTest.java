import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class SelenideAdminEditProductTest extends BaseUITest {

    private String productId;

    @Test
    void editProductShouldApplyChanges() {
        String originalName = "Edit Product 34";
        String originalPrice = "77";
        String editedName = "Admin Notify Product update";
        String editedPrice = "15";

        // 1. Открыть логин
        open("/login");

        // 2. Авторизоваться
        $("#username").shouldBe(visible).setValue("admin");
        $("#password").shouldBe(visible).setValue("secret123");
        $("button.primary").shouldBe(visible).click();

        // 3. Создать товар
        $("#n-name").shouldBe(visible).setValue(originalName);
        $("#n-price").shouldBe(visible).setValue(originalPrice);
        $("#add-btn").shouldBe(visible).click();

        // 4. Найти поле названия созданного товара
        var nameInput = $$("input[id^='nm-']")
                .findBy(value(originalName))
                .shouldBe(visible);

        // 5. Достать id товара
        productId = nameInput.getAttribute("id").replace("nm-", "");

        // 6. Изменить название
        nameInput.setValue(editedName);

        // 7. Изменить цену
        $("#pr-" + productId).shouldBe(visible).setValue(editedPrice);

        // 8. Нажать "Сохранить"
        $("button[data-action='update'][data-id='" + productId + "']")
                .shouldBe(visible)
                .click();

        // 9. Проверить тостер
        $(".toast")
                .shouldBe(visible)
                .shouldHave(text("Товар #" + productId + " обновлен"));

        // 10. Вернуться на витрину
        open("/");

        // 11. Проверить, что карточка обновилась
        var card = $$("#products-list .product-card")
                .findBy(attribute("data-id", productId))
                .shouldBe(visible);

        card.$("h4").shouldHave(exactText(editedName));
        card.shouldHave(attribute("data-name", editedName));
        card.shouldHave(attribute("data-price", editedPrice));
        card.$("h4 + div").shouldHave(text(editedPrice));
    }

    @AfterEach
    void deleteCreatedProduct() {
        // Удаляем товар после теста, чтобы следующий запуск был чистым
        if (productId != null) {
            given()
                    .auth().basic("admin", "secret123")
                    .pathParam("id", productId)
                    .when()
                    .delete("http://localhost:8080/goods/{id}")
                    .then()
                    .statusCode(200);

            productId = null;
        }
    }
}