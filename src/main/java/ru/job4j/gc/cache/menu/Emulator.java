package ru.job4j.gc.cache.menu;

import ru.job4j.gc.cache.DirFileCache;

import java.util.Scanner;

/**
 * 1. Реализация кеша на SoftReference.
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
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.print(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println("Выбран пункт - " + userChoice);
            switch (userChoice) {
                case 1 -> {
                    System.out.print(DIRECTORY);
                    userChoiceDir = scanner.nextLine();
                }
                case 2 -> {
                    dirFileCache.put(userChoiceDir, "something");
                }
                case 3 -> {
                    System.out.println(dirFileCache.get(userChoiceDir));
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
