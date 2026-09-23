import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoodsApiTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @BeforeEach
    void clearGoods() {
        List<Integer> ids = getAllGoodsIds();
        if (ids != null) {
            for (Integer id : ids) {
                deleteGoodsById(id);
            }
        }
    }

    // ===================== API steps =====================

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

    @Step("API: удалить товар id={id}")
    private void deleteGoodsById(int id) {
        given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .delete("/goods/{id}")
                .then()
                .statusCode(200);
    }

    @Step("API: получить id всех товаров")
    private List<Integer> getAllGoodsIds() {
        Response response = getGoodsList(0, 100);
        checkStatusCode(response, 200);
        return response.jsonPath().getList("goods.id ");
    }

    // ===================== API checks =====================

    @Step("API-проверка: статус-код = {expectedStatus}")
    private void checkStatusCode(Response response, int expectedStatus) {
        response.then().statusCode(expectedStatus);
    }

    @Step("API-проверка: список goods пустой")
    private void checkGoodsListIsEmpty(Response response) {
        response.then().body("goods", empty());
    }

    @Step("API-проверка: список содержит товар '{name}'")
    private void checkGoodsListContainsName(Response response, String name) {
        response.then().body("goods.name", hasItem(name));
    }

    @Step("API-проверка (AssertJ): список содержит товар '{name}'")
    private void checkGoodsListContainsNameAssertJ(Response response, String name) {
        assertThat(response.jsonPath().getList("goods.name"))
                .as("Список должен содержать созданный товар")
                .contains(name);
    }

    // ===================== Tests =====================

    @Test
    @Order(1)
    void testGetGoodsListWithGivenWhenThen() {
        Response response = getGoodsList(0, 10);
        response.then().log().all();
        checkStatusCode(response, 200);
        checkGoodsListIsEmpty(response);
    }

    @Test
    @Order(2)
    void testGetGoodsListWithRequestSpecification() {
        RequestSpecification request = given()
                .filter(new AllureRestAssured())
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON);

        Response response = request
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .extract().response();

        checkStatusCode(response, 200);
        checkGoodsListIsEmpty(response);
    }

    @Test
    @Order(3)
    void testCreateGoodsAndCheckWithRestAssured() {
        Response createResponse = createGoods("Test Product RA", 99.99);
        createResponse.then().log().all();
        checkStatusCode(createResponse, 200);

        Response listResponse = getGoodsList(0, 50);
        listResponse.then().log().all();
        checkStatusCode(listResponse, 200);
        checkGoodsListContainsName(listResponse, "Test Product RA");
    }

    @Test
    @Order(4)
    void testCreateGoodsAndCheckWithAssertJ() {
        Response createResponse = createGoods("Test Product AssertJ", 149.50);
        createResponse.then().log().all();
        checkStatusCode(createResponse, 200);

        Response listResponse = getGoodsList(0, 50);
        listResponse.then().log().all();
        checkStatusCode(listResponse, 200);
        checkGoodsListContainsNameAssertJ(listResponse, "Test Product AssertJ");
    }
}