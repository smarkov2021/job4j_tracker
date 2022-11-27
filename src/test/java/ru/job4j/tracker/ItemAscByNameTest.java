package ru.job4j.tracker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class ItemAscByNameTest {
    @Test
    public void whenSortThreeElem() {
        Item test = new Item("test");
        Item first = new Item("first");
        Item perviy = new Item("perviy");
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> expected = new ArrayList<>();
        items.add(test);
        items.add(first);
        items.add(perviy);
        expected.add(first);
        expected.add(perviy);
        expected.add(test);
        items.sort(new ItemAscByName());
        Assertions.assertThat(items).isEqualTo(expected);
    }

    @Test
    public void whenDescSortThreeElem() {
        Item test = new Item("test");
        Item first = new Item("first");
        Item perviy = new Item("perviy");
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> expected = new ArrayList<>();
        items.add(test);
        items.add(first);
        items.add(perviy);
        expected.add(test);
        expected.add(perviy);
        expected.add(first);
        items.sort(new ItemDescByName());
        Assertions.assertThat(items).isEqualTo(expected);
    }
}