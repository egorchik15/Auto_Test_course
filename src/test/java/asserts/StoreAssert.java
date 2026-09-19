package asserts;

import pages.StorePage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class StoreAssert {

    private final StorePage page;

    public StoreAssert(StorePage page) {
        this.page = page;
    }

    public StoreAssert productsListIsVisible() {
        page.getProductsList().shouldBe(visible);
        return this;
    }

    public StoreAssert productCardsAreVisible() {
        page.getProductCards().shouldHave(sizeGreaterThan(0));
        page.getProductCards().first().shouldBe(visible);
        return this;
    }

    public StoreAssert productNamesAreVisible() {
        page.getProductNames().shouldHave(sizeGreaterThan(0));
        page.getProductNames().first().shouldBe(visible);
        return this;
    }

    public StoreAssert cartButtonIsVisible() {
        page.getCartButton().shouldBe(visible);
        return this;
    }

    public StoreAssert productIsVisible(String name) {
        page.getProductNames().findBy(exactText(name)).shouldBe(visible);
        return this;
    }

    public StoreAssert productHasNameAndPrice(String id, String name, String price) {
        var card = page.getProductCards().findBy(attribute("data-id", id));
        card.shouldBe(visible);
        card.$("h4").shouldHave(exactText(name));
        card.shouldHave(attribute("data-price", price));
        card.$("h4 + div").shouldHave(text(price));
        return this;
    }
}
