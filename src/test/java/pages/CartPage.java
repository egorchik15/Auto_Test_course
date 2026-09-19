package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

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

    public CartPage removeProduct(String name) {
        cartItems.findBy(com.codeborne.selenide.Condition.text(name))
                .$("[data-action='remove']")
                .click();
        return this;
    }

    public CartPage makeOrder() {
        makeOrderButton.click();
        return this;
    }
}
