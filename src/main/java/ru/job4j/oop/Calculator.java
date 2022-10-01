package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int m) {
        return m - x;
    }

    public int divide(int d) {
        return d / x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int sumAllOperation(int s) {
        return  multiply(s) + divide(s) + minus(s) + sum(s);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int resultSum = sum(10);
        int resultMinus = minus(5);
        int resultDiv = calculator.divide(15);
        int resultMultiply = calculator.multiply(3);
        int sumAllOperation = calculator.sumAllOperation(5);
    }

}