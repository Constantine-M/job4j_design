package ru.job4j.taskblock2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию.
 *
 * @author Constantine on 19.03.2022
 */
public interface Search {

    /**
     * Данный метод является методом по
     * умолчанию и находит файлы по
     * полному совпадению в имени файла.
     *
     * Чтобы разбить функционал на
     * классы по шаблону "Стратегия",
     * пришлось написать метод по
     * умолчанию и его в классах менять.
     *
     * Пришлось так сделать, потому
     * что метод должен возвращать
     * список файлов. Метод без реализации
     * должен быть "void".
     *
     * Это сделано исключительно в
     * учебных целях, чтобы попрактиковать
     * шаблоны проектирования и
     * немного лучше разобраться при
     * использовании и написании
     * интерфейсов.
     *
     * @param root директория.
     * @param fileName имя файла.
     * @return список совпадений.
     */
    default List<String> search(Path root, String fileName) throws IOException {
        Predicate<Path> pred = path -> path.toFile().toString().equals(fileName);
        SearchFilesIO searcher = new SearchFilesIO(pred);
        Files.walkFileTree(root, searcher);
        return searcher.getFiles();
    }
}
