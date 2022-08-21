package ru.job4j.gc.leak;

import java.util.Objects;

/**
 * 2. Найти утечку памяти.
 *
 * Данный класс описывает модель
 * пользователя.
 *
 * @author Constantine on 14.08.2022
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + '}';
    }
}
