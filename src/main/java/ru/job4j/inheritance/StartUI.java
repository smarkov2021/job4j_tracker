package ru.job4j.inheritance;

import ru.job4j.tracker.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {
    public static void main(String[] args) {
        Item first = new Item();
        LocalDateTime currentDateTime = first.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        String currentDateTimeFormat = currentDateTime.format(formatter);
        System.out.println(currentDateTimeFormat);
    }
}
