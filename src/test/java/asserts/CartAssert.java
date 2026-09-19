package asserts;

import pages.CartPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class CartAssert {

    private final CartPage page;

    public CartAssert(CartPage page) {
        this.page = page;
    }

    public CartAssert cartIsVisible() {
        page.getCartItems().first().shouldBe(visible);
        return this;
    }

    public CartAssert hasProduct(String name) {
        page.getCartItems().findBy(text(name)).shouldBe(visible);
        return this;
    }

    public CartAssert hasItemsCountAtLeast(int count) {
        page.getCartItems().shouldHave(sizeGreaterThanOrEqual(count));
        return this;
    }

    public CartAssert totalIs(String expected) {
        page.getTotalPrice().shouldBe(visible).shouldHave(exactText(expected));
        return this;
    }

    public CartAssert orderToastIsVisible(String text) {
        page.getToast().shouldBe(visible).shouldHave(text(text));
        return this;
    }

    public CartAssert makeOrderButtonIsVisible() {
        page.getMakeOrderButton().shouldBe(visible);
        return this;
    }
}
