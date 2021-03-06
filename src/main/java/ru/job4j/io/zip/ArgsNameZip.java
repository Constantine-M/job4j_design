package ru.job4j.io.zip;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 5.2. Архивировать проект.
 *
 * Данный класс описывает анализ
 * входящей строки и разбиение
 * её на части согласно шаблону.
 *
 * @author Constantine on 08.02.2022
 */
public class ArgsNameZip {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
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
    private void parse(String args) {
        Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            if (matcher.group(1).equals("d") || matcher.group(1).equals("e") || matcher.group(1).equals("o")) {
                values.put(matcher.group(1), matcher.group(2));
            } else {
                throw new IllegalArgumentException("Use keys: -d (directory) / -e (exclude) / -o (output)");
            }
        }
    }

    /**
     * Данный метод анализирует строки.
     *
     * Если строка удовлетворяет
     * шаблону, то вызывается приватный
     * метод {@link ArgsNameZip#parse(String)}.
     *
     * Здесь в регулярном выражении
     * точка обозначает тот самый символ
     * "тире", наличие которого мы
     * должны проверять.
     *
     * @param args строки, передаваемые
     *             для анализа.
     * @return объект класса {@link ArgsNameZip}.
     */
    public static ArgsNameZip of(String[] args) {
        ArgsNameZip names = new ArgsNameZip();
        Pattern pattern = Pattern.compile("(.\\w*)=(.*)");
        for (String str : args) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                if (matcher.group(1).isEmpty() || !matcher.group(1).startsWith("-")) {
                    throw new IllegalArgumentException("No matches in pattern. Follow <-key>=<value> pattern!");
                }
                if (matcher.group(2).isEmpty()) {
                    throw new IllegalArgumentException("No matches in pattern. Please, enter the value!");
                }
                names.parse(str);
            } else {
                throw new IllegalArgumentException();
            }
        }
        return names;
    }
}