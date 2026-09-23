package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideAddProductAdminTest extends BaseUITest {

    @Test
    void addedProductShouldBeVisibleOnStorefront() {
        String productName = "Selenide Product 21";
        String productPrice = "150";

        openLoginPage();
        loginAs("admin", "secret123");
        checkAdminFormIsVisible();
        createProduct(productName, productPrice);
        openStore();
        checkProductsListIsNotEmpty();
        checkProductVisibleOnStorefront(productName);
    }

    // ===================== UI steps =====================

    @Step("UI: открыть страницу логина")
    private void openLoginPage() {
        open("/login");
    }

    @Step("UI: войти как '{username}'")
    private void loginAs(String username, String password) {
        $("#username").shouldBe(visible).setValue(username);
        $("#password").shouldBe(visible).setValue(password);
        $("button.primary").shouldBe(enabled).click();
    }

    @Step("UI: создать товар name='{name}', price='{price}'")
    private void createProduct(String name, String price) {
        $("#n-name").setValue(name);
        $("#n-price").setValue(price);
        $("#add-btn").shouldBe(visible).click();
    }

    @Step("UI: открыть витрину")
    private void openStore() {
        open("/");
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: форма создания товара в админке видна")
    private void checkAdminFormIsVisible() {
        $("#n-name").shouldBe(visible);
        $("#n-price").shouldBe(visible);
    }

    @Step("UI-проверка: список товаров на витрине не пустой")
    private void checkProductsListIsNotEmpty() {
        $$("#products-list h4").shouldHave(sizeGreaterThan(0));
    }

    @Step("UI-проверка: товар '{productName}' виден на витрине")
    private void checkProductVisibleOnStorefront(String productName) {
        $$("#products-list h4")
                .findBy(exactText(productName))
                .shouldBe(visible);
    }
}