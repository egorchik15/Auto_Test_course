import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoodsApiTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @BeforeEach
    void clearGoods() {
        // 1. Получаем текущий список
        Response response = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 100)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> ids = response.jsonPath().getList("goods.id");

        // 2. Если список не пустой — удаляем каждый товар
        if (ids != null) {
            for (Integer id : ids) {
                given()
                        .auth().basic("admin", "secret123")
                        .pathParam("id", id)
                        .when()
                        .delete("/goods/{id}")
                        .then()
                        .statusCode(200);
            }
        }
    }

    @Test
    @Order(1)
    void testGetGoodsListWithGivenWhenThen() {
        given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", empty());   // тело — пустой список
    }

    @Test
    @Order(2)
    void testGetGoodsListWithRequestSpecification() {
        RequestSpecification request = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .contentType(ContentType.JSON);

        request
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", empty());
    }

    @Test
    @Order(3)
    void testCreateGoodsAndCheckWithRestAssured() {
        String body = """
            {
              "name": "Test Product RA",
              "price": 99.99
            }
            """;

        // 1. Создаём товар
        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);

        // 2. Проверяем, что товар есть в списке
        given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 50)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods.name", hasItem("Test Product RA"));
    }

    @Test
    @Order(4)
    void testCreateGoodsAndCheckWithAssertJ() {
        String body = """
            {
              "name": "Test Product AssertJ",
              "price": 149.50
            }
            """;

        // 1. Создаём товар
        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200);

        // 2. Получаем список
        Response response = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 50)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        // 3. Проверяем через AssertJ
        assertThat(response.jsonPath().getList("goods.name"))
                .as("Список должен содержать созданный товар")
                .contains("Test Product AssertJ");
    }
}
