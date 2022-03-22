package ru.job4j.taskblock2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 2. Поиск файлов по критерию.
 *
 * @author Constantine on 19.03.2022
 */
public class FileFinder {

    private static Search searchSrategy;

    private static Path source;

    private static String target;

    private static String file;

    private static String searchType;

    /**
     * Данный метод описывает программу в целом.
     * 1.Выбирется критерий поиска.
     * 2.Заполняется список.
     * 3.Если пустой, то ничего никуда
     * не записывать (ведь ничего нет).
     * 4.Если что-то нашлось, то
     * записать в файл.
     */
    private static void run() throws IOException {
        List<String> results = new ArrayList<>();
        if (searchType.equals("mask")) {
            searchSrategy = new SearchMask();
            results = searchSrategy.search(source, file);
        } else if (searchType.equals("name")) {
            searchSrategy = new SearchName();
            results = searchSrategy.search(source, file);
        } else if (searchType.equals("regex")) {
            searchSrategy = new SearchRegex();
            results = searchSrategy.search(source, file);
        }
        if (results.isEmpty()) {
            System.out.println("There is no matches found.");
        } else {
            Path out = Paths.get(target);
            Files.write(out, results);
            System.out.println("Matches found and were write to log-file!");
        }
    }

    private boolean valid(String[] args) {
        ArgsNameIO jvm = ArgsNameIO.of(args);
        source = Paths.get(jvm.get("d"));
        target = jvm.get("o");
        file = jvm.get("n");
        searchType = jvm.get("t");
        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException("The path is not exists!");
        }
        List<String> listTypes = List.of("mask", "name", "regex");
        if (!listTypes.contains(searchType)) {
            throw new IllegalArgumentException("Search type not found! There are search type by mask, name and regex!");
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        FileFinder fileFinder = new FileFinder();
        fileFinder.valid(args);
        run();
    }
}
