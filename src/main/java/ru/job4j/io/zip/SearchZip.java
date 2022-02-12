package ru.job4j.io.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 5.2. Архивировать проект.
 *
 * Данный класс описывает наш поисковик.
 *
 * @author Constantine on 08.02.2022
 */
public class SearchZip {

    /**
     * Данный метод ищет пути к файлам,
     * за исключением файлов с указанным
     * расширением. Путь к ним не
     * записывается в список путей.
     *
     * Метод проверяет начальную папку
     * и все вложенные в нее папки.
     *
     * @param root начальная папка.
     * @param extension расширение файла.
     * @throws IOException
     */
    public static List<Path> search(Path root, String extension) throws IOException {
        if (!Files.exists(root)) {
            throw new IllegalArgumentException("The path is not exists!");
        }
        SearchFilesZip searcher = new SearchFilesZip(extension);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
