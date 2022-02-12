package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 5.2. Архивировать проект.
 *
 * @author Constantine on 07.02.2022
 */
public class Zip {

    /**
     * Данный метод архивирует файлы.
     *
     * 1.Сначала мы открываем поток и создаем архив.
     *
     * 2.Далее внутри этого трай мы циклом
     * проходимся по спискам путей и передаем
     * этот путь в ZipEntry.
     *
     *3.ZipEntry - это такая сущность,
     * которая должна содержать не просто
     * имя, а ПОЛНЫЙ ПУТЬ.
     *
     * 4.Далее, в следующем блоке мы
     * создаем поток для чтения файла,
     * путь к которому мы указали.
     * Нам ведь нужно архивировать не
     * только файл, но и его содержимое.
     *
     * @param sources список путей к файлам.
     * @param target архив, в который
     *               добавляем файлы.
     */
    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static boolean valid(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You need to text the arguments!");
        }
        return true;
    }*/

    public static void main(String[] args) throws IOException {
        /*if (valid(args)) {
            ArgsNameZip jvm = ArgsNameZip.of(args);
            Path source = Paths.get(jvm.get("d"));
            Path target = Paths.get(jvm.get("o"));
            Predicate<Path> pred = path -> !source.toFile().getName().endsWith(jvm.get("e"));
            List<Path> sources = SearchZip.search(source, jvm.get("e"));
            List<Path> sources = SearchZip.search(source, pred);
            packFiles(sources, target);
        }*/
        ArgsNameZip jvm = ArgsNameZip.of(args);
        Path source = Paths.get(jvm.get("d"));
        Path target = Paths.get(jvm.get("o"));
        String excl = jvm.get("e");
        Predicate<Path> pred = path -> !path.toFile().getName().endsWith(excl);
        List<Path> sources = new ArrayList<>();
        if (Files.isDirectory(source)) {
            sources = SearchZip.search(source, pred);
        } else {
            throw new IllegalArgumentException("The path is not exists!");
        }
        packFiles(sources, target);
    }
}
