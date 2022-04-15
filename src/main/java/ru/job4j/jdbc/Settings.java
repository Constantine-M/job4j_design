package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Данныq класс описывает настройки
 * для подключения.
 *
 * Он тестовый.
 *
 * @author Constantine on 15.04.2022
 */
public class Settings {

    private final Properties prop = new Properties();

    public void load() {
        final ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.prop.getProperty(key);
    }
}
