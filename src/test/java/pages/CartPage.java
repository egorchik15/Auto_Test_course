package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.Step;

public class CartPage {

    private final ElementsCollection cartItems = $$("#cart-items .cart-item");
    private final SelenideElement totalPrice = $("#total-price");
    private final SelenideElement makeOrderButton = $("#makeOrder");
    private final SelenideElement toast = $(".toast");

    public ElementsCollection getCartItems() {
        return cartItems;
    }

    public SelenideElement getTotalPrice() {
        return totalPrice;
    }

    public SelenideElement getMakeOrderButton() {
        return makeOrderButton;
    }

    public SelenideElement getToast() {
        return toast;
    }

    @Step("Удалить товар '{name}' из корзины")
    public CartPage removeProduct(String name) {
        cartItems.findBy(com.codeborne.selenide.Condition.text(name))
                .$("[data-action='remove']")
                .click();
        return this;
    }

    @Step("Нажать кнопку «Оформить заказ»")
    public CartPage makeOrder() {
        makeOrderButton.click();
        return this;
    }
}
