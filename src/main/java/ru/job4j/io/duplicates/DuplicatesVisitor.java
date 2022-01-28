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
     * 1.Проходимся по директории и заполняем
     * карту, где ключом является объект
     * класса {@link FileProperty}.
     *
     * 2.В карте содержатся только уникальные
     * ключи, поэтому если мы находим дубликат,
     * то помещаем его в список. В списке
     * мы храним только пути до дубликатов.
     *
     * 3.Прежед чем поместить в список,
     * спрашиваем, есть ли в этом списке
     * помимо дубликата и оригинальный файл?
     * Если нету,то добавляем в список
     * и оригинальный файл, и дубликат.
     * Иначе помещаем в список только дубликат.
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
        } else if (!duplicate.contains(hmap.get(currentFile))) {
            duplicate.add(hmap.get(currentFile));
            duplicate.add(file);
        } else {
            duplicate.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
