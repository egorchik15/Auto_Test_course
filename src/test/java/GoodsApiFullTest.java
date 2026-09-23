import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoodsApiFullTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    // ===================== API steps =====================

    @Step("API: создать товар name={name}, price={price}")
    private Response createGoods(String name, double price) {
        String body = """
                {
                  "name": "%s",
                  "price": %s
                }
                """.formatted(name, price);

        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/goods/add")
                .then()
                .extract().response();
    }

    @Step("API: получить список товаров")
    private Response getGoodsList(int page, int size) {
        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/goods/list")
                .then()
                .extract().response();
    }

    @Step("API: получить товар по id={id}")
    private Response getGoodsById(int id) {
        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .get("/goods/{id}")
                .then()
                .extract().response();
    }

    @Step("API: обновить товар id={id}, name={name}, price={price}")
    private Response patchGoods(int id, String name, double price) {
        String body = """
                {
                  "name": "%s",
                  "price": %s
                }
                """.formatted(name, price);

        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .patch("/goods/{id}")
                .then()
                .extract().response();
    }

    @Step("API: удалить товар id={id}")
    private Response deleteGoods(int id) {
        return given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .delete("/goods/{id}")
                .then()
                .extract().response();
    }

    @Step("API: взять id первого товара из списка")
    private int getFirstGoodsId() {
        Response listResponse = getGoodsList(0, 10);
        checkStatusCode(listResponse, 200);
        return listResponse.jsonPath().getInt("goods[0].id");
    }

    // ===================== API checks =====================

    @Step("API-проверка: статус-код = {expectedStatus}")
    private void checkStatusCode(Response response, int expectedStatus) {
        response.then().log().all().statusCode(expectedStatus);
    }

    @Step("API-проверка: поле message не null")
    private void checkMessageNotNull(Response response) {
        response.then().body("message", notNullValue());
    }

    @Step("API-проверка: поле goods не null")
    private void checkGoodsNotNull(Response response) {
        response.then().body("goods", notNullValue());
    }

    // ===================== Tests =====================

    @Test
    @Order(1)
    void addGoodsSuccess() {
        Response response = createGoods("Milk", 79.99);
        checkStatusCode(response, 200);
        checkMessageNotNull(response);
    }

    @Test
    @Order(2)
    void addGoodsDuplicateShouldReturn400() {
        Response response = createGoods("Milk", 79.99);
        checkStatusCode(response, 400);
    }

    @Test
    @Order(3)
    void getGoodsListSuccess() {
        Response response = getGoodsList(0, 10);
        checkStatusCode(response, 200);
        checkGoodsNotNull(response);
    }

    @Test
    @Order(4)
    void getGoodsByIdSuccess() {
        int id = getFirstGoodsId();
        Response response = getGoodsById(id);
        checkStatusCode(response, 200);
    }

    @Test
    @Order(5)
    void getGoodsByIdNotFound() {
        Response response = getGoodsById(75);
        checkStatusCode(response, 404);
    }

    @Test
    @Order(6)
    void patchGoodsSuccess() {
        int id = getFirstGoodsId();
        Response response = patchGoods(id, "Milk Updated", 89.99);
        checkStatusCode(response, 200);
    }

    @Test
    @Order(7)
    void patchGoodsNotFound() {
        Response response = patchGoods(84, "Not Exists", 10.0);
        checkStatusCode(response, 404);
    }

    @Test
    @Order(8)
    void deleteGoodsSuccess() {
        int id = getFirstGoodsId();
        Response response = deleteGoods(id);
        checkStatusCode(response, 200);
    }

    @Test
    @Order(9)
    void deleteGoodsNotFound() {
        Response response = deleteGoods(4);
        checkStatusCode(response, 404);
    }
}