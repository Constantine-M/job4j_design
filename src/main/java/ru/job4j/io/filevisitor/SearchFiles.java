package ru.job4j.io.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 4.1. Сканирование файловой системы.
 * 5. Валидация параметров запуска.
 *
 * Данный класс описывает метод обхода
 * дерева файлов и указывает, что
 * делать с каждым файлом.
 *
 * @author Constantine on 24.01.2022
 */
public class SearchFiles extends SimpleFileVisitor<Path> {

    /**
     * Данное поле описывает расширение файла.
     */
    private String extension;

    private List<Path> paths = new ArrayList<>();

    public SearchFiles(String extension) {
        this.extension = extension;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (extension == null || extension.isEmpty()) {
            throw new IllegalArgumentException("Please, enter the extension you want to find!");
        }
        if (file.toFile().getName().endsWith(extension)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
