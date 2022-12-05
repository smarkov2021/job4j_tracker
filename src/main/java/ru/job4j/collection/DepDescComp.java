package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1AsArray = o1.split("/");
        String[] o2AsArray = o2.split("/");
        int result = o2AsArray[0].compareTo(o1AsArray[0]);
        return result != 0 ? result : o1.compareTo(o2);
    }
}