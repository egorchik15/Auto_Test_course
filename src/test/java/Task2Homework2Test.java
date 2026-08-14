import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;


public class Task2Homework2Test {

//    @Test
    @RepeatedTest(10)
    @Tag("homework3")
    void testIsEven() {
        Random random = new Random();
        int number = random.nextInt(1, 101);

        boolean actual = TestTasks.isEven(number);
        boolean expected = (number % 2 == 0);

        assertThat(actual)
                .as("isEven(" + number + ")")
                .isEqualTo(expected);
    }

//        if (actual == expected) {
//            System.out.println("TEST PASSED: isEven(" + number + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: isEven(" + number + ")");
//        }

    @RepeatedTest(10)
    @Tag("homework3")
    void testCheckAccess() {
        Random random = new Random();
        int age = random.nextInt(0, 100);

        String actual = TestTasks.checkAccess(age);
        String expected = (age > 18) ? "Allowed" : "Denied";

        assertThat(actual)
                .as("checkAccess(" + age + ")")
                .isEqualTo(expected);

//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED: checkAccess(" + age + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: checkAccess(" + age + ")");
//        }
    }

    @RepeatedTest(10)
    @Tag("homework3")
    void testIsPositive() {
        Random random = new Random();
        int number = random.nextInt(-100, 100);

        boolean actual = TestTasks.isPositive(number);
        boolean expected = number >= 0;

        assertThat(actual)
                .as("isPositive(" + number + ")")
                .isEqualTo(expected);

//        if (actual == expected) {
//            System.out.println("TEST PASSED: isPositive(" + number + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: isPositive(" + number + ")");
//        }
    }

    @ParameterizedTest
    @Tag("homework3")
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

        assertThat(actual)
                .as("getGrade(" + score + ")")
                .isEqualTo(expected);

//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED: getGrade(" + score + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: getGrade(" + score + ") expected " + expected + " but " + actual);
//        }
    }

    @RepeatedTest(10)
    @Tag("homework3")
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

        assertThat(actual)
                .as("blastOff(" + start + ")")
                .isEqualTo(expected);

//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED: blastOff(" + start + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: blastOff(" + start + ")");
//        }
    }

    @RepeatedTest(10)
    @Tag("homework3")
    void testSumToN() {
        Random random = new Random();
        int number = random.nextInt(1, 51);

        int actual = TestTasks.sumToN(number);
        int expected = 0;
        for (int i = 1; i <= number; i++) {
            expected = expected + i;
        }

        assertThat(actual)
                .as("sumToN(" + number + ")")
                .isEqualTo(expected);
    }

//        if (actual == expected) {
//            System.out.println("TEST PASSED: sumToN(" + number + ") = " + actual);
//        } else {
//            System.out.println("TEST FAILED: sumToN(" + number + ")");
//        }

//    @Test
    @RepeatedTest(10)
    @Tag("homework3")
    void testHasBug() {
        String[] withBug = {"Error", "Bug", "Info"};
        assertThat(TestTasks.hasBug(withBug))
                .as("Массив содержит 'Bug'")
                .isTrue();

//        boolean actual1 = TestTasks.hasBug(withBug);
//        if (actual1) {
//            System.out.println("TEST PASSED: hasBug (with Bug) = true");
//        } else {
//            System.out.println("TEST FAILED: hasBug (with Bug)");
//        }

        String[] withoutBug = {"Error", "Warning", "Info"};
        assertThat(TestTasks.hasBug(withoutBug))
                .as("Массив не содержит 'Bug'")
                .isFalse();
    }

