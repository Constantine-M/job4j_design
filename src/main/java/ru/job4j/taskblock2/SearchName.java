package ru.job4j.taskblock2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию.
 *
 * Чтобы реализовать паттерн
 * "Стратегия", я использовал
 * метод по умолчанию, потому что
 * его мы можем имплементировть.
 *
 * @author Constantine on 20.03.2022
 */
public class SearchName implements Search {

    @Override
    public List<String> search(Path root, String fileName) throws IOException {
        Predicate<Path> pred = path -> path.toFile().getName().equals(fileName);
        SearchFilesIO searcher = new SearchFilesIO(pred);
        Files.walkFileTree(root, searcher);
        return searcher.getFiles();
    }
}
