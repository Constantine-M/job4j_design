package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 0.2. FileInputStream.
 *
 * 1.Прочитали текст.
 * 2.Разбили текст по строкам.
 * 3.Преобразовали текст в числа
 * и выяснили, какие из них четные.
 *
 * @author Constantine on 08.01.2022
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    System.out.println(number + " - even number");
                } else {
                    System.out.println(number + " - an odd number");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
