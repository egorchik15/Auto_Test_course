import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class AssertionHomework3Test {

    @Test
    @Tag("homework3")
    void testIsEvenWithAssert() {
        boolean result = TestTasks.isEven(4);

        assertThat(result)
                .as("Проверка, что 4 — чётное число")
                .isTrue();
    }

    @Test
    @Tag("homework3")
    void testCheckAccessWithAssert() {
        String result = TestTasks.checkAccess(55);

        assertThat(result)
                .as("Проверка доступа для возраста 55")
                .isEqualTo("Allowed");
    }

    @Test
    @Tag("homework3")
    void testRemoveSpecificNameWithAssert() {
        List<String> input = Arrays.asList("Анна", "Иван", "Мария", "Иван", "Пётр");
        List<String> result = TestTasks.removeSpecificName(input, "Иван");

        assertThat(result)
                .as("После удаления 'Иван' список должен содержать 3 элемента")
                .hasSize(3)
                .containsExactly("Анна", "Мария", "Пётр");
    }

    @Test
    @Tag("homework3")
    void testFailingAssert() {
        String result = TestTasks.getGrade(95);

        assertThat(result)
                .as("Намеренно падающая проверка для getGrade(95)")
                .isEqualTo("B");
    }
}
