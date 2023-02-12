package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * Данный класс описывает взаимодействие
 * пользователя с программмой путем
 * ввода текста через консоль.
 * Для этого используется класс
 * {@link Scanner}.
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
