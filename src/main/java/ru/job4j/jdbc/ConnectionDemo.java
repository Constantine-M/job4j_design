package ru.job4j.jdbc;

import ru.job4j.io.fileconfig.Config;

import java.io.*;
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
 * 7.В методе {@link Properties#setProperty}
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
 * @author Constantine on 10.04.2022
 */
public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        Properties config = new Properties();
        try (InputStream in = ConnectionDemo.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        Class.forName(config.getProperty("driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("login"),
                config.getProperty("password"));
             PrintWriter out = new PrintWriter(System.out)) {
            DatabaseMetaData metaData = connection.getMetaData();
            out.println(metaData.getUserName());
            out.println(metaData.getURL());
            out.println(metaData.getDriverName());
            out.println(metaData.getDriverVersion());
        }
    }
}
