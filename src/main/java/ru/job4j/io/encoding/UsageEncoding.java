package ru.job4j.io.encoding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 6. Кодировка.
 *
 * @author Constantine on 12.02.2022
 */
public class UsageEncoding {

    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(s -> builder.append(s).append(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Данный метод записывает данные
     * в файл.
     *
     * Открытие потоков ввода/вывода
     * тяжелая операция, как и в целом
     * работа с ресурсами (работы
     * файловой системой, сетью,
     * базой и т.д.).
     *
     * Поэтому в методе мы производим
     * запись не по одной строчке, а
     * целым списком. Таким образом,
     * поток открывается один раз,
     * а внутри мы просто проходимся
     * по списку строк.
     *
     * @param path путь, куда записываем.
     * @param data данные, которые записываем.
     */
    public void writeDataInFile(String path, List<String> data) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        encoding.writeDataInFile(path, strings);
        String s = encoding.readFile(path);
        System.out.println("Данные из файла:");
        System.out.println(s);
    }
}
