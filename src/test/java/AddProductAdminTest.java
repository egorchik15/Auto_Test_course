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

public class AddProductAdminTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void addedProductShouldBeVisibleOnStorefront() {
        String productName = "Selenium Product 11";
        String productPrice = "123";

        // 1. Открываем страницу логина
        driver.get("http://localhost:8080/login");

        // 2. Вводим логин и пароль
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("secret123");

        // 3. Нажимаем кнопку Sign in
        driver.findElement(By.cssSelector("button.primary")).click();

        // 4. Добавляем товар на сайт, вводим название и цену товара и нажимаем кнопку Создать
        driver.findElement(By.id("n-name")).sendKeys(productName);
        driver.findElement(By.id("n-price")).sendKeys(productPrice);
        driver.findElement(By.id("add-btn")).click();

        // 5. Возвращаемся на главную страницу сайта
        driver.findElement(By.linkText("Вернуться на сайт")).click();

        // 6. Проверяем, что добавленный товар есть на витрине
        List<WebElement> names = driver.findElements(By.cssSelector("#products-list h4"));

        boolean found = false;
        for (WebElement name : names) {
            if (name.getText().equals(productName)) {
                found = true;
                break;
            }
        }

        assertThat(found)
                .as("Товар '" + productName + "' должен отображаться на витрине")
                .isTrue();
    }
}
