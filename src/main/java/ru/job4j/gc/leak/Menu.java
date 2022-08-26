package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

/**
 * 2. Найти утечку памяти.
 *
 * @author Constantine on 14.08.2022
 */
public class Menu {

    public static final int ADD_POST = 1;
    public static final int ADD_MANY_POST = 2;
    public static final int SHOW_ALL_POSTS = 3;
    public static final int DELETE_POST = 4;

    public static final String SELECT = "Выберите меню: ";
    public static final String COUNT = "Выберите количество создаваемых постов: ";
    public static final String TEXT_OF_POST = "Введите текст: ";
    public static final String EXIT = "Конец работы";

    /**
     * Строка в таком виде встречается,
     * если вы используете Java 15 и выше
     * в проекте. Но в более ранних версиях,
     * она используется как превью фича.
     *
     * Since Java 15, text blocks
     * are available as a standard feature.
     * With Java 13 and 14, we needed
     * to enable it as a preview feature.
     *
     * Text blocks start with a “””
     * (three double-quote marks) followed
     * by optional whitespaces and a newline.
     */
    public static final String MENU = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    /**
     * Данный метод инициализирует
     * запуск программы и саму программу.
     *
     * @param commentGenerator объект генератора комментариев
     *                         {@link CommentGenerator}.
     * @param scanner объект {@link Scanner} для чтения
     *                введенных данных.
     * @param userGenerator объект генератора комментариев
     *                         {@link CommentGenerator}.
     * @param postStore объект хранилища постов
     *                  {@link PostStore}.
     */
    private static void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.print(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println("Выбран пункт - " + userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                createPost(commentGenerator, userGenerator, postStore, text);
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    /**
     * Данный меотод создает пост/твит/объявление..
     *
     * @param commentGenerator объект генератора комментариев
     *                         {@link CommentGenerator}.
     * @param userGenerator объект генератора пользователей
     *                      {@link UserGenerator}.
     * @param postStore объект хранилища постов
     *                  {@link PostStore}.
     * @param text текст поста.
     */
    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }
}
