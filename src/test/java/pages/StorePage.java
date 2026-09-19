package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;

public class StorePage {

    private final ElementsCollection productCards = $$("#products-list .product-card");
    private final ElementsCollection productNames = $$("#products-list h4");
    private final SelenideElement cartButton = $("#open-cart-btn");
    private final SelenideElement productsList = $("#products-list");

    public StorePage openPage() {
        open("/");
        return this;
    }

    public ElementsCollection getProductCards() {
        return productCards;
    }

    public ElementsCollection getProductNames() {
        return productNames;
    }

    public SelenideElement getCartButton() {
        return cartButton;
    }

    public SelenideElement getProductsList() {
        return productsList;
    }

    public SelenideElement getFirstProductCard() {
        return productCards.first();
    }

    public String getFirstProductName() {
        return productCards.first().$("h4").getText();
    }

    public String getProductPriceByIndex(int index) {
        return productCards.get(index).getAttribute("data-price");
    }

    public String getProductNameByIndex(int index) {
        return productCards.get(index).$("h4").getText();
    }

    // взаимодействие: click
    public StorePage addFirstProductToCart() {
        productCards.first().$("[data-action='add-to-cart']").click();
        return this;
    }

    public StorePage addProductToCartByName(String name) {
        productCards.findBy(com.codeborne.selenide.Condition.text(name))
                .$("[data-action='add-to-cart']")
                .click();
        return this;
    }

    public StorePage addProductToCartByIndex(int index) {
        productCards.get(index).$("[data-action='add-to-cart']").click();
        return this;
    }

    // взаимодействие: sendKeys / setValue для qty
    public StorePage setQuantityForFirstProduct(String qty) {
        productCards.first().$(".qty-input").setValue(qty);
        return this;
    }

    public StorePage increaseQuantityForFirstProduct() {
        productCards.first().$("[data-action='qty-change'][data-step='1']").click();
        return this;
    }

    public CartPage openCart() {
        cartButton.click();
        return new CartPage();
    }

    public StorePage dragFirstProductToCart() {
        actions()
                .dragAndDrop(productCards.first(), cartButton)
                .perform();
        return this;
    }
}
