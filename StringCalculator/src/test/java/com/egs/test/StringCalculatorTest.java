package com.egs.test;
import org. junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StringCalculatorTest {

    @Test
    public void add_EmptyString_ReturnsZero() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void add_OneNumber_ReturnsSameNumber() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void add_TwoNumbers_ReturnsSum() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }
    @Test
    public void add_ThreeNumbers_ReturnsSum() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1,2,3");
        assertEquals(6, result);
    }
    @Test
    public void addNonNumbers() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1,2,3, , a,");
        assertEquals(6, result);
    }

    @Test
    public void add_NumberSeperatedbyNewLine() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1\n2,3");
        assertEquals(6, result);
    }
    @Test
    public void add_different_split(){
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//;\n1;2");
        assertEquals(3, result);

    }

    @Test
    public void add_OneNegetiveNumber() {
        StringCalculator calculator = new StringCalculator();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calculator.add("1,-2");
        });
        String expectedMessage = "Negatives not allowed: -2";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void add_TwoOrMoreNegetiveNumber() {
        StringCalculator calculator = new StringCalculator();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            calculator.add("2,-4,3,-5");
        });
        String expectedMessage = "Negatives not allowed: -4,-5";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void add_MoreThanThousand() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("1001,2");

        assertEquals(2, result);
    }
    @Test
    public void add_MultipleDelimiter() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//[|||]\n1|||2|||3");
        assertEquals(6, result);
    }
    @Test
    public void add_MultipleDelimiter_OneMore() {
        StringCalculator calculator = new StringCalculator();
        int result = calculator.add("//[|][%]\n1|2%3");
        assertEquals(6, result);
    }
}
