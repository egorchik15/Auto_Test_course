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
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void cartShouldKeepProductsAfterRefresh() {
        // 1. Берём первый товар на витрине
        WebElement firstCard = driver.findElement(By.cssSelector("#products-list .product-card"));
        String productName = firstCard.findElement(By.cssSelector("h4")).getText();

        // 2. Добавляем товар в корзину
        firstCard.findElement(By.cssSelector("button[data-action='add-to-cart']")).click();

        // 3. Открываем корзину
        driver.findElement(By.id("open-cart-btn")).click();

        // 4. Проверяем, что товар есть до refresh
        List<WebElement> cartBefore = driver.findElements(By.cssSelector("#cart-items .cart-item"));
        boolean foundBefore = false;
        for (WebElement item : cartBefore) {
            if (item.getText().contains(productName)) {
                foundBefore = true;
                break;
            }
        }

        assertThat(foundBefore)
                .as("До обновления страницы товар '" + productName + "' должен быть в корзине")
                .isTrue();

        // 5. Обновляем страницу
        driver.navigate().refresh();

        // 6. Снова открываем корзину
        driver.findElement(By.id("open-cart-btn")).click();

        // 7. Проверяем, что товар остался после refresh
        List<WebElement> cartAfter = driver.findElements(By.cssSelector("#cart-items .cart-item"));
        boolean foundAfter = false;
        for (WebElement item : cartAfter) {
            if (item.getText().contains(productName)) {
                foundAfter = true;
                break;
            }
        }

        assertThat(foundAfter)
                .as("После обновления страницы товар '" + productName + "' должен сохраниться в корзине")
                .isTrue();
    }
}
