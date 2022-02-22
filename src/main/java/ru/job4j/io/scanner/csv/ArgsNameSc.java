package ru.job4j.io.scanner.csv;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 7. Scanner.
 *
 * Данный класс описывает анализ
 * входящей строки и разбиение
 * её на части согласно шаблону.
 *
 * Код взят (намеренно) из класса
 * {@link ru.job4j.io.zip.ArgsNameZip}.
 * Это нужно, чтобы понимать,
 * какие блоки потребовались
 * для выполнения задачи.
 *
 * @author Constantine on 21.02.2022
 */
public class ArgsNameSc {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String args) {
        Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            if (matcher.group(1).equals("path") || matcher.group(1).equals("delimiter")
                    || matcher.group(1).equals("out") || matcher.group(1).equals("filter")) {
                values.put(matcher.group(1), matcher.group(2));
            } else {
                throw new IllegalArgumentException("Use keys: -path / -delimiter / -out / -filter");
            }
        }
    }

    public static ArgsNameSc of(String[] args) {
        ArgsNameSc names = new ArgsNameSc();
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
