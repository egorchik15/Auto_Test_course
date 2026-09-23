import io.qameta.allure.Step;
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
        openLoginPage();
        enterWrongCredentials("wrong_user", "wrong_password");
        clickSignIn();
        checkLoginErrorIsVisible();
        checkLoginErrorText("Неверные учетные данные пользователя");
    }

    // ===================== UI steps =====================

    @Step("UI: открыть страницу логина")
    private void openLoginPage() {
        driver.get("http://localhost:8080/login");
    }

    @Step("UI: ввести логин '{username}' и пароль")
    private void enterWrongCredentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Step("UI: нажать Sign in")
    private void clickSignIn() {
        driver.findElement(By.cssSelector("button.primary")).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: сообщение об ошибке отображается")
    private void checkLoginErrorIsVisible() {
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
        assertThat(errorMessage.isDisplayed())
                .as("Сообщение об ошибке должно отображаться")
                .isTrue();
    }

    @Step("UI-проверка: текст ошибки = '{expectedText}'")
    private void checkLoginErrorText(String expectedText) {
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert.alert-danger"));
        assertThat(errorMessage.getText())
                .as("Текст ошибки должен быть корректным")
                .isEqualTo(expectedText);
    }
}