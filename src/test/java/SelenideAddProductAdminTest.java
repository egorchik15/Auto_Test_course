import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAddProductAdminTest extends BaseUITest {

    @Test
    void addedProductShouldBeVisibleOnStorefront() {
        String productName = "Selenide Product 21";
        String productPrice = "150";

        // 1. Логин
        open("/login");
        $("#username").shouldBe(visible).setValue("admin");
        $("#password").shouldBe(visible).setValue("secret123");
        $("button.primary").shouldBe(enabled).click();

        // 2. Убедились, что мы в админке (поле создания видно)
        $("#n-name").shouldBe(visible);
        $("#n-price").shouldBe(visible);

        // 3. Создаём товар
        $("#n-name").setValue(productName);
        $("#n-price").setValue(productPrice);
        $("#add-btn").shouldBe(visible).click();

        // 4. Возвращаемся на витрину
        open("/");

        // 5. Список товаров вообще есть
        $$("#products-list h4").shouldHave(sizeGreaterThan(0));

        // 6. Ищем товар (exactText)
        $$("#products-list h4")
                .findBy(exactText(productName))
                .shouldBe(visible);
    }
}

