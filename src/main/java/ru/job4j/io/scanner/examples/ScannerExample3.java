package ru.job4j.io.scanner.examples;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 7. Scanner.
 *
 * Данный класс описывает пример,
 * когда нужно прочитать числа в
 * шестнадцатеричном виде и
 * вывести в десятичном.
 *
 * @author Constantine on 21.02.2022
 */
public class ScannerExample3 {
    public static void main(String[] args) throws IOException {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
