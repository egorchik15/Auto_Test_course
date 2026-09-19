package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

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

    public AdminPage createProduct(String name, String price) {
        nameInput.setValue(name);
        priceInput.setValue(price);
        addButton.click();
        return this;
    }

    public AdminPage editProduct(String originalName, String newName, String newPrice) {
        SelenideElement nameField = $$("input[id^='nm-']")
                .findBy(com.codeborne.selenide.Condition.value(originalName));
        String id = nameField.getAttribute("id").replace("nm-", "");

        nameField.setValue(newName);
        $("#pr-" + id).setValue(newPrice);
        $("button[data-action='update'][data-id='" + id + "']").click();
        return this;
    }

    public String findProductIdByName(String name) {
        return $$("input[id^='nm-']")
                .findBy(com.codeborne.selenide.Condition.value(name))
                .getAttribute("id")
                .replace("nm-", "");
    }

    public StorePage openStore() {
        open("/");
        return new StorePage();
    }
}
