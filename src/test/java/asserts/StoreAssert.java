package asserts;

import pages.StorePage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import io.qameta.allure.Step;

public class StoreAssert {

    private final StorePage page;

    public StoreAssert(StorePage page) {
        this.page = page;
    }

    @Step("UI: Список товаров виден")
    public StoreAssert productsListIsVisible() {
        page.getProductsList().shouldBe(visible);
        return this;
    }

    @Step("UI: Карточки товаров отображаются")
    public StoreAssert productCardsAreVisible() {
        page.getProductCards().shouldHave(sizeGreaterThan(0));
        page.getProductCards().first().shouldBe(visible);
        return this;
    }

    @Step("UI: Названия товаров отображаются")
    public StoreAssert productNamesAreVisible() {
        page.getProductNames().shouldHave(sizeGreaterThan(0));
        page.getProductNames().first().shouldBe(visible);
        return this;
    }

    @Step("UI: Кнопка корзины видна")
    public StoreAssert cartButtonIsVisible() {
        page.getCartButton().shouldBe(visible);
        return this;
    }

    @Step("UI: Товар '{name}' виден на витрине")
    public StoreAssert productIsVisible(String name) {
        page.getProductNames().findBy(exactText(name)).shouldBe(visible);
        return this;
    }

    @Step("UI: Товар id={id} имеет name='{name}' и price='{price}'")
    public StoreAssert productHasNameAndPrice(String id, String name, String price) {
        var card = page.getProductCards().findBy(attribute("data-id", id));
        card.shouldBe(visible);
        card.$("h4").shouldHave(exactText(name));
        card.shouldHave(attribute("data-price", price));
        card.$("h4 + div").shouldHave(text(price));
        return this;
    }
}
