package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private Connection initConnection() throws SQLException, IOException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.prooerties")) {
            config.load(in);
            String driver = config.getProperty("hibernate.connection.driver_class");
            String url = config.getProperty("hibernate.connection.url");
            String login = config.getProperty("hibernate.connection.username");
            String password = config.getProperty("hibernate.connection.password");
            Class.forName(driver);
            return DriverManager.getConnection(url, login, password);
        }
    }

    public void createSelect(String s) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(s);
        }
    }

    public void createTable(String tableName) throws SQLException {
        createSelect(String.format("CREATE TABLE IF NOT EXISTS %s()", tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        createSelect(String.format("DROP TABLE %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        createSelect(String.format("ALTER TABLE %s ADD IF NOT EXISTS %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        createSelect(String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        createSelect(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TableEditor te = new TableEditor(properties);
        te.initConnection();
        te.createTable("table");
        System.out.println(te.getTableScheme("table"));
        te.addColumn("table", "test", "editor");
        System.out.println(te.getTableScheme("table"));
        te.renameColumn("table", "test", "editor");
        System.out.println(te.getTableScheme("myTable"));
        te.dropColumn("table", "test");
        System.out.println(te.getTableScheme("table"));
        te.dropTable("table");
    }
}

