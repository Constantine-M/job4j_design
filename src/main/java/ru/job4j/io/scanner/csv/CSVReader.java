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

    private static String target;

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
        target = argsName.get("out");
        try (Scanner scanner = new Scanner(source)) {
            scanner.useDelimiter(";");
            List<Integer> listIndex = new ArrayList<>();
            List<String> outList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] str = scanner.nextLine().split(";");
                if (listIndex.isEmpty()) {
                    listIndex = findIndex(str, columns);
                }
                String concatString = concatStr(str, listIndex);
                outList.add(concatString);
            }
            outputData(outList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Данный метод ищет индексы колонок,
     * по которым мы хотим отфильтровать
     * таблицу.
     *
     * Метод должен использоваться только один раз.
     *
     * @param splitString массив значений
     *                    разделенной строки.
     * @param columns имена колонок, по
     *                которым фильтруем таблицу.
     * @return индексы колонок в строке
     * колонок (в шапке таблицы).
     */
    private static List<Integer> findIndex(String[] splitString, String[] columns) {
        List<Integer> indexList = new ArrayList<>();
        for (String colName : columns) {
            int n = Arrays.asList(splitString).indexOf(colName);
            if (n != -1) {
                indexList.add(n);
            }
        }
        if (indexList.isEmpty()) {
            throw new IllegalArgumentException("There is no matches with column names.");
        }
        return indexList;
    }

    /**
     * Данный метод выводит данные
     * на консоль или записывает в файл.
     *
     * У ключа out есть 2 варианта.
     * При вводе stdout данные будут
     * выведены на консоль, иначе
     * они должны быть записаны в файл.
     *
     * @param outList список объединенных
     *                значений в строки.
     * @throws IOException
     */
    private static void outputData(List<String> outList) throws IOException {
        if (!target.equals("stdout")) {
            Path out = Paths.get(target);
            Files.write(out, outList);
        } else {
            for (String str : outList) {
                System.out.println(str);
            }
        }
    }

    /**
     * Данный метод объединяет значения
     * с нужными индексами в строку.
     *
     * Так как объединенная строка
     * будет добавлена в список на вывод,
     * то значения между собой необходимо
     * разделить знаком ";" - он
     * используется в таблицах Excel
     * у нас. В другой вариации используется
     * в кач-ве разделителя знак ",".
     *
     * @param splitString разделенная по
     *                    знаку строка.
     * @param indexList список индексов,
     *                  который подготовлен
     *                  заранее.
     * @return объединенная строка.
     */
    private static String concatStr(String[] splitString, List<Integer> indexList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            if (i == indexList.size() - 1) {
                str.append(splitString[index]);
            } else {
                str.append(splitString[index]).append(";");
            }
        }
        return str.toString();
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
        target = argsNames.get("out");
        String delimiter = argsNames.get("delimiter");
        if (!Files.isRegularFile(source)) {
            throw new IllegalArgumentException("This is not a file! Enter the file name and its extension!");
        }
        if (!Files.isRegularFile(Paths.get(target)) && !target.equals("stdout")) {
            throw new IllegalArgumentException("The 'out' file is not a file!");
        }
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Delimiter can only be a character ';'!");
        }
    }

    public static void main(String[] args) {
        ArgsNameSc argsNames = ArgsNameSc.of(args);
        valid(args);
        handle(argsNames);
    }
}
