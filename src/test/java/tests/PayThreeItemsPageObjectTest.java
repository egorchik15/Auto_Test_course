package tests;

import asserts.AdminAssert;
import asserts.CartAssert;
import asserts.StoreAssert;
import config.TestConfig;
import org.junit.jupiter.api.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.StorePage;

public class PayThreeItemsPageObjectTest extends BaseUITest {

    @Test
    void payThreeItemsUnder300() {
        String name = "Cheap PO Product";
        String price = "50";

        // админка: создать недорогой товар
        AdminPage adminPage = new LoginPage()
                .openPage()
                .loginAs(TestConfig.getUsername(), TestConfig.getPassword());

        new AdminAssert(adminPage)
                .nameInputIsVisible()
                .priceInputIsVisible()
                .addButtonIsVisible();

        adminPage.createProduct(name, price);
        new AdminAssert(adminPage).productAddedToastIsVisible();

        // витрина
        StorePage storePage = adminPage.openStore();
        new StoreAssert(storePage).productIsVisible(name);

        storePage.addProductToCartByName(name);
        storePage.addProductToCartByName(name);
        storePage.addProductToCartByName(name);

        var cartPage = storePage.openCart();
        new CartAssert(cartPage)
                .hasProduct(name)
                .makeOrderButtonIsVisible();

        cartPage.makeOrder();
        new CartAssert(cartPage)
                .orderToastIsVisible("Заказ принят в обработку!");
    }
}
