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
     * он может выкинуть NPE. Но мы
     * этого избежали, т.к. все проверки
     * проходятся в методе
     * {@link ArgsName#of(String[])}.
     * 1.Проходим по массиву строк.
     * 2.С помощью регулярного выражения
     * находим совпадение.
     * 3.Заполняем карту.
     *
     * Можно еще воспользоваться методом
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
        Pattern pattern = Pattern.compile("(\\w*)=(.*)");
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            values.put(matcher.group(1), matcher.group(2));
        }
    }

    /**
     * Данный метод анализирует строки.
     *
     * Если строка удовлетворяет
     * шаблону, то вызывается приватный
     * метод {@link ArgsName#parse(String)}.
     *
     * Здесь в регулярном выражении
     * точка обозначает тот самый символ
     * "тире", наличие которого мы
     * должны проверять.
     *
     * @param args строки, передаваемые
     *             для анализа.
     * @return объект класса {@link ArgsName}.
     */
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
