package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

/**
 * 2. Найти утечку памяти.
 *
 * При создании {@link UserGenerator} мы
 * заполним списки с именами, фамилиями,
 * отчествами, а при вызове
 * {@link UserGenerator#generate()}
 * зачищаем список {@link User} и
 * будем брать случайные значения из списков.
 * Таким образом каждый раз создаем
 * 1000 пользователей.
 *
 * @author Constantine on 14.08.2022
 */
public class UserGenerator  implements Generate {

    /*public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";*/
    public static final String PATH_NAMES = "data/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "data/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "data/gc/leak/files/patr.txt";

    public static List<String> names;
    public static List<String> surnames;
    public static List<String> patrons;

    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    /**
     * Данный метод генерирует пользователей.
     */
    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < 1000; i++) {
            StringJoiner nameJoiner = new StringJoiner(" ");
            nameJoiner.add(surnames.get(random.nextInt(surnames.size())));
            nameJoiner.add(names.get(random.nextInt(names.size())));
            nameJoiner.add(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(nameJoiner.toString()));
        }
    }

    /**
     * Даный метод пробегается по
     * документам с именами, фамилиями
     * и отчествами.
     * Это 3 разных документа.
     */
    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Данный метод получает
     * случайного пользователя
     * из списка пользователей,
     * который был сгенерирован в методе
     * {@link UserGenerator#generate()}.
     *
     * @return случайный пользователь.
     */
    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
