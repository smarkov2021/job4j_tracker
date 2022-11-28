package ru.job4j.tracker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ItemAscByNameTest {
    @Test
    public void whenSortThreeElem() {
        Item test = new Item("test");
        Item first = new Item("first");
        Item perviy = new Item("perviy");
        List<Item> items = Arrays.asList(test, first, perviy);
        List<Item> expected = Arrays.asList(first, perviy, test);
        items.sort(new ItemAscByName());
        Assertions.assertThat(items).isEqualTo(expected);
    }

    @Test
    public void whenDescSortThreeElem() {
        Item test = new Item("test");
        Item first = new Item("first");
        Item perviy = new Item("perviy");
        List<Item> items = Arrays.asList(test, first, perviy);
        List<Item> expected = Arrays.asList(test, perviy, first);
        items.sort(new ItemDescByName());
        Assertions.assertThat(items).isEqualTo(expected);
    }
}