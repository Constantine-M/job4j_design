package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 0.3. BufferedReader.
 *
 * 1.С помощью {@link Pattern} мы
 * написали регулярное выражение.
 * С его помощью мы найдем нужные
 * строки в файле.
 *
 * 2.Создаем список, куда внесем
 * результаты поиска.
 *
 * 3.Используя Stream API, произведем
 * фильтрацию и результаты занесем в
 * список.
 *
 * @author Constantine on 08.01.2022
 */
public class LogFilter {

    public static List<String> filter(String file) {
        Pattern pattern = Pattern.compile("\\s404\\s(-|\\d{4})");
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            result = in.lines()
                    .filter(pattern.asPredicate())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
    }
}
