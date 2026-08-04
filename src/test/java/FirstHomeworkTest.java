import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class FirstHomeworkTest {

    @Test
    public void testIsEven() {
        System.out.println("isEven(4) = " + TestTasks.isEven(4));
        System.out.println("isEven(7) = " + TestTasks.isEven(7));
        System.out.println("isEven(0) = " + TestTasks.isEven(0));
    }

    @Test
    public void testCheckAccess() {
        System.out.println("checkAccess(20) = " + TestTasks.checkAccess(20));
        System.out.println("checkAccess(18) = " + TestTasks.checkAccess(18));
        System.out.println("checkAccess(15) = " + TestTasks.checkAccess(15));
    }

    @Test
    public void testIsPositive() {
        System.out.println("isPositive(5) = " + TestTasks.isPositive(5));
        System.out.println("isPositive(0) = " + TestTasks.isPositive(0));
        System.out.println("isPositive(-3) = " + TestTasks.isPositive(-3));
    }

    @Test
    public void testGetGrade() {
        System.out.println("getGrade(0) = " + TestTasks.getGrade(0));
        System.out.println("getGrade(20) = " + TestTasks.getGrade(20));
        System.out.println("getGrade(21) = " + TestTasks.getGrade(21));
        System.out.println("getGrade(40) = " + TestTasks.getGrade(40));
        System.out.println("getGrade(50) = " + TestTasks.getGrade(50));
        System.out.println("getGrade(75) = " + TestTasks.getGrade(75));
        System.out.println("getGrade(81) = " + TestTasks.getGrade(81));
        System.out.println("getGrade(100) = " + TestTasks.getGrade(100));
        System.out.println("getGrade(-5) = " + TestTasks.getGrade(-5));
        System.out.println("getGrade(101) = " + TestTasks.getGrade(101));
    }

    @Test
    public void testBlastOff() {
        System.out.println("blastOff(5) = " + TestTasks.blastOff(5));
        System.out.println("blastOff(3) = " + TestTasks.blastOff(3));
        System.out.println("blastOff(1) = " + TestTasks.blastOff(1));
        System.out.println("blastOff(10) = " + TestTasks.blastOff(10));
    }

    @Test
    public void testSumToN() {
        System.out.println("sumToN(1) = " + TestTasks.sumToN(1));
        System.out.println("sumToN(3) = " + TestTasks.sumToN(3));
        System.out.println("sumToN(5) = " + TestTasks.sumToN(5));
        System.out.println("sumToN(10) = " + TestTasks.sumToN(10));
        System.out.println("sumToN(0) = " + TestTasks.sumToN(0));
    }

    @Test
    public void testHasBug() {
        System.out.println("Тест 1: " + TestTasks.hasBug(new String[]{"Error", "Bug", "Info"}));
        System.out.println("Тест 2: " + TestTasks.hasBug(new String[]{"error", "warning", "info"}));
        System.out.println("Тест 3: " + TestTasks.hasBug(new String[]{"BUG", "Critical"}));
        System.out.println("Тест 4: " + TestTasks.hasBug(new String[]{"bug"}));
        System.out.println("Тест 5: " + TestTasks.hasBug(new String[]{}));
        System.out.println("Тест 6: " + TestTasks.hasBug(new String[]{"Hello", "World", "bug", "Test"}));
    }

    @Test
    public void testGetEvenInRange() {
       System.out.println("getEvenInRange(1,3) = " + TestTasks.getEvenInRange(1,3));
       System.out.println("getEvenInRange(5,10) = " + TestTasks.getEvenInRange(5,10));
       System.out.println("getEvenInRange(0,50) = " + TestTasks.getEvenInRange(0,50));
       System.out.println("getEvenInRange(15,150) = " + TestTasks.getEvenInRange(15,150));
       System.out.println("getEvenInRange(-5,15) = " + TestTasks.getEvenInRange(-5,15));
       System.out.println("getEvenInRange(1000,1010) = " + TestTasks.getEvenInRange(1000,1010));
    }

    @Test
    public void testFindMax() {
        System.out.println("Тест 1: " + TestTasks.findMax(new int[]{3, 7, 2, 9, 5}));
        System.out.println("Тест 2: " + TestTasks.findMax(new int[]{-5, -2, -10, -1}));
        System.out.println("Тест 3: " + TestTasks.findMax(new int[]{15}));
        System.out.println("Тест 4: " + TestTasks.findMax(new int[]{-3, 0, 4, -8, 2}));
        System.out.println("Тест 5: " + TestTasks.findMax(new int[]{100, 100, 100}));
        System.out.println("Тест 6: " + TestTasks.findMax(new int[]{-20, -15, -30, -5, -25}));
    }

    @Test
    public void testReverse() {
        System.out.println("Тест 1: " + Arrays.toString(TestTasks.reverse(new String[]{"One", "Two", "Zero"})));
        System.out.println("Тест 2: " + Arrays.toString(TestTasks.reverse(new String[]{"A", "B", "C", "D"})));
        System.out.println("Тест 3: " + Arrays.toString(TestTasks.reverse(new String[]{"Hello"})));
        System.out.println("Тест 4: " + Arrays.toString(TestTasks.reverse(new String[]{})));
        System.out.println("Тест 5: " + Arrays.toString(TestTasks.reverse(new String[]{"Java", "Python", "C++", "Go", "Rust"})));
        System.out.println("Тест 6: " + Arrays.toString(TestTasks.reverse(new String[]{"1", "2", "3"})));
    }

    @Test
    public void testCalcAverage() {
        System.out.println("Тест 1: " + TestTasks.calcAverage(Arrays.asList(10, 20, 30)));
        System.out.println("Тест 2: " + TestTasks.calcAverage(Arrays.asList(7)));
        System.out.println("Тест 3: " + TestTasks.calcAverage(Arrays.asList(1, 2)));
        System.out.println("Тест 4: " + TestTasks.calcAverage(Arrays.asList(-10, -20, -30)));
        System.out.println("Тест 5: " + TestTasks.calcAverage(Arrays.asList(-5, 0, 5, 10)));
        System.out.println("Тест 6: " + TestTasks.calcAverage(new ArrayList<>()));
    }

    @Test
    public void testRemoveSpecificName() {
        System.out.println("Тест 1: " + TestTasks.removeSpecificName(
                Arrays.asList("Анна", "Иван", "Мария", "Иван", "Пётр"), "Иван"));
        System.out.println("Тест 2: " + TestTasks.removeSpecificName(
                Arrays.asList("Анна", "Мария", "Пётр"), "Сергей"));
        System.out.println("Тест 3: " + TestTasks.removeSpecificName(
                Arrays.asList("Иван"), "Иван"));
        System.out.println("Тест 4: " + TestTasks.removeSpecificName(
                new ArrayList<>(), "Иван"));
        System.out.println("Тест 5: " + TestTasks.removeSpecificName(
                Arrays.asList("Алекс", "Борис", "Виктор", "Алекс"), "Алекс"));
        System.out.println("Тест 6: " + TestTasks.removeSpecificName(
                Arrays.asList("Кирилл", "Кирилл", "Кирилл"), "Кирилл"));
    }
}