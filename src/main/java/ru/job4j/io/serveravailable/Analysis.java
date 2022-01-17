package ru.job4j.io.serveravailable;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2. Анализ доступности сервера.
 *
 * Данный клас описывает принцип
 * преобразования одного файла в другой.
 *
 * Класс {@link PrintWriter} можно использовать
 * как для вывода информации на консоль,
 * так и в файл или любой другой
 * поток вывода.
 *
 * В методе main происходит запись
 *  текста в файл "unavailable.csv".
 *
 *  !!!matches() отличается от
 *  find() тем, что matches()
 *  ищет ПОЛНОЕ совпадение, а
 *  find() ищет совпадение подстроки.
 *
 * @author Constantine on 16.01.2022
 */
public class Analysis {

    /**
     * Данный метод запишет в файл
     * период времени, когда сервер
     * был недоступен.
     *
     * 1.Вводим переменную, которая
     * будет служить флагом и
     * показывать, работает сервер
     * или не рботает.
     *
     * 2.Заранее подготавливаем
     * regex, которое разобьет
     * строку на 2 группы.
     *
     * 3.Порбегаемся циклом по
     * строкам файла.
     *
     * 4.Если сервер включен и в
     * логах встретили 500 или 400,
     * то фиксируем время и переводим
     * флаг в сотояние выкл.
     *
     * 5.Пока сервер выключен и в
     * логах встречается 400 или 500 -
     * пропускаем строки.
     *
     * 6.Если сервер выключен и
     * в логах встретили числа кроме
     * 400 или 500, то записываем
     * время (оно же конец периода)
     * и флаг переводим в состояние вкл.
     *
     * @param source файл, который читаем.
     * @param target файл, куда записываем
     *               результат анализа.
     */
    public void unavailable(String source, String target) {
        boolean isOn = true;
        String text;
        Pattern pattern = Pattern.compile("(\\d{3})\\s((?:[01]\\d|2[0-3]):(?:[0-5]\\d):(?:[0-5]\\d))");
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            while ((text = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    if (isOn && matcher.group(1).matches("[45]\\d{2}")) {
                        out.printf(matcher.group(2) + ";");
                        isOn = false;
                    } else if (!isOn && !(matcher.group(1).matches("[45]\\d{2}"))) {
                        out.println(matcher.group(2));
                        isOn = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/server-1.log", "logoutV1.csv");
        analysis.unavailable("./data/server-2.log", "logoutV2.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
