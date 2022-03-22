package ru.job4j.taskblock2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию.
 *
 * Данный класс описывает наш поисковик.
 *
 * Данный класс является шаблонным.
 * Он нужен для сохранения
 * структуры пакета для выполнения
 * задания. Изменения касаются
 * имени класса и порядка обхода
 * директории.
 * 
 * @author Constantine on 19.03.2022
 */
public class SearchFilesIO extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;

    private List<String> files = new ArrayList<>();

    public SearchFilesIO(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<String> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            files.add(file.toFile().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
