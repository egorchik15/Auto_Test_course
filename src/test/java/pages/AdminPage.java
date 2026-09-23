package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import io.qameta.allure.Step;

public class AdminPage {

    private final SelenideElement nameInput = $("#n-name");
    private final SelenideElement priceInput = $("#n-price");
    private final SelenideElement addButton = $("#add-btn");
    private final SelenideElement toast = $(".toast");

    public SelenideElement getNameInput() {
        return nameInput;
    }

    public SelenideElement getPriceInput() {
        return priceInput;
    }

    public SelenideElement getAddButton() {
        return addButton;
    }

    public SelenideElement getToast() {
        return toast;
    }

    @Step("Ввести название товара '{name}', ввести цену товара '{price}' и нажать кнопку «Создать»")
    public AdminPage createProduct(String name, String price) {
        nameInput.setValue(name);
        priceInput.setValue(price);
        addButton.click();
        return this;
    }

    @Step("Отредактировать товар '{originalName}', присвоить новые значения: name='{newName}', price='{newPrice}'")
    public AdminPage editProduct(String originalName, String newName, String newPrice) {
        SelenideElement nameField = $$("input[id^='nm-']")
                .findBy(com.codeborne.selenide.Condition.value(originalName));
        String id = nameField.getAttribute("id").replace("nm-", "");

        nameField.setValue(newName);
        $("#pr-" + id).setValue(newPrice);
        $("button[data-action='update'][data-id='" + id + "']").click();
        return this;
    }

    @Step("Найти id товара по имени '{name}'")
    public String findProductIdByName(String name) {
        return $$("input[id^='nm-']")
                .findBy(com.codeborne.selenide.Condition.value(name))
                .getAttribute("id")
                .replace("nm-", "");
    }

    @Step("Перейти на витрину")
    public StorePage openStore() {
        open("/");
        return new StorePage();
    }
}
