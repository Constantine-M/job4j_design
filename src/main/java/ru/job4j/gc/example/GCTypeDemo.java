package ru.job4j.gc.example;

import java.util.Random;

/**
 * 0. Виды сборщиков мусора.
 *
 * Cтоит отметить флаг -Xlog:gc* (до JDK 9
 * нужно использовать -XX:-PrintGCDetails)
 * - если мы его зададим то, сможем увидеть
 * лог сборщика в консоли.
 *
 * Ключи для запуска:
 * Serial => -XX:+UseSerialGC
 * Parallel => -XX:+UseParallelGC
 * CMS => -XX:+UseConcMarkSweepGC (доступен до JDK 14)
 * G1 => -XX:+UseG1GC
 * ZGC => -XX:+UseZGC
 *
 * Данная программа составляет строку из
 * продублированного несколько раз символа,
 * при этом перезаписывая ячейки массива.
 * Старые строки стираются сборщиком мусора
 *
 * @author Constantine on 31.07.2022
 */
public class GCTypeDemo {

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
