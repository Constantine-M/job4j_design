package ru.job4j.taskblock2;


import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

/**
 * 2. Поиск файлов по критерию.
 *
 * @author Constantine on 19.03.2022
 */
public class SearchMask implements Search {

    /**
     * Даный метод ищет совпадения по маске.
     *
     * Что значит по маске?
     * 1.Символ "?" означает ровно один
     * произвольный символ.
     * 2.Символ "*" означает любую
     * последовательность символов
     * произвольной длины, в том числе
     * ЗВЕЗДОЧКА может задавать и пустую
     * последовательность.
     *
     * Оказывается, есть 2 способа
     * реализовать этот метод:
     *
     * 1.Преобразование маски к регулярному
     * выражению.
     * С помощью замены символов в строке
     * произвести последовательно
     * - экранирование символа "."
     * - замена символа "*" на
     * последовательность, означающую
     * любое количество  символов;
     * - замена символов "?" на
     * последовательность, означающую
     * один символ.
     *
     * Затем в начале строки и в конце
     * строки поставьте символы,
     * означающие начало и коней
     * регулярного выражения.
     *
     * 2.Использовать
     * {@link PathMatcher} и классы
     * {@link FileSystems#getDefault()},
     * {@link FileSystem#getPathMatcher(String)}
     * При создании экземпляра
     * {@link PathMatcher} выбрать
     * синтаксис "glob" - это,
     * так скажем, упрощенный
     * regex.
     *
     * Спасибо, учитель!
     *
     * @param root директория.
     * @param mask файл, который ищем.
     * @return список совпадений.
     */
    @Override
    public List<String> search(Path root, String mask) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);
        Predicate<Path> pred = path -> pathMatcher.matches(path.getFileName());
        SearchFilesIO searcher = new SearchFilesIO(pred);
        Files.walkFileTree(root, searcher);
        return searcher.getFiles();
    }
}
