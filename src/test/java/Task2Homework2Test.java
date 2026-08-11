import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.*;
import java.util.stream.Stream;

public class Task2Homework2Test {

    @Test
    void testIsEven() {
        Random random = new Random();
        int number = random.nextInt(1, 101);

        boolean actual = TestTasks.isEven(number);
        boolean expected = (number % 2 == 0);

        if (actual == expected) {
            System.out.println("TEST PASSED: isEven(" + number + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: isEven(" + number + ")");
        }
    }

    @RepeatedTest(5)
    void testCheckAccess() {
        Random random = new Random();
        int age = random.nextInt(0, 100);

        String actual = TestTasks.checkAccess(age);
        String expected = (age > 18) ? "Allowed" : "Denied";

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED: checkAccess(" + age + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: checkAccess(" + age + ")");
        }
    }

    @RepeatedTest(10)
    void testIsPositive() {
        Random random = new Random();
        int number = random.nextInt(-100, 100);

        boolean actual = TestTasks.isPositive(number);
        boolean expected = number >= 0;

        if (actual == expected) {
            System.out.println("TEST PASSED: isPositive(" + number + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: isPositive(" + number + ")");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0, E",
            "20, A",
            "21, D",
            "40, D",
            "50, C",
            "75, B",
            "90, A",
            "100, C",
            "-5, Error",
            "101, Error"
    })
    void testGetGrade(int score, String expected) {
        String actual = TestTasks.getGrade(score);

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED: getGrade(" + score + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: getGrade(" + score + ") expected " + expected + " but " + actual);
        }
    }

    @RepeatedTest(5)
    void testBlastOff() {
        Random random = new Random();
        int start = random.nextInt(1, 11);

        String actual = TestTasks.blastOff(start);
        String expected = "";
        for (int i = start; i >= 1; i--) {
            if (i == start) {
                expected += i;
            } else {
                expected += " " + i;
            }
        }
        expected += " Поехали!";

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED: blastOff(" + start + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: blastOff(" + start + ")");
        }
    }

    @RepeatedTest(5)
    void testSumToN() {
        Random random = new Random();
        int number = random.nextInt(1, 51);

        int actual = TestTasks.sumToN(number);
        int expected = 0;
        for (int i = 1; i <= number; i++) {
            expected = expected + i;
        }

        if (actual == expected) {
            System.out.println("TEST PASSED: sumToN(" + number + ") = " + actual);
        } else {
            System.out.println("TEST FAILED: sumToN(" + number + ")");
        }
    }

    @Test
    void testHasBug() {
        String[] withBug = {"Error", "Bug", "Info"};
        boolean actual1 = TestTasks.hasBug(withBug);

        if (actual1) {
            System.out.println("TEST PASSED: hasBug (with Bug) = true");
        } else {
            System.out.println("TEST FAILED: hasBug (with Bug)");
        }

        String[] withoutBug = {"Error", "Warning", "Info"};
        boolean actual2 = TestTasks.hasBug(withoutBug);

        if (!actual2) {
            System.out.println("TEST PASSED: hasBug (without Bug) = false");
        } else {
            System.out.println("TEST FAILED: hasBug (without Bug)");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "2, 5, '2 4'",
            "1, 6, '2 4 6'",
            "3, 3, ''",
            "4, 4, '4'",
            "10, 15, '10 12 14'"
    })
    void testGetEvenInRange(int start, int end, String expected) {
        String actual = TestTasks.getEvenInRange(start, end).trim();
        expected = expected.trim();

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED: getEvenInRange(" + start + ", " + end + ") = \"" + actual + "\"");
        } else {
            System.out.println("TEST FAILED: getEvenInRange(" + start + ", " + end + ")");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "3, 7, 2, 9, 5, 9",
            "-5, -2, -10, -1, 3, -1",
            "15, 15, 15, 15, 15, 15"
    })
    void testFindMax(int a, int b, int c, int d, int e, int expected) {
        int[] array = {a, b, c, d, e};
        int actual = TestTasks.findMax(array);

        if (actual == expected) {
            System.out.println("TEST PASSED: findMax = " + actual);
        } else {
            System.out.println("TEST FAILED: findMax expected " + actual + " but " + expected);
        }
    }


    @ParameterizedTest
    @CsvSource({
            "One, Two, Zero, Zero, Two, One",
            "A, B, C, C, B, A",
            "Hello, Dear, Friend, Hello, Hello, Hello"
    })
    void testReverse(String a, String b, String c, String exp1, String exp2, String exp3) {
        String[] input = {a, b, c};
        String[] actual = TestTasks.reverse(input);
        String[] expected = {exp1, exp2, exp3};

        if (Arrays.equals(actual, expected)) {
            System.out.println("TEST PASSED: reverse = " + Arrays.toString(actual));
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Test
    void testCalcAverage() {
        List<Integer> list = Arrays.asList(10, 20, 30);
        double actual = TestTasks.calcAverage(list);
        double expected = 20.0;

        if (Math.abs(actual - expected) < 0.0001) {
            System.out.println("TEST PASSED: calcAverage = " + actual);
        } else {
            System.out.println("TEST FAILED: calcAverage expected " + expected + " but " + actual);
        }
    }

    @Test
    void testRemoveSpecificName() {
        List<String> input = Arrays.asList("Анна", "Иван", "Мария", "Иван", "Пётр");
        List<String> actual = TestTasks.removeSpecificName(input, "Иван");
        List<String> expected = Arrays.asList("Анна", "Мария", "Пётр");

        if (actual.equals(expected)) {
            System.out.println("TEST PASSED: removeSpecificName = " + actual);
        } else {
            System.out.println("TEST FAILED");
        }
    }
}
