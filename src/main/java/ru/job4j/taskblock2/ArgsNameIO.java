package ru.job4j.taskblock2;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2. Поиск файлов по критерию.
 *
 * Данный класс описывает анализ
 * входящей строки и разбиение
 * её на части согласно шаблону.
 *
 * Данный класс является шаблонным.
 * Он нужен для сохранения
 * структуры пакета для выполнения
 * задания. Изменения касаются
 * имени класса и ключей.
 *
 * @author Constantine on 20.03.2022
 */
public class ArgsNameIO {

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
            if (matcher.group(1).equals("d") || matcher.group(1).equals("n") || matcher.group(1).equals("t")
                || matcher.group(1).equals("o")) {
                values.put(matcher.group(1), matcher.group(2));
            } else {
                throw new IllegalArgumentException("Follow this pattern: " + System.lineSeparator()
                        + "-d=DIRECTORY -n=FILE_NAME -t=SEARCH_TYPE -o=FILE" + System.lineSeparator()
                        + "Use keys:" + System.lineSeparator()
                        + "-d  directory" + System.lineSeparator()
                        + "-n  file name, mask or regex" + System.lineSeparator()
                        + "-t  search type (complete match, mask, regex)" + System.lineSeparator()
                        + "-o  file, where log will be written (<.txt> for ex.)");
            }
        }
    }

    /**
     * Данный метод анализирует строки.
     *
     * Если строка удовлетворяет
     * шаблону, то вызывается приватный
     * метод {@link ArgsNameIO#parse(String)}.
     *
     * Здесь, в регулярном выражении
     * точка обозначает тот самый символ
     * "тире", наличие которого мы
     * должны проверять.
     *
     * @param args строки, передаваемые
     *             для анализа.
     * @return объект класса {@link ArgsNameIO}.
     */
    public static ArgsNameIO of(String[] args) {
        ArgsNameIO names = new ArgsNameIO();
        Pattern pattern = Pattern.compile("(.\\w*)=(.*)");
        for (String str : args) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                if (matcher.group(1).isEmpty() || !matcher.group(1).startsWith("-")) {
                    throw new IllegalArgumentException("No matches in pattern. Follow [-key=value] pattern!");
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
