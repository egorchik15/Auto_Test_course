package asserts;

import pages.CartPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import io.qameta.allure.Step;

public class CartAssert {

    private final CartPage page;

    public CartAssert(CartPage page) {
        this.page = page;
    }

    @Step("UI: Корзина доступна")
    public CartAssert cartIsVisible() {
        page.getCartItems().first().shouldBe(visible);
        return this;
    }

    @Step("UI: В корзине есть товар '{name}'")
    public CartAssert hasProduct(String name) {
        page.getCartItems().findBy(text(name)).shouldBe(visible);
        return this;
    }

    @Step("UI: В корзине минимум '{count}' позиций")
    public CartAssert hasItemsCountAtLeast(int count) {
        page.getCartItems().shouldHave(sizeGreaterThanOrEqual(count));
        return this;
    }

    @Step("UI: Сумма корзины = {expected}")
    public CartAssert totalIs(String expected) {
        page.getTotalPrice().shouldBe(visible).shouldHave(exactText(expected));
        return this;
    }

    @Step("UI: Тостер содержит текст '{text}'")
    public CartAssert orderToastIsVisible(String text) {
        page.getToast().shouldBe(visible).shouldHave(text(text));
        return this;
    }

    @Step("UI: Кнопка 'оформить заказ' видна")
    public CartAssert makeOrderButtonIsVisible() {
        page.getMakeOrderButton().shouldBe(visible);
        return this;
    }
}
