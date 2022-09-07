package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.util.Scanner;

/**
 * 1. Реализация кеша на SoftReference.
 *
 * Данный класс непосредственно
 * реализаует работу кэша. Логика
 * спрятана в других классах - здесь
 * только демонстрация.
 *
 * @author Constantine on 28.08.2022
 */
public class Emulator {

    public static final String SELECT = "Выберите меню: ";

    public static final String DIRECTORY = "Укажите директорию: ";

    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                1. Указать кэшируемую директорию (файл).
                2. Загрузить содержимое файла в кэш.
                3. Получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    private static final int SELECT_DIR = 1;

    private static final int LOAD_TO_CACHE = 2;

    private static final int GET_FROM_CACHE = 3;

    /**
     * Данное поле описывает выбор
     * пользователя. Эта переменная
     * запоминает, какой файл указал
     * пользователь, чтобы в дальнейшем
     * использовать это в процессе
     * кеширования или получения
     * содержимого файла.
     */
    private static String userChoiceDir;

    private static void run(DirFileCache dirFileCache, Scanner scanner) {
        String ls = System.lineSeparator();
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.print(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println("Выбран пункт - " + userChoice + ls);
            switch (userChoice) {
                case SELECT_DIR -> {
                    System.out.print(DIRECTORY);
                    userChoiceDir = scanner.nextLine();
                }
                case LOAD_TO_CACHE -> {
                    dirFileCache.get(userChoiceDir);
                }
                case GET_FROM_CACHE -> {
                    System.out.println(dirFileCache.get(userChoiceDir) + ls);
                }
                default -> {
                    run = false;
                    System.out.println(EXIT);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = new DirFileCache("C:\\projects\\job4j_design\\data\\");
        run(dirFileCache, scanner);
    }
}
