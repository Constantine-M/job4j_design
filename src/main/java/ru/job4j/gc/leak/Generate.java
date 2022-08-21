package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 2. Найти утечку памяти.
 *
 * Данный интерфейс описывает
 * генератор. В данном задании
 * генерировать будем пользователей
 * и их комментарии.
 *
 * Чтение не будем реализовывать
 * отдельно для экономии кода в
 * задании, поэтому реализуем
 * как дефолтный метод в Generate.
 *
 * @author Constantine on 14.08.2022
 */
public interface Generate {

    void generate();

    /**
     * Данный метод пробегается по документу,
     * путь до которого мы передаем в
     * метод, и добавляет текст документа
     * в список.
     *
     * @param path путь до документа.
     * @return список с текстом.
     * @throws IOException
     */
    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        Files.lines(Paths.get(path))
                .forEach(text::add);
        return text;
    }
}
