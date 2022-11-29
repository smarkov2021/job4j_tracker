package ru.job4j.collection;

import java.util.Comparator;

import static java.lang.Math.min;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        for (int index = 0; index < min(left.length(), right.length()); index++) {
            char leftAsChar = left.charAt(index);
            char rightAsChar = right.charAt(index);
            if (leftAsChar != rightAsChar) {
                return Character.compare(left.charAt(index), right.charAt(index));
            }
        }
        if (left.length() == right.length()) {
            return 0;
        }
        return left.length() > right.length() ? 1 : -1;
    }
}