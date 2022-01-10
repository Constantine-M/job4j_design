package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 0.3. BufferedReader.
 * 0.4. BufferedOutputStream.
 *
 * @author Constantine on 08.01.2022
 */
public class LogFilter {

    /**
     * Данный метод фильтрует список записей.
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
     * @param file файл, записи в котором фильтруем
     * @return отфильтрованный список
     */
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

    /**
     * Данный метод сохраняет отфильтрованные
     * логи в отдельный файл.
     *
     * Исходный поток ввода - это файл FileOutputStream.
     * Он записывает данные по байтам. Блокирует всю
     * программу, пока запись не закончится. Это плохо.
     *
     * 1.Первая обертка - это BufferedOutputStream.
     * Это буфер, который собираем переданные в
     * него байты. Аккумулирует их и постепенно
     * отдает их в FileOutputStream. В этом случае
     * программа не блокируется до тех пока
     * в буфере есть место.
     *
     * 2.Вторая обертка над буфером - это PrintWriter.
     * Мы знаем, что будем записывать текст.
     * В Java есть удобное API для этого, например,
     * PrintWriter поддерживает метод
     * {@link PrintWriter#println()} для записи
     * данных с последующим переходом на новую строку.
     *
     * @param log отфильтрованный список (лог)
     * @param file файл, куда будет записан лог
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file))
        )) {
            for (String str : log) {
                out.printf("%s%n", str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
        save(log, "404.txt");
    }
}
