package tests;

import asserts.AdminAssert;
import asserts.StoreAssert;
import config.TestConfig;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static io.restassured.RestAssured.given;

public class AdminEditProductPageObjectTest extends BaseUITest {

    private String productId;
    private final String originalName = "Edit PO " + System.currentTimeMillis();
    private final String editedName = "Edited PO " + System.currentTimeMillis();

    @Test
    void editProductShouldApplyChanges() {
        var adminPage = new LoginPage()
                .openPage()
                .loginAs(TestConfig.getUsername(), TestConfig.getPassword());

        adminPage.createProduct(originalName, "77");
        new AdminAssert(adminPage).productAddedToastIsVisible();

        productId = adminPage.findProductIdByName(originalName);

        adminPage.editProduct(originalName, editedName, "15");
        new AdminAssert(adminPage).productUpdatedToastIsVisible(productId);

        var storePage = adminPage.openStore();
        new StoreAssert(storePage)
                .productHasNameAndPrice(productId, editedName, "15");
    }

    @AfterEach
    void cleanup() {
        if (productId != null) {
            deleteGoodsById(productId);
            productId = null;
        }
    }

    @Step("API: удалить товар id={id}")
    private void deleteGoodsById(String id) {
        given()
                .filter(new AllureRestAssured())
                .auth().basic(TestConfig.getUsername(), TestConfig.getPassword())
                .pathParam("id", id)
                .when()
                .delete(TestConfig.getApiBaseUrl() + "/goods/{id}")
                .then()
                .statusCode(200);
    }
}