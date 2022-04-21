package ru.job4j.jdbc.jdbc;

import ru.job4j.io.fileconfig.Config;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * 0. JDBC.
 * 0.1. Statement.
 *
 * Данный класс описывает работу
 * с БД посредством JDBC.
 *
 * 1.Первой же строкой регистрируем
 * драйвер в системе.
 *
 * 2.Далее подключимся к БД.
 * Для этого воспользуемся классом
 * {@link DriverManager}.
 *
 * 3.Для подключения необходимы
 * url, логин и пароль.
 *
 * 4.В url стоит префикс “jdbc:postgres”.
 * Это указывает, что мы подключаемся
 * к postgres через jdbc. Далее как
 * обычно идет хост и порт, а за
 * ними уже имя базы данных.
 *
 * 5.Таким образом, мы получили объект
 * типа {@link Connection}.
 * Если он не равен null, то это значит,
 * что установлено подключение.
 *
 * 6.Для исполнения операций существуют
 * специальные интерфейсы:
 * {@link Statement}, PrepareStatement.
 *
 * 7.С помощью команды
 * {@link Connection#createStatement()}
 * мы создали объект для запроса.
 * Для его выполнения существует
 * 3 метода: execute(), executeUpdate(),
 * executeQuery().
 *
 * 8.Создали таблицу с помощью
 * метода {@link Statement#execute}.
 *
 * 9.В методе
 * {@link ConnectionDemo#getTableScheme}
 * проверим, что таблица создалась.
 * Для этого выведем ее схему, а
 * именно ее столбцы и их типы.
 * Здесь мы уже плотно используем
 * {@link String#format}.
 *
 * С помощью класса
 * {@link DatabaseMetaData}
 * мы можем получить информацию
 * о БД и ее внутренней структуре.
 *
 * В методе {@link Properties#setProperty}
 * пробовал вместо url, login, password
 * вставить url + properties, т.к.
 * создал отдельный объект и думал,
 * что подтянет оттуда то, чего
 * не хватает. Оказывается, что нет -
 * не подтянет.
 *
 * Чтобы подтянуло, нужно url записать так:
 * String url = "jdbc:postgresql:
 * /localhost/test?user=fred
 * &password=secret&ssl=true", где
 * props.setProperty("user","fred");
 * props.setProperty("password","secret");
 * props.setProperty("ssl","true");
 *
 * UPDATE
 * Спасибо за подсказки!
 * Т.к. я уже залез в {@link Properties},
 * то используем этот класс и его
 * методы. Метод {@link Properties#load}
 * делает то же самое, что и метод
 * {@link Config#load()} - мы указываем,
 * какой файл читать, сохраняем
 * записи в объект класса
 * {@link Properties}, а потом
 * достаем по нужным ключам
 * интересующие нас значения.
 *
 * Объект {@link ClassLoader} можно
 * создать как отдельно, так и
 * внутри блока try(). Главное -
 * не забыть. Вот ключевые слова:
 * <твой класс>.class.getClassLoader.
 *
 * {@link ClassLoader} можно
 * представить в виде потока.
 * Для этого используем метод
 * {@link ClassLoader#getResourceAsStream}.
 *
 * @author Constantine on 18.04.2022
 */
public class ConnectionDemo {

    private static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = ConnectionDemo.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver"));
        return DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("login"),
                config.getProperty("password"));
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
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

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        try (Connection connection = getConnection();
             PrintWriter out = new PrintWriter(System.out)) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id serial PRIMARY KEY",
                        "name varchar(255)"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            DatabaseMetaData metaData = connection.getMetaData();
            out.println(metaData.getUserName());
            out.println(metaData.getURL());
            out.println(metaData.getDriverName());
            out.println(metaData.getDriverVersion());
        }
    }
}
