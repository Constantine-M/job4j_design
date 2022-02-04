package ru.job4j.io.filevisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы.
 * 5. Валидация параметров запуска.
 *
 * Данный класс описывает наш поисковик.
 *
 * @author Constantine on 24.01.2022
 */
public class Search {

    /**
     * Данный метод ищет файлы, которые
     * удовлетворяют нашему условию.
     * Условие может быть любое.
     *
     * Метод проверяет начальную папку
     * и все вложенные в нее папки.
     *
     * @param root начальная папка.
     * @param condition условие поиска файла.
     * @throws IOException
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean valid(String[] args) {
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("Enter the path you want to check!");
        }
        if (args[1].isEmpty()) {
            throw new IllegalArgumentException("Please, enter the extension you want to find!");
        }
        return true;
    }

    public static void main(String[] args) throws  Exception {
        if (valid(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }
}
