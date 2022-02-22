package ru.job4j.io.scanner.examples;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * 7. Scanner.
 *
 * Данный класс описывает пример,
 * когда надо из потока данных
 * вычленить адреса почты,
 * которые разделены между собой “, ”.
 *
 * В данном случае мы могли
 * воспользоваться методом
 * {@link String#split} но когда
 * дело доходит до чтения файлов,
 * то Scanner позволяет использовать
 * преимущества буферизированных
 * потоков и возможности по
 * разбиению на токены.
 *
 * @author Constantine on 21.02.2022
 */
public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
