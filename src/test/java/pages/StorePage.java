package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;
import io.qameta.allure.Step;

public class StorePage {

    private final ElementsCollection productCards = $$("#products-list .product-card");
    private final ElementsCollection productNames = $$("#products-list h4");
    private final SelenideElement cartButton = $("#open-cart-btn");
    private final SelenideElement productsList = $("#products-list");

    @Step("Открыть витрину")
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

    @Step("Получить название первого товара")
    public String getFirstProductName() {
        return productCards.first().$("h4").getText();
    }

    @Step("Получить цену товара с индексом {index}")
    public String getProductPriceByIndex(int index) {
        return productCards.get(index).getAttribute("data-price");
    }

    @Step("Получить название товара с индексом {index}")
    public String getProductNameByIndex(int index) {
        return productCards.get(index).$("h4").getText();
    }

    @Step("Добавить первый товар в корзину")
    // взаимодействие: click
    public StorePage addFirstProductToCart() {
        productCards.first().$("[data-action='add-to-cart']").click();
        return this;
    }

    @Step("Добавить товар '{name}' в корзину")
    public StorePage addProductToCartByName(String name) {
        productCards.findBy(com.codeborne.selenide.Condition.text(name))
                .$("[data-action='add-to-cart']")
                .click();
        return this;
    }

    @Step("Добавить товар с индексом {index} в корзину")
    public StorePage addProductToCartByIndex(int index) {
        productCards.get(index).$("[data-action='add-to-cart']").click();
        return this;
    }

    @Step("Установить количество '{qty}' для первого товара")
    // взаимодействие: sendKeys / setValue для qty
    public StorePage setQuantityForFirstProduct(String qty) {
        productCards.first().$(".qty-input").setValue(qty);
        return this;
    }

    @Step("Увеличить количество первого товара")
    public StorePage increaseQuantityForFirstProduct() {
        productCards.first().$("[data-action='qty-change'][data-step='1']").click();
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        cartButton.click();
        return new CartPage();
    }

    @Step("Перетащить первый товар в корзину (drag-and-drop)")
    public StorePage dragFirstProductToCart() {
        actions()
                .dragAndDrop(productCards.first(), cartButton)
                .perform();
        return this;
    }
}
