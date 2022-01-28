package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * 4.2. Поиск дубликатов.
 *
 * @author Constantine on 25.01.2022
 */
public class DuplicatesFinder {

    /**
     * Данный метод находит дубликаты
     * и помещает их в список.
     * @param path директория, которую нужно проверить.
     * @return список путей к файлам-дубликатам.
     * @throws IOException
     */
    public static List<Path> findDuplicate(Path path) throws IOException {
        DuplicatesVisitor dupleVisit = new DuplicatesVisitor();
        Files.walkFileTree(path, dupleVisit);
        return dupleVisit.getDuplicate();
    }

    public static void main(String[] args) throws IOException {
        Path root = Path.of("./data");
        List<Path> list = findDuplicate(root);
        list.forEach(System.out::println);
    }
}
