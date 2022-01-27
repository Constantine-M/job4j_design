package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * 4.2. Поиск дубликатов.
 *
 * Данный класс описывает метод обхода.
 * Здесь мы описываем поиск дублей.
 *
 * @author Constantine on 25.01.2022
 */
public class DuplicatesVisitor extends SimpleFileVisitor<java.nio.file.Path> {

    private final Map<FileProperty, Path> hmap = new HashMap<>();

    private List<Path> duplicate = new ArrayList<>();

    public List<Path> getDuplicate() {
        return duplicate;
    }

    /**
     * Данный метод проходит по всей структуре
     * файла, анализируя каждый элемент.
     *
     * @param file директория.
     * @return переходит к следующему файлу
     * в структуре.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!hmap.containsKey(currentFile)) {
            hmap.put(currentFile, file);
        } else {
            duplicate.add(file);
            duplicate.add(hmap.get(currentFile));
        }
        return FileVisitResult.CONTINUE;
    }
}
