package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplacedItemAndFindByGeneratedIsEqual() {
        SqlTracker tracker = new SqlTracker(connection);
        Item secondItem = new Item("secondItem");
        Item newItem = new Item("newItem");
        tracker.add(secondItem);
        int index = secondItem.getId();
        tracker.replace(index, newItem);
        assertThat(tracker.findById(index).getName()).isEqualTo("newItem");
    }

    @Test
    public void whenDeletedItemIsNotExist() {
        SqlTracker tracker = new SqlTracker(connection);
        Item thirdItem = new Item("thirdItem");
        tracker.add(thirdItem);
        tracker.delete(thirdItem.getId());
        assertThat(tracker.findById(thirdItem.getId())).isNull();
    }

    @Test
    public void whenListWithoutDeletedElemIsEqualAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("firstItem");
        Item secondItem = new Item("secondItem");
        Item thirdItem = new Item("thirdItem");
        List<Item> items = List.of(firstItem, thirdItem);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        tracker.delete(secondItem.getId());
        assertThat(items.equals(tracker.findAll())).isTrue();
    }

    @Test
    public void whenFindAllItemsIsEqualList() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("firstItem");
        Item secondItem = new Item("secondItem");
        Item thirdItem = new Item("thirdItem");
        List<Item> items = List.of(firstItem, secondItem, thirdItem);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        assertThat(items.equals(tracker.findAll())).isTrue();
    }

    @Test
    public void whenFindAllItemsIsNotEqualList() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("firstItem");
        Item secondItem = new Item("secondItem");
        Item thirdItem = new Item("thirdItem");
        List<Item> items = List.of(firstItem, secondItem, thirdItem);
        tracker.add(firstItem);
        tracker.add(secondItem);
        assertThat(items.equals(tracker.findAll())).isFalse();
    }

    @Test
    public void whenFindItemsByNameIsEqualList() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstItem = new Item("firstItem");
        Item secondItem = new Item("secondItem");
        Item thirdItem = new Item("thirdItem");
        Item fourthItem = new Item("secondItem");
        List<Item> items = List.of(secondItem, fourthItem);
        tracker.add(firstItem);
        tracker.add(secondItem);
        tracker.add(thirdItem);
        tracker.add(fourthItem);
        assertThat(items.equals(tracker.findByName("secondItem"))).isTrue();
    }
}