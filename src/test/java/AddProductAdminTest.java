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

        openLoginPage();
        loginAs("admin", "secret123");
        createProduct(productName, productPrice);
        returnToStorefront();
        checkProductVisibleOnStorefront(productName);
    }

    // ===================== UI steps =====================

    @Step("UI: открыть страницу логина")
    private void openLoginPage() {
        driver.get("http://localhost:8080/login");
    }

    @Step("UI: войти как '{username}'")
    private void loginAs(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.primary")).click();
    }

    @Step("UI: создать товар name='{name}', price='{price}'")
    private void createProduct(String name, String price) {
        driver.findElement(By.id("n-name")).sendKeys(name);
        driver.findElement(By.id("n-price")).sendKeys(price);
        driver.findElement(By.id("add-btn")).click();
    }

    @Step("UI: вернуться на витрину")
    private void returnToStorefront() {
        driver.findElement(By.linkText("Вернуться на сайт")).click();
    }

    // ===================== UI checks =====================

    @Step("UI-проверка: товар '{productName}' отображается на витрине")
    private void checkProductVisibleOnStorefront(String productName) {
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