//        boolean actual2 = TestTasks.hasBug(withoutBug);
//        if (!actual2) {
//            System.out.println("TEST PASSED: hasBug (without Bug) = false");
//        } else {
//            System.out.println("TEST FAILED: hasBug (without Bug)");
//        }

    @ParameterizedTest
    @Tag("homework3")
    @CsvSource({
            "2, 5, '2 4'",
            "1, 6, '2 4 6'",
            "1, 4, '2 4'",
            "3, 8, '4 6 8'",
            "4, 4, '4'",
            "5, 5, ''",
            "10, 15, '10 12 14'",
            "0, 10, '0 2 4 6 8 10'",
            "7, 12, '8 10 12'",
            "20, 25, '20 22 24'",
            "1, 1, ''",
            "2, 2, '2'"
    })
    void testGetEvenInRange(int start, int end, String expected) {
        String actual = TestTasks.getEvenInRange(start, end).trim();

        assertThat(actual)
                .as("getEvenInRange(" + start + ", " + end + ")")
                .isEqualTo(expected.trim());

//        expected = expected.trim();
//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED: getEvenInRange(" + start + ", " + end + ") = \"" + actual + "\"");
//        } else {
//            System.out.println("TEST FAILED: getEvenInRange(" + start + ", " + end + ")");
//        }
    }

    @ParameterizedTest
    @Tag("homework3")
    @CsvSource({
            "3, 7, 2, 9, 5, 9",
            "-5, -2, -10, -1, 0, 0",
            "15, 15, 15, 15, 15, 15",
            "1, 2, 3, 4, 5, 5",
            "100, 20, 30, 40, 50, 50",
            "-1, -2, -3, -4, -5, -1",
            "0, 0, 0, 0, 0, 0",
            "8, 3, 12, 5, 7, 12",
            "-10, 0, 10, -20, 5, 10",
            "9, 1, 8, 2, 7, 9",
            "4, 4, 4, 4, 9, 9"
    })
    void testFindMax(int a, int b, int c, int d, int e, int expected) {
        int[] array = {a, b, c, d, e};
        int actual = TestTasks.findMax(array);

        assertThat(actual)
                .as("findMax(" + Arrays.toString(array) + ")")
                .isEqualTo(expected);

//        if (actual == expected) {
//            System.out.println("TEST PASSED: findMax = " + actual);
//        } else {
//            System.out.println("TEST FAILED: findMax expected " + actual + " but " + expected);
//        }
    }

    @ParameterizedTest
    @Tag("homework3")
    @CsvSource({
            "One, Two, Zero, Zero, Two, One",
            "A, B, C, C, B, A",
            "Hello, World, Java, Java, World, Hello",
            "X, Y, Z, Z, Y, X",
            "Red, Green, Blue, Blue, Green, Red",
            "1, 2, 3, 3, 2, 1",
            "Cat, Dog, Bird, Bird, Dog, Cat",
            "Kotlin, Java, Scala, Scala, Java, Kotlin",
            "First, Second, Third, First, Second, Third",
            "Apple, Banana, Orange, Orange, Banana, Apple"
    })
    void testReverse(String a, String b, String c, String exp1, String exp2, String exp3) {
        String[] input = {a, b, c};
        String[] actual = TestTasks.reverse(input);
        String[] expected = {exp1, exp2, exp3};

        assertThat(actual)
                .as("reverse(" + Arrays.toString(input) + ")")
                .containsExactly(expected);

//        if (Arrays.equals(actual, expected)) {
//            System.out.println("TEST PASSED: reverse = " + Arrays.toString(actual));
//        } else {
//            System.out.println("TEST FAILED");
//        }
    }

//    @Test
    @RepeatedTest(10)
    @Tag("homework3")
    void testCalcAverage() {
        List<Integer> list = Arrays.asList(10, 20, 30);
        double actual = TestTasks.calcAverage(list);

        assertThat(actual)
                .as("Среднее арифметическое списка [10, 20, 30]")
                .isEqualTo(20.0);

//        double expected = 20.0;
//        if (Math.abs(actual - expected) < 0.0001) {
//            System.out.println("TEST PASSED: calcAverage = " + actual);
//        } else {
//            System.out.println("TEST FAILED: calcAverage expected " + expected + " but " + actual);
//        }
    }

//    @Test
    @RepeatedTest(10)
    @Tag("homework3")
    void testRemoveSpecificName() {
        List<String> input = Arrays.asList("Анна", "Иван", "Мария", "Иван", "Пётр");
        List<String> actual = TestTasks.removeSpecificName(input, "Иван");

        assertThat(actual)
                .as("После удаления имени 'Иван'")
                .containsExactly("Анна", "Мария", "Пётр");

//        List<String> expected = Arrays.asList("Анна", "Мария", "Пётр");
//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED: removeSpecificName = " + actual);
//        } else {
//            System.out.println("TEST FAILED");
//        }
    }
}
