package ru.job4j.gc.profiling;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Constantine on 31.07.2022
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}
