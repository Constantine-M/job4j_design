package ru.job4j.gc.leak;

import java.util.List;
import java.util.Objects;

/**
 * 2. Найти утечку памяти.
 *
 * Данный класс описывает модель
 * поста (не тот пост, где нужно
 * отказаться от вкусной еды).
 *
 * Замечание от ментора (за что спасибо):
 * "Если есть возможность использовать
 * примитивы, то их и используем.
 * Обертки - объекты, занимают больше
 * памяти и могут возникнуть  трудности
 * при работе с ними (NPE, например)".
 *
 * @author Constantine on 14.08.2022
 */
public class Post {

    private int id;

    private String text;

    private List<Comment> comments;

    public Post(int id, String text, List<Comment> comments) {
        this.id = id;
        this.text = text;
        this.comments = comments;
    }

    public Post(String text, List<Comment> comments) {
        this.text = text;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && Objects.equals(text, post.text)
                && Objects.equals(comments, post.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, comments);
    }

    @Override
    public String toString() {
        return "Post{"
                + ", text='" + text + '\''
                + ", comments=" + comments
                + '}';
    }
}
