package ru.job4j.taskblock2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию.
 *
 * @author Constantine on 20.03.2022
 */
public class SearchRegex implements Search {

    @Override
    public List<String> search(Path root, String regex) throws IOException {
        Predicate<Path> pred = path -> path.toFile().getName().matches(regex);
        SearchFilesIO searcher = new SearchFilesIO(pred);
        Files.walkFileTree(root, searcher);
        return searcher.getFiles();
    }
}
