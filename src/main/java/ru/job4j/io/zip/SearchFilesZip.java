package ru.job4j.io.zip;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    private Predicate<Path> condition;

    private List<Path> paths = new ArrayList<>();

    public SearchFilesZip(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
