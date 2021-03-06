package ru.job4j.io.fileconfig;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1. Читаем файл конфигурации.
 *
 * Данный класс описывает настройки
 * программы - базы данных, пути хранения и т.д.
 * Все эти настройки следует хранить
 * вне кода программы.
 *
 * Файл настроек должен содержать пары ключ-знание.
 *
 * @author Constantine on 12.01.2022
 */
public class Config {

    private final String path;

    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    /**
     * Данный метод считывает
     * все ключи в карту values.
     *
     * 1.С помощью цикла мы построчно
     * считывали текст файла.
     *
     * 2.Далее проверяли, имеются ли
     * пустые строки и строки с комментариями.
     *
     * 3.Для разбиения строки я
     * использовал regex и {@link Matcher}.
     *
     * 4.REGEXP выделил в строке
     * 2 группы символов - до
     * знака "=" и после этого знака.
     *
     * 5.С помощью {@link Matcher#find()}
     * я находил эти групы и добавлял
     * в карту.
     */
    public void load() {
        Pattern pattern = Pattern.compile("(.*)=(.*)|(.*)=(^$)");
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String text;
            while ((text = read.readLine()) != null) {
                if (!text.isEmpty() && !text.startsWith("#")) {
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        values.put(matcher.group(1), matcher.group(2));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Данный метод возвращает значение ключа.
     * @param key ключ.
     * @return значение ключа.
     */
    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key is not found! Fail to parse the key from file.");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./src/main/resources/app.properties"));
    }
}
