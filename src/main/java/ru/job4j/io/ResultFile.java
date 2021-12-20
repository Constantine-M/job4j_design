package ru.job4j.io;

import java.io.FileOutputStream;

/**
 * 0.1. FileOutputStream.
 *
 * Программа взаимодействует с внешними
 * ресурсами. Такие ресурсы еще называют
 * приемниками и источниками.
 *
 * Файл - это источник данных и приемник.
 * Класс {@link java.io.FileOutputStream}
 * позволяет записать данные в файл.
 *
 * 1.Для записи используется метод
 * {@see FileOutputStream#write(byte b[])}
 * - этот метод принимает массив байт,
 * поэтому строку преобразовали в массив
 * байтов - {@code getBytes()}.
 * 2.Файл будет создан в корне проекта.
 * 3.Любой ресурс должен быть закрыт  -
 * для этого используется конструкция
 * try-with-resources.
 *
 * Использование {@code System.lineSeparator()}
 * позволяет сделать код независимым
 * от операционной системы.
 *
 * @author Constantine on 20.12.2021
 */
public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("table.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    table[i][j] = (i + 1) * (j + 1);
                    String value = String.valueOf(table[i][j]);
                    out.write(value.getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}
