package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class SelenideApiDataTest extends BaseUITest {

    private Integer createdId;
    private final String productName = "API Product 35";

    @Test
    void productCreatedApiShouldBeVisibleInUI() {
        // 1. Создать товар через API
        Response response = createGoods(productName, 33.5);
        checkStatusCode(response, 200);

        System.out.println("API RESPONSE: " + response.asString());

        // 2. Сохранить id
        createdId = extractGoodsId(response);

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
        if (createdId != null) {
            deleteGoods(createdId);
            createdId = null;
        }
    }

    // ===================== API steps =====================

    @Step("API: создать товар name={name}, price={price}")
    private Response createGoods(String name, double price) {
        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "%s",
                          "price": %s
                        }
                        """.formatted(name, price))
                .when()
                .post("http://localhost:8080/goods/add")
                .then()
                .extract()
                .response();
    }

    @Step("API: удалить товар id={id}")
    private void deleteGoods(int id) {
        given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .delete("http://localhost:8080/goods/{id}")
                .then()
                .statusCode(200);
    }

    @Step("API: извлечь id товара из ответа")
    private Integer extractGoodsId(Response response) {
        return response.jsonPath().getInt("data.id");
    }

    @Step("API-проверка: статус-код = {expectedStatus}")
    private void checkStatusCode(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }
}