package ru.job4j.io.zip;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 5.2. Архивировать проект.
 *
 * Данный класс описывает метод обхода
 * дерева файлов и указывает, что
 * делать с каждым файлом.
 *
 * @author Constantine on 08.02.2022
 */
public class SearchFilesZip extends SimpleFileVisitor<Path> {

    private String extension;

    private List<Path> paths = new ArrayList<>();

    public SearchFilesZip(String extension) {
        this.extension = extension;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toFile().getName().endsWith(extension)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
