package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftAsArray = left.split("\\.");
        String[] rightAsArray = right.split("\\.");
        return Integer.compare(Integer.parseInt(leftAsArray[0]), Integer.parseInt(rightAsArray[0]));
    }
}