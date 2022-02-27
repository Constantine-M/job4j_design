package ru.job4j.io.intro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 0.2. FileInputStream.
 * 0.3. BufferedReader.
 *
 * Данный класс показывает,
 * как производить чтение файла.
 *
 * 1.Здесь используется конструкция
 * try-with-resources, чтобы закрыть поток ввода.
 *
 * 2.Для манипуляций со строкой создаем
 * объект {@link StringBuilder}.
 *
 * 3.В цикле мы считываем поток пока
 * он не закончится (= -1).
 *
 * 4.Для этого мы создаем переменную
 * {@code int read}, в которой будут
 * храниться байты информации.
 *
 * 5.Далее преобразуем байты в char, т.к.
 * там содержатся буквы латинского алфавита
 * и соединяем текст друг с другом с
 * помощью {@code append}.
 *
 * Заккоментированный код позволяет
 * разбить текст по строкам (но! это
 * делает и обычный {@code println}.
 *
 * Ниже пример с буфферизированными
 * потоками - в таком случае мы
 * читаем не по одному байту, а по
 * несколько.
 *
 * 1.{@code new BufferedReader
 * (new FileReader("input.txt")))} - тут
 * наглядный пример шаблона декоратор.
 * Один поток оборачивается в другой.
 *
 * 2.Базовый поток - это поток байтов.
 * Его можно обернуть в символьный поток,
 * если мы знаем, что нам нужно читать текст.
 *
 * 3.Символьные потоки позволяют читать
 * сразу символы, а не байты. Так же у
 * буферизированного символьного потока
 * есть методы чтения целой строки.
 * В нашем примере используется чтение
 * и вывод строк через stream api.
 *
 * @author Constantine on 08.01.2022
 */
public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            /*String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }*/
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("With buffered stream...");
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
