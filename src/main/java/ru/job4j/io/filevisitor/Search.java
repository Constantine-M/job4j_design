package ru.job4j.io.filevisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы.
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
     * @param root директория, которую проверяем.
     * @param condition условие выборки файла.
     * @throws IOException
     */
    public static void search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
    }

    public static void main(String[] args) throws  Exception {
        Path start = Paths.get("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\tree");
        search(start, p -> p.toFile().getName().endsWith(".java"));
    }
}
