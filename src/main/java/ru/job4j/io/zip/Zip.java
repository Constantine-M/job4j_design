package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.*;
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

    private Path source;

    private Path target;

    private String excl;

    public Path getSource() {
        return source;
    }

    public Path getTarget() {
        return target;
    }

    public String getExcl() {
        return excl;
    }

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

    private boolean valid(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("You need to text the arguments!");
        }
        ArgsNameZip jvm = ArgsNameZip.of(args);
        source = Paths.get(jvm.get("d"));
        target = Paths.get(jvm.get("o"));
        excl = jvm.get("e");
        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException("The path is not exists!");
        }
        if (!excl.startsWith(".")) {
            throw new IllegalArgumentException("Enter the extension according to template <.<extension>>");
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.valid(args);
        Predicate<Path> pred = path -> !path.toFile().getName().endsWith(zip.getExcl());
        List<Path> sources = SearchZip.search(zip.getSource(), pred);
        packFiles(sources, zip.getTarget());
    }
}
