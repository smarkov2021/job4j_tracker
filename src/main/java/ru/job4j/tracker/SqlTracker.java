package ru.job4j.tracker;

import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection cn;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    private void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Timestamp timestampFromLDT = Timestamp.valueOf(item.getTime());
        int lastIndex;
        try (Statement statement = cn.createStatement()) {
            ResultSet lf = statement.executeQuery("select max(id) from items");
            lf.next();
            lastIndex = lf.getInt(1);
        try (PreparedStatement ps = cn.prepareStatement("INSERT INTO items(name, created) values (?, ?)")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, timestampFromLDT);
            ps.execute();
        }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        item.setId(lastIndex + 1);
        return item;
    }

    @Override
    public boolean replace(int id, Item item)  {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement("UPDATE items SET name = ? where id =?")) {
            ps.setString(1, item.getName());
            ps.setInt(2, id);
            ps.execute();
            rsl = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement ps = cn.prepareStatement("delete from items where id =?")) {
            ps.setInt(1, id);
            ps.execute();
            rsl = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement("select id, name, created from items")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int ide = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp timestamp = resultSet.getTimestamp("created");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                rsl.add(new Item(ide, name, localDateTime));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key)  {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement("select id, name, created from items where name = ?")) {
            ps.setString(1, key);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int ide = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp timestamp = resultSet.getTimestamp("created");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                rsl.add(new Item(ide, name, localDateTime));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Item rsl = null;
        try (PreparedStatement ps = cn.prepareStatement("select id, name, created from items where id = ? limit 1")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int ide = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp timestamp = resultSet.getTimestamp("created");
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                rsl = new Item(ide, name, localDateTime);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        return rsl;
        }
    }