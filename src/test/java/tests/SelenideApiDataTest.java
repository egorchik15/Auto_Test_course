package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class SelenideApiDataTest extends BaseUITest {

    private Integer createdId;
    private final String productName = "API Product 35";

    @Test
    void productCreatedApiShouldBeVisibleInUI() {
        // 1. Создать товар через API
        var response = given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "%s",
                          "price": 33.5
                        }
                        """.formatted(productName))
                .when()
                .post("http://localhost:8080/goods/add")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("API RESPONSE: " + response.asString());

        // 2. Сохранить id
        createdId = response.jsonPath().getInt("data.id");

        // 3. Открыть витрину
        open("/");

        // 4. Список товаров загрузился
        $$("#products-list h4").shouldHave(sizeGreaterThan(0));

        // 5. Товар из API виден в UI
        $$("#products-list h4")
                .findBy(exactText(productName))
                .shouldBe(visible);
    }

    @AfterEach
    void cleanup() {
        //  очистка
        if (createdId != null) {
            given()
                    .auth().basic("admin", "secret123")
                    .pathParam("id", createdId)
                    .when()
                    .delete("http://localhost:8080/goods/{id}");
            createdId = null;
        }
    }
}
