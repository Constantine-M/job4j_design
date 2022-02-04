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
        return values.get(key);
    }

    /**
     * Данный метод разбивает входящую
     * строку на пары ключ-значение.
     *
     * У алгоритма ниже (с использованием
     * Pattern/Matcher) есть минус -
     * он может выкинуть NPE.
     * 1.Проходим по массиву строк.
     * 2.С помощью регулярного выражения
     * находим совпадение.
     * 3.Заполняем карту.
     *
     * Поэтому воспользуемся методом
     * {@link String#split(String)}.
     * 1.Проходимся по массиву.
     * 2.Разбиваем строку по символу "=".
     * 3.Делаем все нужные проверки:
     * значений 2 (до = и после =),
     * первое не пустое и начинается с "-",
     * второе значение не пустое.
     * Таким образом в одном методе
     * мы ловим все исключения.
     *
     * @param args входящие строки.
     */
    private void parse(String args) {
       /* String[] str = args.split("=");
        values.put(str[0].substring(1), str[1]);*/
        Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            values.put(matcher.group(1), matcher.group(2));
        }
     /*   Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        for (String str : args) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                values.put(matcher.group(1), matcher.group(2));
            }
        }*/
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        Pattern pattern = Pattern.compile("(.\\w*)=(.*)");
        for (String str : args) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                if (matcher.group(1).isEmpty() || !matcher.group(1).startsWith("-")) {
                    throw new IllegalArgumentException();
                }
                if (matcher.group(2).isEmpty()) {
                    throw new IllegalArgumentException();
                }
            names.parse(str);
            } else {
                throw new IllegalArgumentException();
            }
        }
        /*for (String str : args) {
            String[] val = str.split("=");
            if (val.length >= 0) {
                throw new IllegalArgumentException();
            }
            if (val[0].isEmpty() || !val[0].startsWith("-")) {
                throw new IllegalArgumentException();
            }
            if (val[1].isEmpty()) {
                throw new IllegalArgumentException();
            }
            names.parse(str);
        }*/
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
