package tests;

import asserts.AdminAssert;
import asserts.StoreAssert;
import config.TestConfig;
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
            given()
                    .auth().basic(TestConfig.getUsername(), TestConfig.getPassword())
                    .pathParam("id", productId)
                    .when()
                    .delete(TestConfig.getApiBaseUrl() + "/goods/{id}");
        }
    }
}
