package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    /**
     * Данный метод описывает принцип
     * работы чата с ботом.
     *
     * 1.Чтобы записать все фразы в лог,
     * создали список и его заполняем.
     *
     * 2.Чтобы пропускать блок с
     * ответами от бота, обьявил и
     * проинициализировал переменную
     * {@code safeWord}.
     *
     * 3.Цикл закрывается в явном виде,
     * когда мы напишем ключевое
     * слово "закончить".
     *
     * 4.Внутри цикла, если то
     * что мы пишем = слову "стоп",
     * то далее записываем ЭТО СЛОВО
     * в переменную {@code safeWord}.
     *
     * 5.Далее если ключевое слово
     * "стоп" = значению переменной
     * {@code safeWord}, то блок с
     * ответами от бота будет пропускаться.
     *
     * 6.Иначе идет проверка, что
     * слово "продолжить" = тому, что
     * мы написали в консоль. Если
     * условие выполняется, то
     * значению переменной
     * {@code safeWord} присваивается
     * значение слова/фразы, которую
     * мы ввели в консоль.
     *
     * 7.Если условие п.6 не выполняется,
     * то по-новой вводим фразу.
     * Цикл делает оборот..
     *
     * Фича: слово "продолжить"
     * записывается в лог 2 раза.
     * Проблему найти не могу.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        String safeWord = null;
        List<String> log = new ArrayList<>();
        while (!OUT.equals(message)) {
            log.add(message);
            if (STOP.equals(message)) {
                safeWord = message;
            }
            if (!STOP.equals(safeWord)) {
                Random rnd = new Random();
                String answer = readPhrases().get(rnd.nextInt(readPhrases().size()));
                System.out.println(answer);
                log.add(answer);
                message = input.nextLine();
            } else if (CONTINUE.equals(message)) {
                safeWord = message;
            } else {
                message = input.nextLine();
            }
        }
        log.add(message);
        saveLog(log);
    }

    /**
     * Данный метод читает файл и
     * записывает все фразы в список.
     *
     * @return список фраз.
     */
    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            reader.lines().forEach(phrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }

    /**
     * Данный метод сохраняет лог
     * чата в файл.
     *
     * @param log список фраз, которые
     *            написали мы, и фразы
     *            которыми ответил бот.
     */
    public void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8")))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logConversation.txt", "./data/botPhrases.txt");
        cc.run();
    }
}
