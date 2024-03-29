package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

/**
 * 2. Найти утечку памяти.
 *
 * Данный класс описывает генератор
 * комментариев.
 *
 * Также при создании заполним список
 * фразами, а при вызове
 * {@link CommentGenerator#generate()}
 * зачистим список, а затем сгенерируем
 * 50 комментариев из случайных фраз.
 *
 * @author Constantine on 14.08.2022
 */
public class CommentGenerator implements Generate {

    /*public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";*/
    public static final String PATH_PHRASES = "data/gc/leak/files/phrases.txt";

    public static final int COUNT = 50;

    private List<Comment> comments = new ArrayList<>();

    private List<String> phrases;

    private UserGenerator userGenerator;

    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    /**
     * Данный метод пробегается
     * по документу (золотому фонду
     * цитат) и добавляет цитаты
     * в список фраз.
     */
    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Данный метод генерирует
     * комментарии.
     */
    @Override
    public void generate() {
        comments.clear();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct()
                .limit(3)
                .forEach(ints::add);
        for (int i = 0; i < COUNT; i++) {
            StringJoiner commentJoiner = new StringJoiner(" ");
            commentJoiner.add(phrases.get(ints.get(0)));
            commentJoiner.add(phrases.get(ints.get(1)));
            commentJoiner.add(phrases.get(ints.get(2)));
            comments.add(new Comment(commentJoiner.toString(),
                    userGenerator.randomUser()));
        }
    }
}
