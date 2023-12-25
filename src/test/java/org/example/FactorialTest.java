package org.example;

import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.testng.Assert.*;

public class FactorialTest {

    private Factorial factorial;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeMethod
    public void setUpStreams() {
        String input = "5\n"; // Ввод, который будет симулирован
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        factorial = new Factorial(System.in, System.out);
    }

    @Test
    public void testValidInput() {
        int result = factorial.isInputNumberValid();
        assertEquals(result, 5);
    }

    @Test
    public void testInvalidInput() {
        String input = "15\n"; // Ввод недопустимого числа
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        factorial = new Factorial(inputStream, System.out);

        int result = factorial.isInputNumberValid();
        assertEquals(result, -1);
        assertEquals(outContent.toString(), "Ошибка! Введите числа от 1 до 10 включительно");
    }

    @Test
    public void testCalculateFactorial() {
        int input = factorial.isInputNumberValid();
        if (input != -1) {
            factorial.calculateFactorial(input);
            assertEquals(outContent.toString(), "Факториал: 120");
        }
    }

    @AfterMethod
    public void restoreStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
        System.setErr(System.err);
    }
}
