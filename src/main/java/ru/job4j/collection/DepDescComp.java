package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] o1AsArray = o1.split("/");
        String[] o2AsArray = o2.split("/");
        if (o2AsArray[0].compareTo(o1AsArray[0]) != 0) {
            return o2AsArray[0].compareTo(o1AsArray[0]);
        }
        for (int index = 1; index < Math.min(o1AsArray.length, o2AsArray.length); index++) {
            if (o1AsArray[index].compareTo(o2AsArray[index]) != 0) {
                return o1AsArray[index].compareTo(o2AsArray[index]);
            }
        }
        return Integer.compare(o1AsArray.length, o2AsArray.length);
    }
}