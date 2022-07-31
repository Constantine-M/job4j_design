package ru.job4j.io.scanner.examples;

import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * 7. Scanner.
 *
 * Данный класс описывает пример,
 * когда надо из потока данных
 * вычленить только числа.
 *
 * Еще раз про {@link String#join} вот здесь
 * <a href="https://javarush.ru/groups/posts/809-v-java-8-mozhno-obhhedinjatjh-stroki"></a>
 *
 * @author Constantine on 20.02.2022
 */
public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 2 3",
                         "4 5 6",
                         "7 8 9"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}
