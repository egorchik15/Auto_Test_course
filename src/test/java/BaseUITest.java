import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseUITest {

    @BeforeAll
    static void setUpAll() {
        Configuration.baseUrl = "http://localhost:8080";
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;
    }
}
