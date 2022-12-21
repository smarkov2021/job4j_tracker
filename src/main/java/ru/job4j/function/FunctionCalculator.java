package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionCalculator {
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        int value = start;
        while (value < end) {
            result.add(func.apply((double) value));
            value++;
        }
        return result;
    }
}