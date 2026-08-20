import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminLoginNegativeTest {

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
    void loginWithWrongCredentialsShouldShowError() {
        // 1. Открываем страницу логина
        driver.get("http://localhost:8080/login");

        // 2. Вводим неверный логин и пароль
        driver.findElement(By.id("username")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("wrong_password");

        // 3. Нажимаем Sign in
        driver.findElement(By.cssSelector("button.primary")).click();

        // 4. Проверяем сообщение об ошибке
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));

        assertThat(errorMessage.isDisplayed())
                .as("Сообщение об ошибке должно отображаться")
                .isTrue();

        assertThat(errorMessage.getText())
                .as("Текст ошибки должен быть корректным")
                .isEqualTo("Неверные учетные данные пользователя");
    }
}