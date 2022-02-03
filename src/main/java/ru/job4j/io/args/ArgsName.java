package ru.job4j.io.args;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 5.1. Именованные аргументы.
 *
 * @author Constantine on 02.02.2022
 */
public class ArgsName {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not found!");
        }
        return values.get(key);
    }

    /**
     * Данный метод разбивает входящую
     * строку на пары ключ-значение.
     *
     * 1.Проходим по массиву строк.
     * 2.С помощью регулярного выражения
     * находим совпадение.
     * 3.Заполняем карту.
     *
     * @param args входящие строки.
     */
    private void parse(String[] args) {
        Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        for (String str : args) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                values.put(matcher.group(1), matcher.group(2));
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        ArgsName test = ArgsName.of(new String[] {"-out=project.zip", "-encoding="});
        System.out.println(test.get("encoding"));
    }
}
