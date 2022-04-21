package ru.job4j.jdbc.statement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * 0.1. Statement.
 *
 * Данный класс описывает исполнение
 * основных операций.
 *
 * @author Constantine on 18.04.2022
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;

    public TableEditor() throws SQLException, IOException, ClassNotFoundException {
        initConnection();
    }

    private Connection initConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
        return connection;
    }

    private void executeStatement(String sql) {
        try (Connection connection = initConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Данный метод создает пустую таблицу
     * без столбцов с указанным именем.
     * @param tableName наименование таблицы.
     */
    public void createTable(String tableName) throws SQLException, IOException, ClassNotFoundException {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        executeStatement(sql);
    }

    /**
     * Данный метод удаляет таблицу
     * по указанному имени.
     * @param tableName наименование таблицы.
     */
    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        executeStatement(sql);
    }

    /**
     * Данный метод добавляет столбец в таблицу.
     * @param tableName наименование таблицы.
     * @param columnName наименование столбца.
     * @param type тип столбца.
     */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN IF NOT EXISTS %s %s", tableName, columnName, type);
        executeStatement(sql);
    }

    /**
     * Данный метод удаляет столбец из таблицы.
     * @param tableName наименование таблицы.
     * @param columnName наименование столбца.
     */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s", tableName, columnName);
        executeStatement(sql);
    }

    /**
     * Данный метод переименовывает столбец.
     * @param tableName наименование таблицы.
     * @param columnName старое наименование столбца.
     * @param newColumnName новое наименование столбца.
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName);
        executeStatement(sql);
    }

    /**
     * Данный метод позволяет отобразить
     * в консоли созданную таблицу и ее
     * столбцы. При этом столбцы будут
     * отображены в виде строк.
     *
     * Еще раз. Строка - это есть столбец
     * и тип данных в этом столбце.
     *
     * @param tableName наименование таблицы.
     * @return отображение таблицы в консоли.
     */
    public String getTableScheme(String tableName) throws Exception {
        Connection connection = initConnection();
        var rowSeparator = "|"
                .concat("-".repeat(15))
                .concat("|")
                .concat("-".repeat(15)).concat("|")
                .concat(System.lineSeparator());
        var header = String.format("|%-15s|%-15s|%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("|%-15s|%-15s|%n",
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
        TableEditor tableEditor = new TableEditor();
        System.out.println("CREATE TABLE");
        tableEditor.createTable("systems");
        System.out.println("↓");
        System.out.println(tableEditor.getTableScheme("systems"));
        System.out.println(System.lineSeparator());
        System.out.println("ADD COLUMN");
        System.out.println("↓");
        tableEditor.addColumn("systems", "id", "serial");
        tableEditor.addColumn("systems", "name", "varchar(255)");
        tableEditor.addColumn("systems", "place", "varchar(255)");
        System.out.println(tableEditor.getTableScheme("systems"));
        System.out.println(System.lineSeparator());
        System.out.println("DROP COLUMN \"PLACE\"");
        tableEditor.dropColumn("systems", "place");
        System.out.println(tableEditor.getTableScheme("systems"));
        System.out.println(System.lineSeparator());
        System.out.println("RENAME COLUMN \"NAME\"");
        System.out.println("↓");
        tableEditor.renameColumn("systems", "name", "developer");
        System.out.println(tableEditor.getTableScheme("systems"));
        System.out.println(System.lineSeparator());
        System.out.println("DROP TABLE");
        System.out.println("↓");
        tableEditor.dropTable("systems");
        System.out.println("THERE'S NOTHING TO SEE");
    }
}
