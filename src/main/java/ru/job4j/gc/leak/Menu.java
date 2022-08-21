package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

/**
 * 2. Найти утечку памяти.
 *
 * @author Constantine on 14.08.2022
 */
public class Menu {

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

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }

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
            System.out.print("Выберите меню: ");
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println("Выбран пункт - " + userChoice);
            if (1 == userChoice) {
                System.out.println("Введите текст");
                String text = scanner.nextLine();
                createPost(commentGenerator, userGenerator, postStore, text);
            } else if (2 == userChoice) {
                System.out.println("Введите текст");
                String text = scanner.nextLine();
                System.out.println("Выберите количество создаваемых постов");
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (3 == userChoice) {
                System.out.println(PostStore.getPosts());
            } else if (4 == userChoice) {
                postStore.removeAll();
            } else {
                run = false;
                System.out.println("Конец работы");
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
}
