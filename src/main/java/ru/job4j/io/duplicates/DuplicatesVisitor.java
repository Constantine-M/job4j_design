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
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /**
     * Данное поле описывает список
     * уникальных файлов.
     */
    private Set<FileProperty> uniques = new HashSet<>();

    /**
     * Данное поле описывает список
     * файлов-дубликатов.
     */
    private List<Path> duplicates = new ArrayList<>();

    public List<Path> getDuplicates() {
        return duplicates;
    }

    /**
     * Данный метод проходит по всей структуре
     * файла, анализируя каждый элемент.
     *
     * 1.Так как файл определен именем и размером,
     * то чтобы найти дубль, использовали
     * структуру {@link Set}. Туда попадают
     * только уникальные файлы.
     *
     * 2.Все дубли будут записаны в список
     * дубликатов (отдельный список).
     *
     * Но есть нюанс - мы не можем получить
     * все дубликаты. Первый (уникальный)
     * файл в другом списке.
     *
     * @param file директория.
     * @return переходит к следующему файлу
     * в структуре.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!uniques.add(currentFile)) {
            duplicates.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
