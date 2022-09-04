package ru.job4j.gc.cache;

import ru.job4j.io.zip.SearchFilesZip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * Это тестовый класс. Создан в
 * изыскательных целях для работы с
 * относительными путями. Пока
 * что трудно сказать, что это оно..
 *
 * @author Constantine on 04.09.2022
 */
public class Search {

    public static List<Path> search(Path root) throws IOException {
        SearchFiles searcher = new SearchFiles();
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
