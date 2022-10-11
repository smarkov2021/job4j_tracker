package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[size];
        int count = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName().equals(key)) {
                rsl[count] = item;
                count++;
            }
        }
        rsl = Arrays.copyOf(rsl, count);
        return rsl;
    }

    public Item[] findAll() {
        Item[] rsl = new Item[items.length];
        rsl = items;
        rsl = Arrays.copyOf(rsl, size);
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

        public Item findById(int id) {
            int index = indexOf(id);
            return index != -1 ? items[index] : null;
    }

        public boolean replace(int id, Item item) {
            int index = indexOf(id);
            if (index != -1) {
                items[index] = item;
                items[index].setId(id);
                return true;
            }
            return false;
        }
}