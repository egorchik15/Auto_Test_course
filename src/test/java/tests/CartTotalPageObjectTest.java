package tests;

import asserts.CartAssert;
import asserts.StoreAssert;
import org.junit.jupiter.api.Test;
import pages.StorePage;

public class CartTotalPageObjectTest extends BaseUITest {

    @Test
    void cartTotalShouldBeCorrect() {
        StorePage storePage = new StorePage().openPage();
        new StoreAssert(storePage).productCardsAreVisible();

        double p1 = Double.parseDouble(storePage.getProductPriceByIndex(0));
        double p2 = Double.parseDouble(storePage.getProductPriceByIndex(1));
        int expected = (int) (p1 + p2);

        storePage.addProductToCartByIndex(0);
        storePage.addProductToCartByIndex(1);

        var cartPage = storePage.openCart();
        new CartAssert(cartPage)
                .hasItemsCountAtLeast(2)
                .totalIs(String.valueOf(expected));
    }
}
