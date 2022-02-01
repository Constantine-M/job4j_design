package ru.job4j.io.filevisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
     * @param extension расширение файла.
     * @throws IOException
     */
    public static List<Path> search(Path root, String extension) throws IOException {
        if (root == null) {
            throw new IllegalArgumentException("Enter the path you want to check!");
        }
        SearchFiles searcher = new SearchFiles(extension);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws  Exception {
        Path root = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\tree");
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", root.toAbsolutePath()));
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", root.toAbsolutePath()));
        }
        List<Path> paths = search(root, ".java");
        paths.forEach(System.out::println);
    }
}
