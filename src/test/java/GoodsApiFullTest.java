import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoodsApiFullTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    // ========== POST /goods/add ==========

    @Test
    @Order(1)
    void addGoodsSuccess() {
        String body = """
                {
                  "name": "Milk",
                  "price": 79.99
                }
                """;

        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", notNullValue());
    }

    @Test
    @Order(2)
    void addGoodsDuplicateShouldReturn400() {
        String body = """
                {
                  "name": "Milk",
                  "price": 79.99
                }
                """;

        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/goods/add")
                .then()
                .log().all()
                .statusCode(400);
    }

    // ========== GET /goods/list ==========

    @Test
    @Order(3)
    void getGoodsListSuccess() {
        given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .body("goods", notNullValue());
    }

    // ========== GET /goods/{id} ==========

    @Test
    @Order(4)
    void getGoodsByIdSuccess() {
        // Сначала получаем список, чтобы взять существующий id
        Response listResponse = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int id = listResponse.jsonPath().getInt("goods[0].id");

        given()
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .get("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(5)
    void getGoodsByIdNotFound() {
        given()
                .auth().basic("admin", "secret123")
                .pathParam("id", 75)
                .when()
                .get("/goods/{id}")
                .then()
                .log().all()
                .statusCode(404);
    }

    // ========== PATCH /goods/{id} ==========

    @Test
    @Order(6)
    void patchGoodsSuccess() {
        Response listResponse = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int id = listResponse.jsonPath().getInt("goods[0].id");

        String body = """
                {
                  "name": "Milk Updated",
                  "price": 89.99
                }
                """;

        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .patch("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(7)
    void patchGoodsNotFound() {
        String body = """
                {
                  "name": "Not Exists",
                  "price": 10.0
                }
                """;

        given()
                .auth().basic("admin", "secret123")
                .contentType(ContentType.JSON)
                .pathParam("id", 84)
                .body(body)
                .when()
                .patch("/goods/{id}")
                .then()
                .log().all()
                .statusCode(404);
    }

    // ========== DELETE /goods/{id} ==========

    @Test
    @Order(8)
    void deleteGoodsSuccess() {
        Response listResponse = given()
                .auth().basic("admin", "secret123")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/goods/list")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        int id = listResponse.jsonPath().getInt("goods[0].id");

        given()
                .auth().basic("admin", "secret123")
                .pathParam("id", id)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Order(9)
    void deleteGoodsNotFound() {
        given()
                .auth().basic("admin", "secret123")
                .pathParam("id", 4)
                .when()
                .delete("/goods/{id}")
                .then()
                .log().all()
                .statusCode(404);
    }
}

