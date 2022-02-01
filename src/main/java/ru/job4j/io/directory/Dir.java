package ru.job4j.io.directory;

import java.io.File;

/**
 * 4.0. File.
 * 5. Валидация параметров запуска.
 *
 * В данногй задаче применяется метод
 * {@link String#format(String, Object...)},
 * в который первым параметром передается
 * строка-шаблон, в которой, на местах,
 * в которые мы хотим подставить значения,
 * стоят специальные символы (у нас - %s)
 *
 * После строки-шаблона передаются параметры,
 * значения которых и будут подставлены
 * на место символов %s, %d (для цифр).
 *
 * 1.Проверяем, что файл существует.
 * 2.Проверяем, что файл - директория.
 * 3.В цикле получаем список файлов
 * в этой директории.
 *
 * @author Constantine on 31.01.2022
 */
public class Dir {

    /**
     * Данный метод считает размер папки.
     * Метод {@link File#length()} показывает
     * размер ФАЙЛА. А если нужно узнать
     * размер ПАПКИ, то ужно пройтись по
     * ней и сложить размеры всех файлов,
     * которые находятся в этой папке.
     *
     * @param directory отдельный файл
     *                  или папка.
     * @return размер файла или папки.
     */
    public static long folderSize(File directory) {
        long length = 0;
        if (directory.isFile()) {
            length += directory.length();
        } else {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += folderSize(file);
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("Folder: %s | Size: %d", subfile.getName(), folderSize(subfile)));
        }
    }
}
