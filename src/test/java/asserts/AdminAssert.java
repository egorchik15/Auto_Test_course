package asserts;

import pages.AdminPage;
import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

public class AdminAssert {

    private final AdminPage page;

    public AdminAssert(AdminPage page) {
        this.page = page;
    }

    @Step("UI: Поле названия товара видно")
    public AdminAssert nameInputIsVisible() {
        page.getNameInput().shouldBe(visible);
        return this;
    }

    @Step("UI: Поле цены товара видно")
    public AdminAssert priceInputIsVisible() {
        page.getPriceInput().shouldBe(visible);
        return this;
    }

    @Step("UI: Кнопка «Создать» видна")
    public AdminAssert addButtonIsVisible() {
        page.getAddButton().shouldBe(visible);
        return this;
    }

    @Step("UI: Поле названия содержит '{value}'")
    public AdminAssert nameHasValue(String value) {
        page.getNameInput().shouldHave(value(value));
        return this;
    }

    @Step("UI: Тостер «Товар успешно добавлен!» виден")
    public AdminAssert productAddedToastIsVisible() {
        page.getToast().shouldBe(visible).shouldHave(text("Товар успешно добавлен!"));
        return this;
    }

    @Step("UI: Тостер «Товар #{productId} обновлен» виден")
    public AdminAssert productUpdatedToastIsVisible(String productId) {
        page.getToast().shouldBe(visible).shouldHave(text("Товар #" + productId + " обновлен"));
        return this;
    }
}
