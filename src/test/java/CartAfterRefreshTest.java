import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class CartAfterRefreshTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        openStore();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void cartShouldKeepProductsAfterRefresh() {
        String productName = getFirstProductName();
        addFirstProductToCart();
        openCart();
        checkProductInCart(productName, "До обновления страницы");

        refreshPage();
        openCart();
        checkProductInCart(productName, "После обновления страницы");
    }

    // ===================== UI steps =====================

    @Step("UI: открыть витрину")
    private void openStore() {
        driver.get("http://localhost:8080/");
    }

    @Step("UI: получить название первого товара")
    private String getFirstProductName() {
        WebElement firstCard = driver.findElement(By.cssSelector("#products-list .product-card"));
        return firstCard.findElement(By.cssSelector("h4")).getText();
    }

    @Step("UI: добавить первый товар в корзину")
    private void addFirstProductToCart() {
        WebElement firstCard = driver.findElement(By.cssSelector("#products-list .product-card"));
        firstCard.findElement(By.cssSelector("button[data-action='add-to-cart']")).click();
    }

    @Step("UI: открыть корзину")
    private void openCart() {
        driver.findElement(By.id("open-cart-btn")).click();
    }

    @Step("UI: обновить страницу")
    private void refreshPage() {
        driver.navigate().refresh();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: товар '{productName}' есть в корзине ({stage})")
    private void checkProductInCart(String productName, String stage) {
        List<WebElement> cartItems = driver.findElements(By.cssSelector("#cart-items .cart-item"));
        boolean found = false;
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                found = true;
                break;
            }
        }

        assertThat(found)
                .as(stage + " товар '" + productName + "' должен быть в корзине")
                .isTrue();
    }
}