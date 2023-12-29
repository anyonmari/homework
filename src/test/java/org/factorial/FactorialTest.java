package org.factorial;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Факториал")
class FactorialTest {

    private Factorial fact;
    private int num;
    private ByteArrayInputStream inputStream = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;


    @Test
    @DisplayName("Вычислить факториал 1")
    public void testCalculateFactorialOfOne() {

        inputStream = new ByteArrayInputStream("1".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        fact = new Factorial(inputStream, ps);


        fact.calculateFactorial();
        String outputText = byteArrayOutputStream.toString();
        String key = "Факториал: ";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();

        Assertions.assertEquals("1", output);

    }

    @Test
    @DisplayName("Вычислить факториал 5")
    public void testCalculateFactorialOfFive() {

        inputStream = new ByteArrayInputStream("5".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        fact = new Factorial(inputStream, ps);

        //testGetTheInputNumber();
        fact.calculateFactorial();
        String outputText = byteArrayOutputStream.toString();
        String key = "Факториал:";
        String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
        Assertions.assertEquals("120", output);

    }

    @Test
    @DisplayName("Вычислить факториал -5")
    public void testCalculateFactorialOfNegative() {

        inputStream = new ByteArrayInputStream("-5".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        fact = new Factorial(inputStream, ps);


        fact.calculateFactorial();
        String outputText = byteArrayOutputStream.toString();
        String key = "Ошибка! Введите целое число от 1 до 10 включительно";
        String output = outputText.substring(outputText.indexOf(key));
        Assertions.assertEquals("Ошибка! Введите целое число от 1 до 10 включительно", output);

    }

    @Test
    @DisplayName("Валидное значение")
    void isInputNumberValid() {

        inputStream = new ByteArrayInputStream("abc".getBytes());
        byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        fact = new Factorial(inputStream, ps);
        fact.calculateFactorial();
        String outputText = byteArrayOutputStream.toString();

        String key = "Ошибка! Введите целое число от 1 до 10 включительно";
        String output = outputText.substring(outputText.indexOf(key));
        Assertions.assertEquals("Ошибка! Введите целое число от 1 до 10 включительно", output);

    }

    @AfterEach
    public void doTearDown() {
        inputStream = null;
        byteArrayOutputStream = null;
        fact = null;
    }
}