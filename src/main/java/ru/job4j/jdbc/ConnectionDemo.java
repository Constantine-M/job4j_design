package ru.job4j.jdbc;

import ru.job4j.io.fileconfig.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 0. JDBC.
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
 * 6.Далее с помощью класса
 * {@link DatabaseMetaData}
 * мы можем получить информацию
 * о БД и ее внутренней структуре.
 *
 * В методе
 * {@link ConnectionDemo#writeProperties}
 * пробовал вместо url, login, password
 * вставить url + properties, т.к.
 * создал отдельный объект и думал,
 * что подтянет оттуда то, чего
 * не хватает. Оказывается, что нет -
 * не подтянет. Чтобы подтянуло,
 * нужно url записать так:
 * String url = "jdbc:postgresql:
 * //localhost/test?user=fred
 * &password=secret&ssl=true", где
 * props.setProperty("user","fred");
 * props.setProperty("password","secret");
 * props.setProperty("ssl","true");
 *
 * В методе {@link ConnectionDemo#main}
 * не совсем понятно, нужно ли
 * использовать абсолютный путь для
 * записи properties. Я решил
 * использовать.
 *
 * @author Constantine on 10.04.2022
 */
public class ConnectionDemo {

    public static void writeProperties(String url, String login, String password, String target) throws ClassNotFoundException {
        Properties properties = new Properties();
        properties.setProperty("login", login);
        properties.setProperty("password", password);
        try (Connection connection = DriverManager.getConnection(url, login, password);
            PrintWriter out = new PrintWriter(new FileWriter(target))) {
            DatabaseMetaData metaData = connection.getMetaData();
            out.println("driverVersion=" + metaData.getDriverVersion());
            out.println("driverName=" + metaData.getDriverName());
            out.println("login=" + metaData.getUserName());
            out.println("password=" + properties.get("password"));
            out.println("url=" + metaData.getURL());
        } catch (SQLException s) {
            s.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "Okhorzina2912";
        String target = "C:\\projects\\job4j_design\\app.properties";
        writeProperties(url, login, password, target);
        System.out.println(new Config("app.properties"));
    }
}
