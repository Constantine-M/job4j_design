package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 6. Кодировка.
 *
 * @author Constantine on 13.02.2022
 */
public class ConsoleChat {

    /**
     * Данное поле описывает имя файла,
     * в который будет записан весь
     * диалог между ботом и пользователем.
     */
    private final String path;

    /**
     * Данное поле описывает имя файла,
     * в котором находятся строки с
     * ответами, которые будет использовать
     * бот.
     */
    private final String botAnswers;

    private static final String OUT = "закончить";

    private static final String STOP = "стоп";

    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        /*do {
            String message = input.nextLine();
            if (message.equals(STOP)) {

            }
        } while (!message.equals(OUT));*/
        String message = input.nextLine();
        while (!message.equals(OUT)) {
            String safeWord = "";
            if (message.equals(STOP)) {
                safeWord = message;
            }
            if (message.equals(CONTINUE)) {
                safeWord = message;
            }
            if (!safeWord.equals(STOP)) {
                Random rnd = new Random();
                String answer = readPhrases().get(rnd.nextInt(readPhrases().size()));
                System.out.println(answer);
            }
            message = input.nextLine();
        }
        /*while (!input.next().equals(OUT)) {
            if (!input.nextLine().equals(STOP) || input.nextLine().equals(CONTINUE)) {
                Random rnd = new Random();
                String answer = readPhrases().get(rnd.nextInt(readPhrases().size()));
                System.out.println(answer);
            }
        }*/
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            reader.lines().forEach(phrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    public void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("logi.txt", "./data/botPhrases.txt");
        cc.run();
    }
}
