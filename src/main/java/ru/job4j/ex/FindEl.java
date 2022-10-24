package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                rsl = i;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element not Found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] value = {"test1", "test2", "test3", "test4"};
        String key = "test5";
        try {
            int index = indexOf(value, key);
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}