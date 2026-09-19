package asserts;

import pages.AdminPage;
import static com.codeborne.selenide.Condition.*;

public class AdminAssert {

    private final AdminPage page;

    public AdminAssert(AdminPage page) {
        this.page = page;
    }

    public AdminAssert nameInputIsVisible() {
        page.getNameInput().shouldBe(visible);
        return this;
    }

    public AdminAssert priceInputIsVisible() {
        page.getPriceInput().shouldBe(visible);
        return this;
    }

    public AdminAssert addButtonIsVisible() {
        page.getAddButton().shouldBe(visible);
        return this;
    }

    public AdminAssert nameHasValue(String value) {
        page.getNameInput().shouldHave(value(value));
        return this;
    }

    public AdminAssert productAddedToastIsVisible() {
        page.getToast().shouldBe(visible).shouldHave(text("Товар успешно добавлен!"));
        return this;
    }

    public AdminAssert productUpdatedToastIsVisible(String productId) {
        page.getToast().shouldBe(visible).shouldHave(text("Товар #" + productId + " обновлен"));
        return this;
    }
}
