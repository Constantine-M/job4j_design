package ru.job4j.io.scanner.csv;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 7. Scanner.
 *
 * Данный класс описывает принципы
 * работы с файлами формата CSV.
 *
 * @author Constantine on 21.02.2022
 */
public class CSVReader {

    private static Path source;

    private static Path target;

    /**
     * Данный метод читает файл СSV и записывает
     * данные в новый файл.
     *
     * 1.В блоке try объявляем сканер и
     * начинаем проходить по строкам файла.
     *
     * 2.В кач-ве разделителя используем
     * символ ";".
     *
     * 3.В цикле проверяем, есть ли
     * далее строки в файле.
     *
     * 4.Если есть, то разбиваем входящую
     * строку знаком ";".
     *
     * 5.Ранее создали список индексов.
     * Смысл в том, чтобы пройтись по
     * первой строке, найти индексы значений
     * имен столбиков и записать в список.
     * Далее просто использовать этот
     * список, чтобы каждую строку не
     * проходить.
     *
     * 6.Чтобы корректно выводить нужные
     * значения в одной строке, я решил
     * с помощью {@link StringBuilder}
     * объединять в одну строку нужные
     * значения (согласно их индексам).
     *
     * 7.Далее эти объдиненные строки
     * добавлять в список на вывод.
     *
     * 8.Вносим значения списка в файл,
     * который указали.
     *
     * @param argsName аргументы, введенные
     *                 пользователем.
     */
    public static void handle(ArgsNameSc argsName) {
        String[] columns = argsName.get("filter").split(",");
        source = Paths.get(argsName.get("path"));
        target = Paths.get(argsName.get("out"));
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter(";");
            List<Integer> listIndex = new ArrayList<>();
            List<String> outList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(";");
                if (listIndex.isEmpty()) {
                    listIndex = findIndex(str, columns);
                }
                StringBuilder pair = new StringBuilder();
                for (Integer i : listIndex) {
                    pair.append(str[i]).append(";");
                }
                outList.add(pair.toString());
            }
            Files.write(target, outList, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Данный метод ищет индексы колонок,
     * по которым мы хотим отфильтровать
     * таблицу.
     *
     * Метод вынес, т.к. он по задумке
     * должен использоваться только один раз.
     *
     * @param splitString массив значений
     *                    разделенной строки.
     * @param columns имена колонок, по
     *                которым фильтруем таблицу.
     * @return индексы колонок в строке
     * колонок (в шапке таблицы).
     */
    private static List<Integer> findIndex(String[] splitString, String[] columns) {
        List<Integer> index = new ArrayList<>();
        for (String s : columns) {
            int n = Arrays.asList(splitString).indexOf(s);
            if (n != -1) {
                index.add(n);
            }
        }
        return index;
    }

    /**
     * Данный метод проверяет аргументы,
     * которые вводит пользователь.
     *
     * Некоторые проверки проводятся в
     * других методах, т.к. это удобнее
     * и эффективнее.
     *
     * @param args аргументы, вводимые
     *             пользователем.
     */
    private static void valid(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("You need to text all arguments!");
        }
        ArgsNameSc argsNames = ArgsNameSc.of(args);
        source = Paths.get(argsNames.get("path"));
        target = Paths.get(argsNames.get("out"));
        String delimiter = argsNames.get("delimiter");
        if (!Files.isRegularFile(source)) {
            throw new IllegalArgumentException("This is not a file! Enter the file name and its extension!");
        }
        if (!Files.isRegularFile(target) && !target.toString().equals("stdout")) {
            throw new IllegalArgumentException("The 'out' file is not a file!");
        }
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Delimiter can only be a character ';'!");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsNameSc argsNames = ArgsNameSc.of(args);
        valid(args);
        handle(argsNames);
        /*List<Integer> index = new ArrayList<>();
        List<String> outList = new ArrayList<>();
        ArgsNameSc argsName = ArgsNameSc.of(new String[]
                {"-path=file.csv", "-delimiter=;", "-out=stdout", "-filter=name,age"}
                );
        String[] filters = argsName.get("filter").split(",");
        Path target = Paths.get("./data/target.csv");
        Path source = Paths.get("./data/source.csv");
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter(";");
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(";");
                if (index.size() < filters.length) {
                    for (String s : filters) {
                        int n = Arrays.asList(str).indexOf(s);
                        if (n != -1) {
                            index.add(n);
                        }
                    }
                }
                StringBuilder pair = new StringBuilder();
                for (Integer i : index) {
                    pair.append(str[i]).append(";");
                }
                outList.add(pair.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Files.write(target, outList, StandardOpenOption.CREATE);*/
    }
}
