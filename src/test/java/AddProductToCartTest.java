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

public class AddProductToCartTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void productShouldAppearInCart() {
        // 1. Берём первую карточку товара на витрине
        WebElement firstCard = driver.findElement(By.cssSelector("#products-list .product-card"));
        String productName = firstCard.findElement(By.cssSelector("h4")).getText();

        // 2. Нажимаем "В корзину" у этого товара
        firstCard.findElement(By.cssSelector("button[data-action='add-to-cart']")).click();

        // 3. Открываем корзину
        driver.findElement(By.id("open-cart-btn")).click();

        // 4. Проверяем, что товар отображается в корзине
        List<WebElement> cartItems = driver.findElements(By.cssSelector("#cart-items .cart-item"));

        boolean found = false;
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                found = true;
                break;
            }
        }

        assertThat(found)
                .as("Товар '" + productName + "' должен отображаться в корзине")
                .isTrue();
    }
}
