package ru.job4j.gc.leak;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2. Найти утечку памяти.
 *
 * Данный класс описывает наше
 * хранилище постов.
 *
 * Здесь применялся атомарный класс
 * {@link AtomicInteger}.
 * Операция называется атомарной тогда,
 * когда её можно безопасно выполнять
 * при параллельных вычислениях в
 * нескольких потоках, не используя
 * при этом ни блокировок, ни synchronized.
 *
 * Также благодаря этому, приложение
 * отрабатывает быстрее.
 *
 * Но есть и минусы. Таким образом,
 * используя {@link AtomicInteger},
 * мы изолируем состояние определенного
 * потока. Это значит, что каждый поток
 * будет содержать скрытую ссылку на свою
 * копию переменной {@link ThreadLocal}
 * и в дальнейшем будет поддерживать
 * свою собственную копию, вместо
 * того чтобы использовать ресурсы
 * между всеми потоками, пока этот
 * самый поток жив.
 *
 * An {@code AtomicInteger} is used in
 * applications such as atomically
 * incremented counters, and cannot be
 * used as a replacement for an
 * {@link java.lang.Integer}.
 *
 * @author Constantine on 14.08.2022
 */
public class PostStore {

    private static Map<Integer, Post> posts = new HashMap<>();

    private Integer id = 1;

    public Post add(Post post) {
        post.setId(id);
        posts.put(id, post);
        id++;
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public static Collection<Post> getPosts() {
        return posts.values();
    }
}
