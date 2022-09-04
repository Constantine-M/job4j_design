package ru.job4j.gc.cache;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Это тестовый класс. Создан в
 * изыскательных целях для работы с
 * относительными путями. Пока
 * что трудно сказать, что это оно..
 *
 * @author Constantine on 04.09.2022
 */
public class SearchFiles extends SimpleFileVisitor<Path> {

    private List<Path> paths = new ArrayList<>();

    public List<Path> getPaths() {
        return paths;
    }

    /**
     * Чтобы обойти дерево и найти этот
     * файл, нужно проверить, что файл
     * содержит название того, что мы ищем.
     * Но название в виде String передать
     * нельзя - нужна последовательность
     * символов. А это уже не то, что нам нужно.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Predicate<Path> pred = path -> path.toFile().getName().contains("botPhrases.txt");
        if (pred.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
