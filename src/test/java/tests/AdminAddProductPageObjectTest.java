package tests;

import asserts.AdminAssert;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class AdminAddProductPageObjectTest extends BaseUITest {

    @Test
    void addProductShouldShowToast() {
        var adminPage = new LoginPage()
                .openPage()
                .loginAs(TestConfig.getUsername(), TestConfig.getPassword());

        new AdminAssert(adminPage)
                .nameInputIsVisible()
                .priceInputIsVisible()
                .addButtonIsVisible();

        adminPage.createProduct(
                TestConfig.getStartProductName(),
                TestConfig.getStartProductPrice()
        );

        new AdminAssert(adminPage).productAddedToastIsVisible();
    }
}
