package ru.job4j.gc.leak;

import java.util.Objects;

/**
 * 2. Найти утечку памяти.
 *
 * Данный класс описывает модель -
 * комментарий к посту.
 *
 * Комментарий будет закрепляться
 * за пользователем.
 *
 * @author Constantine on 14.08.2022
 */
public class Comment {

    private String text;

    private User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text)
                && Objects.equals(user, comment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, user);
    }

    @Override
    public String toString() {
        return "Comment{"
                + "text='" + text + '\''
                + ", user=" + user
                + '}';
    }
}
