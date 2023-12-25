package org.example;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Factorial {


    private  Scanner scanner;
    private PrintStream printStream;
    private int num;

    public static void main(String[] args) {
        Factorial fact = new Factorial(System.in,System.out);
        System.out.println("Введите целое число от 1 до 10 включительно");
        fact.calculateFactorial();
    }

    public Factorial(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public int isInputNumberValid()  {
        String input = this.scanner.nextLine();

        try {
            int num = Integer.parseInt(input);

            if(num >= 1 && num <= 10) {

                this.num = num;

                return num;
            } else {
                this.printStream.print("Ошибка! Введите числа от 1 до 10 включительно");
                return -1;
            }

        } catch (NumberFormatException e) {
            this.printStream.print("Ошибка! Введите числа от 1 до 10 включительно");
            return -1;
        }
    }


    public void calculateFactorial(int num) {
        int result = 1;
        for(int i = 1; i <= num; i++) {
            result *= i;

        }
        this.printStream.print("Факториал: " + result);

    }

    public void calculateFactorial() {

        int input = this.isInputNumberValid();
        if(input != -1) {
            calculateFactorial(input);
        }

    }
}
