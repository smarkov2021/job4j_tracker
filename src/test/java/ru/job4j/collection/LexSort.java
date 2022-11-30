package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftAsArray = left.split("\\.");
        String[] rightAsArray = right.split("\\.");
        for (int index = 0; index < rightAsArray.length; index++) {
            if (Integer.compare(Integer.parseInt(leftAsArray[index]), Integer.parseInt(rightAsArray[index])) != 0) {
                return Integer.compare(Integer.parseInt(leftAsArray[index]),
                        Integer.parseInt(rightAsArray[index]));
            }
        }
        return 0;
    }
}