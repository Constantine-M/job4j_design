package ru.job4j.serialization.jsonxml;

/**
 * 2. Формат JSON.
 *
 * Данный класс описывает счета
 * банка {@link Bank}.
 *
 * @author Constantine on 13.03.2022
 */
public class Account {

    private final int number;

    public Account(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Account{"
                + "number=" + number
                + '}';
    }
}
