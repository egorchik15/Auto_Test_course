import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Task1Homework2Test {

    @BeforeEach
    void beforeEach() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Test method end");
        System.out.println("========================");
    }

    @Test
    void testIsEvenRandom() {
        Random random = new Random();
        int number = random.nextInt(1, 101);
        boolean result = TestTasks.isEven(number);
        System.out.println("isEven(" + number + ") = " + result);
    }

    @RepeatedTest(20)
    void testCheckAccessRandom() {
        Random random = new Random();
        int age = random.nextInt(0, 100);
        String result = TestTasks.checkAccess(age);
        System.out.println("checkAccess(" + age + ") = " + result);
    }

    @ParameterizedTest
    @MethodSource("randomScores")
    void testGetGradeRandom(int score) {
        String result = TestTasks.getGrade(score);
        System.out.println("getGrade(" + score + ") = " + result);
    }

    static Stream<Integer> randomScores() {
        Random random = new Random();
        return IntStream.generate(() -> random.nextInt(0, 101))
                .limit(10)
                .boxed();
    }
}