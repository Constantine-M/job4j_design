package ru.job4j.io.filevisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;

/**
 * 4.1. Сканирование файловой системы.
 *
 * Данный класс описывает метод обхода
 * дерева файлов и указывает, что
 * делать с каждым файлом.
 *
 * @author Constantine on 24.01.2022
 */
public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            System.out.println(file.toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
