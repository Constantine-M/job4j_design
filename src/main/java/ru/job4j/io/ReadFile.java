package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 0.2. FileInputStream.
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
    }
}
