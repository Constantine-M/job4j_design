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

    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    public Map<FileProperty, List<Path>> getDuplicates() {
        return duplicates;
    }

    /**
     * Данный метод проходит по всей структуре
     * файла, анализируя каждый элемент.
     *
     * 1.Проходимся по директории и заполняем
     * карту, где ключом является объект
     * класса {@link FileProperty}.
     *
     * 2.В карте содержатся только уникальные
     * ключи, поэтому если мы находим первый
     * уникальный файл (оригинал), то создаем
     * для него новый список, в котором будем
     * хранить путь к нему.
     *
     * 3.Далее добавляем путь к этому файлу в
     * созданный список.
     *
     * 3.Если в процессе прохождения по папкам
     * мы находим дубликат файла-оригинала,
     * то путь к этому дубликату сохраняем в
     * список путей.
     *
     * Этот список ассоциируется с файлом-оригиналом
     * (с ключом). В случае нахождения дубликата
     * у 1 файла будет 2 разных пути.
     *
     * @param file директория.
     * @return переходит к следующему файлу
     * в структуре.
     * @throws IOException
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty currentFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!duplicates.containsKey(currentFile)) {
            duplicates.put(currentFile, new ArrayList<>());
            duplicates.get(currentFile).add(file);
        } else {
            duplicates.get(currentFile).add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
