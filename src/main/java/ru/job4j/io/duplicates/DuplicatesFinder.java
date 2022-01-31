package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
     * @throws IOException
     */
    public static void findDuplicate(Path path) throws IOException {
        DuplicatesVisitor dupleVisit = new DuplicatesVisitor();
        Files.walkFileTree(path, dupleVisit);
        dupleVisit.getDuplicates().forEach((key, value) -> {
            if (value.size() > 1) {
                value.forEach(System.out::println);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Path root = Path.of("./data");
        findDuplicate(root);
    }
}
